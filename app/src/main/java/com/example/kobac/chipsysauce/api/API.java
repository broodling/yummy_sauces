package com.example.kobac.chipsysauce.api;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Base for all API super classes.
 */
public class API {

    /**
     * Read-only request.
     */
    public static final String GET = "GET";
    /**
     * Request to store new data.
     */
    public static final String PUT = "PUT";
    /**
     * Request with additional data.
     */
    public static final String POST = "POST";


    /**
     * Request to delete a resource.
     */
    public static final String DELETE = "DELETE";
    /**
     * Set to true to output complete response.
     */
    private static final boolean DEBUG = true;

    /**
     * Get the list of songs.
     *
     * @param context The application context.
     * @return The complete response from the server.
     * @throws Exception ~
     */

    public static APIResponse chipsyAPI(final Context context) throws Exception {
        return execute(context, GET, "http://umaci.intellex.rs/api/sync", null);
    }

    /**
     * Get the details about forecast.
     *
     * @param context The application context.
     * @param id      The Id of the city.
     * @return The response from the server.
     * @throws Exception -
     */


    /**
     * Execute a call to API function.
     *
     * @param context     The application context.
     * @param httpMethod  HTTP request to use.
     * @param path        The name of the API.
     * @param requestBody Data to send to server in a request body.
     * @return Content from the server.
     * @throws Exception ~
     */
    private static APIResponse execute(final Context context, final String httpMethod, final String path, final String requestBody) throws Exception {

        // Build the URL that is going to be targeted
        final String URL = path;

        // Create connection
        HttpURLConnection httpConnection;
        httpConnection = (HttpURLConnection) new URL(URL).openConnection();

        // Set HTTP method
        httpConnection.setRequestMethod(httpMethod);
        if (!httpMethod.equals(DELETE)) {
            httpConnection.setDoOutput(!httpMethod.equals(GET));
        }

        // Add body to the request if exists
        if (requestBody != null && requestBody.length() > 0 && httpConnection.getDoOutput()) {
            final OutputStream os = httpConnection.getOutputStream();
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(requestBody);
            writer.flush();
            writer.close();
            os.close();
        }

        // Log
        final String body = "body: " + (requestBody != null ? requestBody : "");

        // Read the response
        final int responseCode = httpConnection.getResponseCode();

        // Get proper response based on error
        InputStream inputStream = httpConnection.getErrorStream();
        final boolean success = inputStream == null;
        if (success) {
            inputStream = httpConnection.getInputStream();
        }

        // Wrap data with reader
        final StringBuilder builder = new StringBuilder();
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine = "";
        while ((inputLine = bufferedReader.readLine()) != null) {

            // Append data to the StringBuilder
            builder.append(inputLine);
            builder.append("\n");
        }

        // Close buffered reader
        bufferedReader.close();

        // Capture the response
        final APIResponse response = new APIResponse(success, responseCode, builder.toString());


        return response;
    }

}
