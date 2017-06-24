package prototype.api;

/**
 * Response error
 */
public class ResponseError extends Response{

    private final static String ERROR = "error";

    /**
     * Constructor for responce
     * @param statusBody message that should be shown.
     */
    public ResponseError(Object statusBody) {
        super(ERROR, statusBody);
    }
}
