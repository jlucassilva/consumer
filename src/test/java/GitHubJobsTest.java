import httpConsumer.APIRequest;
import httpConsumer.APIResultException;
import httpConsumer.HttpConsumer;
import httpConsumer.util.GsonMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import util.Job;
import util.JsonFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class GitHubJobsTest {

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
    public void carregaTodosOsJobs() throws IOException, APIResultException {
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            jobs.addAll(apiRequest.executeGet("https://jobs.github.com/positions.json?search=code&page=" + i).getResultListAs(Job.class));
        }
        JsonFile.writeJsonInFile("", "gitJobs.json", gson.toJson(jobs));
        assertNotNull(jobs);
    }

    @Test
    public void readJsonInFile() throws IOException {
        String str = "";
        for (String s : JsonFile.readFromFile("gitJobs.json")) {
            str = str.concat(s);
        }
        String aux = new String();
        aux = new String(str.getBytes(), StandardCharsets.UTF_8);
        aux = new String(str.getBytes(), StandardCharsets.ISO_8859_1);
        aux = new String(str.getBytes(), StandardCharsets.US_ASCII);
        aux = new String(str.getBytes(), StandardCharsets.UTF_16);
        aux = new String(str.getBytes(), StandardCharsets.UTF_16LE);
        aux = new String(str.getBytes(), StandardCharsets.UTF_16BE);

        assertNotNull(str);

        assertNotNull(str);
    }
}