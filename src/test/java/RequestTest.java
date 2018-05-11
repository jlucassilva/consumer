import httpConsumer.APIRequest;
import httpConsumer.HttpConsumer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import httpConsumer.util.GsonMapper;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Lucas on 10/07/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class RequestTest {

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
    public void deveEnviarGet() throws Exception {
        apiRequest.executeGet("httpbin.org/get");
        Assert.assertTrue(apiRequest.sucessful());
    }

    @Test
    public void deveEnviarPost() throws Exception {
        Model model = new Model(10L, "Lucas");
        apiRequest.executePost("httpbin.org/post", model);

        assertTrue(apiRequest.sucessful());
        assertNotNull(apiRequest.getJsonResult());

    }

    @Test
    public void deveEnviarPut() throws IOException {
        Model model = new Model(10L, "Lucas");
        apiRequest.executePut("httpbin.org/put", model);

        assertTrue(apiRequest.sucessful());
        assertNotNull(apiRequest.getJsonResult());


    }
}
