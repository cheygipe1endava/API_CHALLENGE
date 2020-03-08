package builders;

import java.net.MalformedURLException;
import java.net.URL;


public class URLBuilder {

    private String baseUrl;
    private String path;
    private String query;

    public URLBuilder() {

    }

    public URLBuilder addDomain(String domain) {
        this.baseUrl = domain;
        return this;
    }

    public URLBuilder addPathStep(String step) {
        this.path = step;
        return this;
    }

    public URLBuilder addQuery(String query) {
        this.query = query;
        return this;
    }

    public URL build() {
        try {
            return new URL(baseUrl + "/" + String.join("/", path)+ "?" + "api_key=3fb4c73306abfe5787339b5dba7276ba");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

