package github.personManager.exception;

public class CustomException extends RuntimeException {

    private int statusCode;

    public CustomException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

