package com.thoughtworks.buildnotifier.web;

import android.content.Context;
import android.util.Log;
import com.thoughtworks.buildnotifier.model.domain.Pipeline;
import com.thoughtworks.buildnotifier.model.domain.Pipelines;
import com.thoughtworks.buildnotifier.preferences.BuildNotifierPreferenceManager;
import com.thoughtworks.buildnotifier.xml.FeedParser;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class BuildClient {
    private String server;
    private HttpClient httpClient;

    public BuildClient(Context context) {
        server = BuildNotifierPreferenceManager.getServer(context);
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
