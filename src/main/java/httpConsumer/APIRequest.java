package httpConsumer;

import httpConsumer.util.GsonMapper;

import javax.inject.Inject;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Lucas on 26/08/2015.
 */

public class APIRequest implements Serializable {

    public APIRequest() {
    }

    @Inject
    public APIRequest(GsonMapper gson, HttpConsumer apiConsumer) {
        this.gson = gson;
        this.apiConsumer = apiConsumer;
    }

    private GsonMapper gson;
    private Class resultType;
    private Boolean successful;
    private HttpConsumer apiConsumer;

    private String jsonResult;
    private List<APIErrorMessage> errorMessages;

    public APIRequest executeGet(String URI) throws IOException {
        try {
            jsonResult = apiConsumer.get(URI);
            successful = true;
        } catch (APIRequestException e) {
            handleFailure(e);
        }
        return this;
    }

    public <T> APIRequest executePut(String URI, T o) throws IOException {
        resultType = o.getClass();
        try {
            jsonResult = apiConsumer.put(URI, o);
            successful = true;
        } catch (APIRequestException e) {
            handleFailure(e);
        }
        return this;
    }

    public <T> APIRequest executePost(String URI, T o) throws IOException {
        resultType = o.getClass();
        try {
            jsonResult = apiConsumer.post(URI, o);
            successful = true;
        } catch (APIRequestException e) {
            handleFailure(e);
        }
        return this;
    }

    public <T> T getSingleResult() throws APIResultException {
        if (sucessful()) {
            return (T) gson.convertJson(jsonResult, resultType);
        } else {
            throw new APIResultException("A ultima requisição feita recebeu uma mensagem de erro como resposta." +
                    " Voce deve tratar a(s) mensagem(s) de erro.");
        }
    }

    public <T> List<T> getResultList() throws APIResultException {
        if (sucessful()) {
            return gson.convertJsonToList(jsonResult, resultType);
        } else {
            throw new APIResultException("A ultima requisição feita recebeu uma mensagem de erro como resposta." +
                    " Voce deve tratar a(s) mensagem(s) de erro.");
        }
    }

    public <T> T getSingleResultAs(Class<T> type) throws APIResultException {
        if (sucessful()) {
            return gson.convertJson(jsonResult, type);
        } else {
            throw new APIResultException("A ultima requisição feita recebeu uma mensagem de erro como resposta." +
                    " Voce deve tratar a(s) mensagem(s) de erro.");
        }
    }

    public <T> List<T> getResultListAs(Class<T> type) throws APIResultException {

        if (sucessful()) {
            return gson.convertJsonToList(jsonResult, type);
        } else {
            throw new APIResultException("A ultima requisição feita recebeu uma mensagem de erro como resposta." +
                    " Voce deve tratar a(s) mensagem(s) de erro.");
        }
    }

    private void handleFailure(APIRequestException e) {
        errorMessages = APIExceptionHandler.getErrorsFromAPIException(e);
        successful = false;
    }

    public Boolean sucessful() {
        return successful;
    }

    public List<APIErrorMessage> getErrorMessages() {
        return errorMessages;
    }

    public String getJsonResult() {
        return this.jsonResult;
    }
}
