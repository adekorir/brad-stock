package brad.util.sys;

public class BRADException extends Exception {

    private final long serialVersionUID = 28L;

    public BRADException() {
        super();
    }
    public BRADException(String message) {
        super(message);
    }
    public BRADException(String message, Throwable cause) {
        super(message, cause);
    }
}
