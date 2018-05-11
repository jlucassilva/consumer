import httpConsumer.APIRequest;
import httpConsumer.HttpConsumer;
import httpConsumer.util.GsonMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import util.JsonFile;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class JsonFileTest {

    private GsonMapper gson;
    private APIRequest apiRequest;
    private HttpConsumer httpClient;

    @Before
    public void setUp() throws Exception {

        gson = new GsonMapper();
        httpClient = new HttpConsumer(gson);
        apiRequest = new APIRequest(gson, httpClient);
    }


    @Test
    public void readFromFile() throws IOException {
        String str = "";
        for (String s : JsonFile.readFromFile("test.json")) {
            str = str.concat(s);
        }
        System.out.println(str);
        assertNotNull(str);
    }

    @Test
    public void writeJsonInFile() throws IOException {
        Model model = new Model(10L, "Lucas");
        apiRequest.executePut("httpbin.org/put", model);

        JsonFile.writeJsonInFile("", "test.json", apiRequest.getJsonResult());
    }
}