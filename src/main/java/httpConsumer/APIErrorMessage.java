package httpConsumer;

/**
 * Created by Lucas on 13/09/2016.
 */
public class APIErrorMessage {
    private String category;
    private String message;

    public APIErrorMessage(String category, String message) {
        this.category = category;
        this.message = message;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
