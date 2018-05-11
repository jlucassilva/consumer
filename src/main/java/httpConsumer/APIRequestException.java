package httpConsumer;

import java.io.InputStream;

/**
 * Created by Lucas on 13/09/2016.
 */
public class APIRequestException extends Exception{

    private InputStream errorStream;

    public APIRequestException(InputStream errorStream) {
        this.errorStream = errorStream;
    }

    public InputStream getErrorStream() {
        return errorStream;
    }

}
