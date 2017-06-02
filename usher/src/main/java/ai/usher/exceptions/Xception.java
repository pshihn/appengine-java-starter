package ai.usher.exceptions;

public class Xception extends Exception {
    private static final long serialVersionUID = 4715080357896387641L;
    private final int code;

    public Xception(int code, String message) {
        super(message);
        this.code = code;
    }

    public Xception(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
