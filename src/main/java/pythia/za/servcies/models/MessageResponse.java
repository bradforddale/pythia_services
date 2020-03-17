package pythia.za.servcies.models;

public class MessageResponse {
    private String message;

    @Override
    public String toString() {
        return message;
    }

    public String getMessage() {
        return message;
    }

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse(Exception e) {
        this.message = e.getMessage();
    }
}
