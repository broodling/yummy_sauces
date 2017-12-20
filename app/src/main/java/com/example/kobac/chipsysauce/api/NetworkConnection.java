package com.example.kobac.chipsysauce.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.net.ConnectivityManager.TYPE_ETHERNET;
import static android.net.ConnectivityManager.TYPE_WIFI;

/**
 * The list of available network connections.
 */
public enum NetworkConnection {

	/**
	 * There is no network.
	 */
	NONE,

	/**
	 * Device is connected via mobile network.
	 */
	MOBILE,

	/**
	 * Device is connected via WiFi.
	 */
	WIFI,

	/**
	 * Device is connected via ethernet.
	 */
	ETHERNET;

	public static final int NETWORK_ERROR = 99;

	public static final int NETWORK_NONE = 0;

	public static final int NETWORK_MOBILE = 1;

	public static final int NETWORK_WIFI = 2;

	public static final int NETWORK_ETHERNET = 3;

	/**
	 * Checks if the user has any network connection.
	 *
	 * @param context
	 *            The application context.
	 * @return
	 *         True if mobile of wireless network is available.
	 */
	public static boolean hasNetworkConnection(final Context context) {
		return getNetworkConnection(context) >= MOBILE.ordinal();
	}

	/**
	 * Returns the status of the network.
	 *
	 * @param context The application context.
	 * @return -1 on error, 0 if no network, 1 for mobile and 2 for WiFi
	 */
	public static Integer getNetworkConnection(final Context context) {


		if (context != null) {
			try {
				final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
				final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
				if (null != networkInfo) {
					if (networkInfo.getType() == TYPE_WIFI) {
						return NETWORK_WIFI;
					}
					if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
						return NETWORK_MOBILE;
					}
					if (networkInfo.getType() == TYPE_ETHERNET) {
						return NETWORK_ETHERNET;
					}
				}
				return NETWORK_NONE;

			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
		return NETWORK_ERROR;
	}

}
