/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlis.location.nominatim.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author michaelassraf
 */
public class HttpUtlis {

    Logger logger = Logger.getLogger(getClass().getSimpleName());
    public static final String START_PARAM_URL_DELIMETER = "?";
    public static final String URL_VALUE_DELIMETER = "=";
    public static final String URL_PARAM_DELIMETER = "&";
    public static final String URL_DELIMETER = "/";

    public URL getURL(String urlString) {
        try {
            return new URL(urlString);
        } catch (Throwable th) {
            logger.error("Can't build url", th);
            return null;
        }
    }

    public String getHttpCallAsString(URL url) {
        String response = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            response = inputStreamToString(httpURLConnection.getInputStream());
        } catch (Throwable th) {
            logger.error("Can't get response from web location " + url, th);
        }
        return response;
    }

    public String inputStreamToString(InputStream inputStream) {
        try {
            if (inputStream == null) {
                return null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (Throwable th) {
            logger.error("something happened while reading from input stream", th);
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (Throwable th) {
                logger.error("Can't clost input stream.", th);
            }
        }
    }

    public String createURLWithGetParams(String url, Map<String, Object> params, boolean dontAddQuestionMark) {
        StringBuilder urlWithParams = new StringBuilder().append(url);

        if (params != null && params.size() > 0) {
            if (!dontAddQuestionMark) {
                urlWithParams.append(START_PARAM_URL_DELIMETER);
            }
            boolean isMoreThanOne = false;
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (isMoreThanOne) {
                    urlWithParams.append(URL_PARAM_DELIMETER);
                }
                if (entry.getKey() == null) {
                    urlWithParams.append(entry.getValue());
                } else {
                    urlWithParams.append(entry.getKey()).append(URL_VALUE_DELIMETER).append(entry.getValue() instanceof String ? uRLEncode((String) entry.getValue()) : entry.getValue());
                }
                isMoreThanOne = true;
            }
        }
        return urlWithParams.toString();
    }

    public String uRLEncode(String input) {
        try {
            return URLEncoder.encode(input, "UTF-8");
        } catch (Throwable th) {
            logger.error("Can't encode string " + input);
            return input;
        }
    }

}
