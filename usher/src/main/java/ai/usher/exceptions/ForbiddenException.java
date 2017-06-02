package ai.usher.exceptions;

import javax.servlet.http.HttpServletResponse;

public class ForbiddenException extends Xception {
    private static final long serialVersionUID = -1603521750371827664L;

    public ForbiddenException(String message, Throwable cause) {
        super(HttpServletResponse.SC_FORBIDDEN, message, cause);
    }

    public ForbiddenException(String message) {
        super(HttpServletResponse.SC_FORBIDDEN, message);
    }

}
