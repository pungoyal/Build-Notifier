package com.thoughtworks.buildnotifier.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.thoughtworks.buildnotifier.domain.Constants;
import com.thoughtworks.buildnotifier.domain.Pipeline;
import com.thoughtworks.buildnotifier.domain.Pipelines;
import com.thoughtworks.buildnotifier.exceptions.ServerUnreachableException;
import com.thoughtworks.buildnotifier.exceptions.XMLParseException;
import com.thoughtworks.buildnotifier.xml.FeedParser;

public class WebClient {
	private String server;

	public WebClient(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(Constants.APPLICATION_KEY, 0);
		server = preferences.getString(Constants.SERVER_KEY, "10.0.2.2:4567");
	}

	public Pipelines getStatus() throws ServerUnreachableException, XMLParseException {
		try {
			String cctray = getResponseXML();
			FeedParser feedParser = new FeedParser();
			return feedParser.parse(cctray);

		} catch (IOException e) {
			throw new ServerUnreachableException(e, server);
		}
	}

	public Pipeline getStatusOf(String pipelineName) throws ServerUnreachableException, XMLParseException {
		Pipelines pipelines = getStatus();
		return pipelines.find(pipelineName);
	}

	private String getResponseXML() throws IOException {
		StringBuffer buffer = new StringBuffer();

		HttpURLConnection connection = null;
		URL u = new URL("http://" + server + "/cctray.xml");
		Log.d("Client", "URL - " + u.toString());
		connection = (HttpURLConnection) u.openConnection();
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);

		connection.connect();
		InputStream inputStream = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		String line = null;
		String line_separator = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			buffer.append(line + line_separator);
		}
		connection.disconnect();
		return buffer.toString();
	}
}
