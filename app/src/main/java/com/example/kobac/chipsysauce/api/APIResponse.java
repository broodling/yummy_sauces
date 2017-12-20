package com.example.kobac.chipsysauce.api;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import java.io.Serializable;

/**
 * A response from the server.
 */
public class APIResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Define a response from an API function.
	 *
	 * @param success
	 *            True if the response is successful.
	 * @param responseCode
	 *            The response code sent from the server.
	 * @param responseBody
	 *            The plain response body sent from the server.
	 */
	public APIResponse(final boolean success, final int responseCode, final String responseBody) {
		mResponseCode = responseCode;

		// Parse the body
		mBody = responseBody.toString();
		try {

			// Try to get JSON object
			final Object jsonValue = JSONValue.parse(mBody.toString());

			// JSON array or object
			if (jsonValue instanceof JSONArray) {
				mBody = jsonValue;

			} else if (jsonValue instanceof JSONObject) {
				mBody = jsonValue;
			}

		} catch (final Exception e) {

			// If the response is not empty string, log the exception
			if (!responseBody.equals("")) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Get the response code.
	 *
	 * @return
	 *         The standard HTTP response code.
	 */
	public int getCode() {
		return mResponseCode;
	}

	/**
	 * Get the response body as plain text.
	 *
	 * @return
	 *         The original response body.
	 */
	public String getString() {
		return mBody.toString();
	}

	/**
	 * Get the response body as JSONObject.
	 *
	 * @return
	 *         The response body parsed as a JSONObject.
	 */
	public JSONObject getJSONObject() {
		return !mBody.equals("") ? (JSONObject) mBody : new JSONObject();
	}

	/**
	 * Get the response body as JSONArray.
	 *
	 * @return
	 *         The response body parsed as a JSONArray.
	 */
	public JSONArray getJSONArray() {
		return !mBody.equals("") ? (JSONArray) mBody : new JSONArray();
	}

	@Override
	public String toString() {
		return (mResponseCode == 200 ? "✓" : "⚠ " + mResponseCode) + " " + getString();
	}

	/**
	 * The HTTP response code from the server.
	 */
	private final int mResponseCode;

	/**
	 * The response body from the server.
	 */
	private Object mBody = "";

}
