package httpConsumer;

import httpConsumer.util.GsonMapper;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Lucas on 19/09/2016.
 */

public class HttpConsumer implements Serializable {


    private final String DEVELOPMENT = "http://localhost:8080";
    private final String PRODUCTION = "http://www.taquioninovacao.com.br/lifesensing";
    private final String APTAMEROS = "http://www.taquioninovacao.com.br/aptameros";
    private String urlPath = "http://";
    private final String GET = "GET";
    private final String PUT = "PUT";
    private final String POST = "POST";

    private GsonMapper gson;
    private HttpURLConnection httpURLConnection;

    public HttpConsumer() {
    }

    @Inject
    public HttpConsumer(GsonMapper gson) {
        this.gson = gson;
    }

    public String get(String uri) throws IOException, APIRequestException {
        createConnection(uri, GET);
        return getResultAsJson();
    }

    public <T> String post(String uri, T o) throws IOException, APIRequestException {
        return sendRequest(uri, o, POST);
    }

    public <T> String put(String uri, T o) throws IOException, APIRequestException {
        return sendRequest(uri, o, PUT);
    }

    private <T> String sendRequest(String uri, T o, String requestMethod) throws APIRequestException, IOException {
        createConnection(uri, requestMethod);
        flushJson(getJson(o));
        return getResultAsJson();
    }

    private HttpURLConnection createConnection(String uri, String requestMethod) {
        httpURLConnection = null;
        try {
            URL url = new URL(urlPath.concat(uri));
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpURLConnection;
    }

    private void flushJson(String json) {
        try {
            OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
            wr.write(json);
            wr.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getResultAsJson() throws APIRequestException, IOException {
        InputStream inputStream = getInputStream();
        String result = new String();
        try {
            result = InputStreamResponse.convertStreamToString(inputStream);
        } finally {
            closeInputStream(inputStream);
        }
        return result;
    }

    private InputStream getInputStream() throws APIRequestException, IOException {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException ioException) {
            if (receivedErrorHasResponse()) {
                throw new APIRequestException(httpURLConnection.getErrorStream());
            } else {
                throw ioException;
            }
        }
    }

    private void closeInputStream(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean receivedErrorHasResponse() {
        if (httpURLConnection.getErrorStream() != null) {
            if (httpURLConnection.getContentType().contains("application/json")) {
                return true;
            }
        }
        return false;
    }

    private String getJson(Object object) {
        return gson.toJson(object);

    }
}
