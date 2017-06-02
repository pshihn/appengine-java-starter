package ai.usher.exceptions;

import javax.servlet.http.HttpServletResponse;

public class DuplicateException extends Xception {
    private static final long serialVersionUID = 1L;

    public DuplicateException(String message, Throwable cause) {
        super(HttpServletResponse.SC_CONFLICT, message, cause);
    }

    public DuplicateException(String message) {
        super(HttpServletResponse.SC_CONFLICT, message);
    }
}
