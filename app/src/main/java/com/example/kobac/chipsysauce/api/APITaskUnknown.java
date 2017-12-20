package com.example.kobac.chipsysauce.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;

import com.example.kobac.chipsysauce.BaseActivity;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Handle the asynchronous task that executes the API calls.
 */
public abstract class APITaskUnknown extends RemoteAsyncTask<List<APIResponse>> {

    /**
     * The progress dialog.
     */
    private final boolean mAutoInterface;
    /**
     * The progress dialog.
     */
    private ProgressDialog dialog;

    /**
     * Initialize and execute the task.
     */
    public APITaskUnknown() {
        this(true);
    }

    /**
     * Initialize and execute the task.
     *
     * @param autoInterface Set to true to automatically show the interface
     */
    public APITaskUnknown(final boolean autoInterface) {
        mAutoInterface = autoInterface;

        // Check Internet connection
        if (!NetworkConnection.hasNetworkConnection(getActivity())) {
//			getActivity().showToast(R.string.Network_connection_is_not_avaialble);

        } else {
            execute(getActivity(), null);
        }
    }

    @Override
    protected void onPreExecute() {
        if (mAutoInterface) {
//			dialog = showCancelableDialog(getActivity(), getTask(), getActivity().getString(R.string.please_wait_));
        }
    }

    @Override
    protected void onSuccess(List<APIResponse> apiResponses) {

    }

    @Override
    protected void onCancelled() {
        dismiss();
    }

    @Override
    protected void onError_NoNetwork() {
        dismiss();

        if (mAutoInterface) {
            // getActivity().showToast(R.string.Network_connection_is_not_avaialble);
        }
    }

    @Override
    protected void onError_Other(final Exception exception) {
        dismiss();

        if (mAutoInterface) {
            // getActivity().showToast(R.string.Network_error_occured);
        }
    }

    @Override
    protected void onError_NetworkTimeout() {
        onError_Other(new TimeoutException(""));
    }

    /**
     * Dismiss the progress dialog.
     */
    protected void dismiss() {
        if (dialog != null) {
            try {
                dialog.dismiss();
            } catch (final Exception e) {
            }
        }
    }

    /**
     * Create a progress dialog for AsyncTask that can be cancelled by user.
     *
     * @param context The application context.
     * @param task    Task that should be cancelled.
     * @param content Content inside the progress dialog.
     * @return Fully built progress dialog with the ability to cancel.
     */
    public ProgressDialog showCancelableDialog(final Context context, final AsyncTask<?, ?, ?> task, final String content) {
        try {
            final ProgressDialog dialog = ProgressDialog.show(context, null, content);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnCancelListener(new OnCancelListener() {

                @Override
                public void onCancel(final DialogInterface dialog) {
                    task.cancel(true);
                }
            });

            return dialog;
        } catch (final Exception e) {
        }
        return null;
    }

    /**
     * Define the calling activity.
     *
     * @return The activity that is calling the task.
     */
    abstract protected BaseActivity getActivity();

    /**
     * Called when the API task has been successfully completed.
     *
     * @param response The response from the API.
     */
    abstract public void onComplete(final APIResponse response);

    /**
     * Called when the API task has encountered an error.
     *
     * @param error The error to show to the user.
     */
    abstract public void onError(final String error);

}
