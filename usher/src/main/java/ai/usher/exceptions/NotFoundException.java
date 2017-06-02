package ai.usher.exceptions;

import javax.servlet.http.HttpServletResponse;

public class NotFoundException extends Xception {
    private static final long serialVersionUID = 1L;

    public NotFoundException(String message, Throwable cause) {
        super(HttpServletResponse.SC_NOT_FOUND, message, cause);
    }

    public NotFoundException(String message) {
        super(HttpServletResponse.SC_NOT_FOUND, message);
    }
}
