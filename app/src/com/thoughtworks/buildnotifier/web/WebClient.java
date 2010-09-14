package com.thoughtworks.buildnotifier.web;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.thoughtworks.buildnotifier.domain.Constants;
import com.thoughtworks.buildnotifier.domain.Pipeline;
import com.thoughtworks.buildnotifier.domain.Pipelines;
import com.thoughtworks.buildnotifier.exceptions.ServerUnreachableException;
import com.thoughtworks.buildnotifier.exceptions.XMLParseException;
import com.thoughtworks.buildnotifier.xml.FeedParser;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class WebClient {
    private String server;
    private HttpClient httpClient;

    public WebClient(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.APPLICATION_KEY, 0);
        server = preferences.getString(Constants.SERVER_KEY, "10.0.2.2:4567");
        httpClient = new DefaultHttpClient();

    }

    public Pipelines getStatus() throws ServerUnreachableException, XMLParseException {
        try {
            String cctray = getResponseXML();
            Log.d("client", cctray);
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
        return Http.get("http://" + server + "/cctray.xml").use(httpClient).asString();
    }
}
