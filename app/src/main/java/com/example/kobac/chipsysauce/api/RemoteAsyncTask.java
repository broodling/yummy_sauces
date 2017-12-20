package com.example.kobac.chipsysauce.api;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;

import java.net.NoRouteToHostException;
import java.util.concurrent.TimeoutException;

/**
 * Asynchronous task that specializes in fetching data from remote sources.
 *
 * @param <RESULT>
 *            The expected result.
 */
abstract public class RemoteAsyncTask<RESULT> {

	/**
	 * Executes the RemoteTask with a timeout.
	 *
	 * @param context
	 *            The application context.
	 * @param timeout
	 *            Timeout in seconds, after which the TimeoutException exception
	 *            will be raised. Set to null to skip timeout.
	 * @return The task that has been executed.
	 */
	@SuppressLint("NewApi")
	public RemoteAsyncTask<RESULT> execute(final Context context, final Integer timeout) {
		mContext = context;

		// Handle the timeout properly
		mTimeout = timeout != null && timeout > 0 ? timeout * 1000 : null;

		// Check the network connection
		if (requiresNetworkConnection() && !NetworkConnection.hasNetworkConnection(context)) {
			onError_NoNetwork();
			return this;
		}

		// Start the task
		getAsyncTask();
		if (Build.VERSION.SDK_INT >= 11 && useThreadPool()) {
			mAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} else {
			mAsyncTask.execute();
		}

		// Check for the timeout
		if (mTimeout != null) {
			mHandler = new Handler();
			mRunnable = new Runnable() {

				@Override
				public void run() {
					mHandler.removeCallbacks(this);
					mHandler.removeCallbacksAndMessages(this);
					mHandler = null;
					mRunnable = null;
					if (mAsyncTask != null && mAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
						mNetworkException = new TimeoutException();
						mAsyncTask.cancel(true);
						mAsyncTask = null;
					}
				}
			};
			mHandler.postDelayed(mRunnable, mTimeout);
		}

		return this;
	}

	/**
	 * Get the working asynchronous task.
	 *
	 * @return The task what will be executed in the background thread.
	 */
	private AsyncTask<Void, Void, RESULT> getAsyncTask() {
		return mAsyncTask = new AsyncTask<Void, Void, RESULT>() {
			private boolean finish = false;

			@Override
			protected void onPreExecute() {
				RemoteAsyncTask.this.onPreExecute();
			}

			@Override
			protected RESULT doInBackground(final Void... params) {
				try {

					// Check for offline alternative
					if (!NetworkConnection.hasNetworkConnection(mContext)) {
						final RESULT result = offlineAlternative();
						if (result != null) {
							return result;
						}
						throw new NoRouteToHostException();
					}

					return RemoteAsyncTask.this.doInBackground();

				} catch (final Exception e) {
					e.printStackTrace();
					mNetworkException = e;
					return null;
				}
			}

			@Override
			protected void onPostExecute(final RESULT result) {

				// If all went well
				if (mNetworkException == null) {
					RemoteAsyncTask.this.onSuccess(result);
					finish = true;
				}

				// Dismiss progress dialog
				dismissProgressDialog();
				if (finish) {
					return;
				}

				// If the result was null
				if (mNetworkException == null) {
					mNetworkException = new NullPointerException();
				}

				// Show the error
				showErrorIfAny();
			}

			@Override
			protected void onCancelled() {
				dismissProgressDialog();
				showErrorIfAny();
			}

			/**
			 * Show the error, if any encountered.
			 */
			private void showErrorIfAny() {

				// Make sure the error exited
				if (mNetworkException == null) {
					return;
				}

				// Raise proper exception
				try {
					throw mNetworkException;
				} catch (final NoRouteToHostException e) {
					onError_NetworkTimeout();
				} catch (final TimeoutException e) {
					onError_NetworkTimeout();
				} catch (final Exception e) {
					onError_Other(e);
				}
			}
		};
	}

