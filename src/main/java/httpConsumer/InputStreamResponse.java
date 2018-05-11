package httpConsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Created by Lucas on 13/09/2016.
 */
public class InputStreamResponse {


    public static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return convertStream(inputStream, bufferedReader);
    }

    public static String convertStreamToStringISO(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
        return convertStream(inputStream, bufferedReader);
    }

    private static String convertStream(InputStream inputStream, BufferedReader bufferedReader) {
        String result = new String();
        try {
            result = convertedStream(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String convertedStream(BufferedReader bufferedReader) throws IOException {
        String line;
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }
        return result;
    }


}
