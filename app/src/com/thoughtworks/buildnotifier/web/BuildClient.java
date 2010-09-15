package com.thoughtworks.buildnotifier.web;

import android.content.Context;
import android.util.Log;
import com.thoughtworks.buildnotifier.model.domain.Pipeline;
import com.thoughtworks.buildnotifier.model.domain.Pipelines;
import com.thoughtworks.buildnotifier.xml.FeedParser;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class BuildClient {
    private String server;
    private HttpClient httpClient;

    public BuildClient(Context context) {
//        SharedPreferences preferences = context.getSharedPreferences(Constants.APPLICATION_KEY, 0);
//        server = preferences.getString(Constants.SERVER_KEY, "10.0.2.2:4567");
        server = "10.0.2.2:4567";
        httpClient = new DefaultHttpClient();

    }

    public Pipelines getStatus() {
        try {
            String cctray = getResponseXML();
            FeedParser feedParser = new FeedParser();
            Log.d("client", "parsing xml");
            return feedParser.parse(cctray);

        } catch (Exception e) {
            Log.e("BuildClient", "Exception", e);
            return new Pipelines();
        }
    }

    public Pipeline getStatusOf(String pipelineName) {
        Pipelines pipelines = getStatus();
        return pipelines.find(pipelineName);
    }

    private String getResponseXML() throws IOException {
        return Http.get("http://" + server + "/cctray.xml").use(httpClient).asString();
    }

    public boolean isFailing() {
        Pipelines pipelines = getStatus();
        return pipelines.isFailing();
    }
}
