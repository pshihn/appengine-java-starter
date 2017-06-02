package ai.usher.exceptions;

import javax.servlet.http.HttpServletResponse;

public class BadRequestException extends Xception {
    private static final long serialVersionUID = 1L;

    public BadRequestException(String message, Throwable cause) {
        super(HttpServletResponse.SC_BAD_REQUEST, message, cause);
    }

    public BadRequestException(String message) {
        super(HttpServletResponse.SC_BAD_REQUEST, message);
    }

}
