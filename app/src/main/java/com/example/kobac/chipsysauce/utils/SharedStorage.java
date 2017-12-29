package com.example.kobac.chipsysauce.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/**
 * Convenience class for manipulating shared preferences.
 */
public class SharedStorage {

	/**
	 * Initializes the shared preferences and returns the reference.
	 *
	 * @param context
	 *            The application context;
	 * @return The reference to shared preferences.
	 */
	@SuppressLint("InlinedApi")
	@SuppressWarnings("deprecation")
	public static synchronized SharedPreferences getPreferences(final Context context) {
		if (mSharedPreferences == null) {
			if (Build.VERSION.SDK_INT >= 11) {
				mSharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_MULTI_PROCESS);
			} else {
				mSharedPreferences = context.getSharedPreferences(context.getPackageName(), 0);
			}
		}

		return mSharedPreferences;
	}

	/**
	 * Initializes the shared preferences and returns the reference.
	 *
	 * @param context
	 *            The application context;
	 * @return The reference to shared preferences editor.
	 */
	public static synchronized SharedPreferences.Editor getEditor(final Context context) {
		if (mSharedPreferencesEditor == null) {
			mSharedPreferencesEditor = getPreferences(context).edit();
		}

		return mSharedPreferencesEditor;
	}

	/**
	 * Deletes a shared preferences value.
	 *
	 * @param context
	 *            The application context;
	 * @param keys
	 *            Keys to be deleted.
	 */
	public static synchronized void remove(final Context context, final String... keys) {
		for (final String key : keys) {
			getEditor(context).putString(key, null);
			getEditor(context).remove(key);
		}
		getEditor(context).commit();
	}

	/**
	 * Checks if the supplied key is found in the shared storage.
	 *
	 * @param context
	 *            The application context;
	 * @param key
	 *            Name of the key.
	 * @return
	 *         True if the key exists in the storage, false otherwise.
	 */
	public static synchronized boolean contains(final Context context, final String key) {
		return getPreferences(context).contains(key);
	}

	/**
	 * Puts the boolean in the shared preferences.
	 *
	 * @param context
	 *            The application context;
	 * @param key
	 *            Key that holds the value.
	 * @param value
	 *            Value to be preserved.
	 */
	public static synchronized void put(final Context context, final String key, final Boolean value) {
		getEditor(context).putBoolean(key, value).commit();
	}

	/**
	 * Puts the integer in the shared preferences.
	 *
	 * @param context
	 *            The application context;
	 * @param key
	 *            Key that holds the value.
	 * @param value
	 *            Value to be preserved.
	 */
	public static synchronized void put(final Context context, final String key, final Integer value) {
		getEditor(context).putInt(key, value).commit();
	}

	/**
	 * Puts the long integer in the shared preferences.
	 *
	 * @param context
	 *            The application context;
	 * @param key
	 *            Key that holds the value.
	 * @param value
	 *            Value to be preserved.
	 */
	public static synchronized void put(final Context context, final String key, final Long value) {
		getEditor(context).putLong(key, value).commit();
	}

	/**
	 * Puts the float in the shared preferences.
	 *
	 * @param context
	 *            The application context;
	 * @param key
	 *            Key that holds the value.
	 * @param value
	 *            Value to be preserved.
	 */
	public static synchronized void put(final Context context, final String key, final Float value) {
		getEditor(context).putFloat(key, value).commit();
	}

	/**
	 * Puts the string in the shared preferences.
	 *
	 * @param context
	 *            The application context;
	 * @param key
	 *            Key that holds the value.
	 * @param value
	 *            Value to be preserved.
	 */
	public static synchronized void put(final Context context, final String key, final String value) {
		getEditor(context).putString(key, value).commit();
	}

	/**
	 * Get a boolean from the shared preferences.
	 *
	 * @param context
	 *            The application context.
	 * @param key
	 *            Name of the key.
	 * @param defValue
	 *            Default value, returned if not found.
	 * @return Value from the shared preferences or the default value.
	 */
	public static synchronized Boolean getBoolean(final Context context, final String key, final Boolean defValue) {
		return getPreferences(context).getBoolean(key, defValue);
	}

	/**
	 * Get an integer from the shared preferences.
	 *
	 * @param context
	 *            The application context;
	 * @param key
	 *            Name of the key.
	 * @param defValue
	 *            Default value, returned if not found.
	 * @return Value from the shared preferences or the default value.
	 */
	public static synchronized Integer getInt(final Context context, final String key, final Integer defValue) {
		return getPreferences(context).getInt(key, defValue);
	}

	/**
	 * Get a long integer from the shared preferences.
	 *
	 * @param context
	 *            The application context;
	 * @param key
	 *            Name of the key.
	 * @param defValue
	 *            Default value, returned if not found.
	 * @return Value from the shared preferences or the default value.
	 */
	public static synchronized Long getLong(final Context context, final String key, final Long defValue) {
		return getPreferences(context).getLong(key, defValue);
	}

	/**
	 * Get a float from the shared preferences.
	 *
	 * @param context
	 *            The application context;
	 * @param key
	 *            Name of the key.
	 * @param defValue
	 *            Default value, returned if not found.
	 * @return Value from the shared preferences or the default value.
	 */
	public static synchronized Float getFloat(final Context context, final String key, final Float defValue) {
		return getPreferences(context).getFloat(key, defValue);
	}

	/**
	 * Get a string from the shared preferences.
	 *
	 * @param context
	 *            The application context;
	 * @param key
	 *            Name of the key.
	 * @param defValue
	 *            Default value, returned if not found.
	 * @return Value from the shared preferences or the default value.
	 */
	public static synchronized String getString(final Context context, final String key, final String defValue) {
		return getPreferences(context).getString(key, defValue);
	}

	/**
	 * Reference to the instance of shared preferences, for singleton pattern.
	 */
	private static SharedPreferences mSharedPreferences = null;

	/**
	 * Reference to the instance of editor for shared preferences, for singleton
	 * pattern.
	 */
	private static SharedPreferences.Editor mSharedPreferencesEditor = null;

}
