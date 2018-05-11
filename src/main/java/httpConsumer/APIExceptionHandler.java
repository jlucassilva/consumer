package httpConsumer;


import httpConsumer.util.GsonMapper;

import javax.inject.Inject;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Lucas on 13/09/2016.
 */
public class APIExceptionHandler {


    private static GsonMapper gson;

    public APIExceptionHandler() {
    }

    @Inject
    public APIExceptionHandler(GsonMapper gson) {
        this.gson = gson;
    }

    public static List<APIErrorMessage> getErrorsFromAPIException(APIRequestException e) {
        return convertErrorStreamToList(e.getErrorStream());
    }

    private static List<APIErrorMessage> convertErrorStreamToList(InputStream errorStream) {
        GsonMapper gson = new GsonMapper();
        String errors = InputStreamResponse.convertStreamToStringISO(errorStream);
        System.out.println((errors));
        return gson.convertJsonToList(errors, APIErrorMessage.class);
    }
}