	/**
	 * Cancels the running task, if any.
	 *
	 * @param mayInterruptIfRunning
	 *            Set to true to force the interrupt.
	 * @return True if the task has been canceled, false otherwise.
	 */
	public boolean cancel(final boolean mayInterruptIfRunning) {

		try {
			if (mAsyncTask != null && mAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
				mAsyncTask.cancel(mayInterruptIfRunning);
				return true;
			}
		} catch (final Exception e) {}
		mAsyncTask = null;

		return false;
	}

	/**
	 * Get the asynchronous task which is running in the background.
	 *
	 * @return The requested task.
	 */
	public AsyncTask<Void, Void, RESULT> getTask() {
		return mAsyncTask;
	}

	/**
	 * Returns true if this task was cancelled before it completed normally. If
	 * you are calling cancel(boolean) on the task, the value returned by this
	 * method should be checked periodically from doInBackground(Object[]) to
	 * end the task as soon as possible.
	 *
	 * @return Gives true only if task was cancelled before it was completed.
	 */
	public boolean isCancelled() {
		return mAsyncTask != null ? mAsyncTask.isCancelled() : true;
	}

	/**
	 * Set the progress dialog for the load.
	 *
	 * @param progressDialog
	 *            The dialog to set.
	 */
	public void setProgressDialog(final ProgressDialog progressDialog) {
		mProgressDialog = progressDialog;
		if (mProgressDialog != null) {
			mProgressDialog.show();
		}
	}

	/**
	 * Dismiss the progress dialog if visible.
	 */
	private void dismissProgressDialog() {
		try {

			if (mHandler != null) {
				mHandler.removeCallbacks(mRunnable);
				mHandler.removeCallbacksAndMessages(mRunnable);
				mHandler = null;
			}

			mRunnable = null;
			mAsyncTask = null;

			if (mProgressDialog != null && mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Decides if this loading required network connection.
	 *
	 * @return Return false not to check the network connection before start.
	 */
	protected boolean requiresNetworkConnection() {
		return true;
	}

	/**
	 * Choose to use or not thread pool.
	 *
	 * @return True to use thread pool, false no to use it.
	 */
	protected boolean useThreadPool() {
		return true;
	}

	/**
	 * Supply an offline alternative for the download.
	 *
	 * @return The offline result or null if not available.
	 */
	public RESULT offlineAlternative() {
		return null;
	}

	/**
	 * The application context.
	 */
	private Context mContext;

	/**
	 * Underlying asynchronous task.
	 */
	private AsyncTask<Void, Void, RESULT> mAsyncTask;

	/**
	 * Optional progress dialog to show.
	 */
	private ProgressDialog mProgressDialog;

	/**
	 * Encountered exception.
	 */
	public Exception mNetworkException = null;

	/**
	 * Define timeout, in seconds.
	 */
	private Integer mTimeout;

	/**
	 * The handled for the background task.
	 */
	private Handler mHandler;

	/**
	 * The runnable for the background task.
	 */
	private Runnable mRunnable;

	/**
	 * Invoked just before the task is about to be executed.
	 */
	abstract protected void onPreExecute();

	/**
	 * Define the background remote task that will be executed.
	 *
	 * @return
	 *         The result from the remote task.
	 * @throws Exception
	 *             ~
	 */
	abstract protected RESULT doInBackground() throws Exception;

	/**
	 * Invoked when the API has successfully returned result.
	 *
	 * @param result
	 *            The result from the API.
	 */
	abstract protected void onSuccess(final RESULT result);

	/**
	 * Invoked when the task was cancelled by the user.
	 */
	abstract protected void onCancelled();

	/**
	 * Invoked when there is no network connection.
	 */
	abstract protected void onError_NoNetwork();

	/**
	 * Invoked when network timeout is reached.
	 */
	abstract protected void onError_NetworkTimeout();

	/**
	 * Invoked when an unknown error occurs.
	 *
	 * @param exception
	 *            The raised exception.
	 */
	abstract protected void onError_Other(final Exception exception);
}
