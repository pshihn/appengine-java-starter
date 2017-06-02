package ai.usher.exceptions;

import javax.servlet.http.HttpServletResponse;

public class NotImplementedException extends Xception {
    private static final long serialVersionUID = 1L;

    public NotImplementedException(String message, Throwable cause) {
        super(HttpServletResponse.SC_NOT_IMPLEMENTED, message, cause);
    }

    public NotImplementedException(String message) {
        super(HttpServletResponse.SC_NOT_IMPLEMENTED, message);
    }

    public NotImplementedException() {
        super(HttpServletResponse.SC_NOT_IMPLEMENTED, "Method not implemented/supported");
    }

}
