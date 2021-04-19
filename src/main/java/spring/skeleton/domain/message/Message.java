package spring.skeleton.domain.message;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Message {

    private String id;

    @NotNull
    @Size(min = 1, max = 255)
    private String message;

    public Message(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
