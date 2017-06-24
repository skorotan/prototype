package prototype.api;

/**
 * Response ok
 */
public class ResponseOk extends Response {

    private final static String OK = "status ok";

    /**
     * Constructor for responce
     * @param statusBody message that should be shown.
     */
    public ResponseOk(Object statusBody) {
        super(OK, statusBody);
    }
}
