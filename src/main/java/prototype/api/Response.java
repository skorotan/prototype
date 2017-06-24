package prototype.api;

/**
 * Response
 */
public class Response {

    /**
     * Status
     */
    private String status;

    /**
     * Message body
     */
    private Object message;

    /**
     * Constructor
     * @param status status
     * @param message message
     */
    protected Response(String status, Object message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public Object getMessage() {
        return message;
    }

}
