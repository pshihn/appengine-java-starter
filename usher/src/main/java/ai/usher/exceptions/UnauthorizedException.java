package ai.usher.exceptions;

import javax.servlet.http.HttpServletResponse;

public class UnauthorizedException extends Xception {
    private static final long serialVersionUID = 1L;

    public UnauthorizedException(String message, Throwable cause) {
        super(HttpServletResponse.SC_UNAUTHORIZED, message, cause);
    }

    public UnauthorizedException(String message) {
        super(HttpServletResponse.SC_UNAUTHORIZED, message);
    }
}
