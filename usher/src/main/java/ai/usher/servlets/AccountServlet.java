package ai.usher.servlets;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;

import ai.usher.ExecutionContext;
import ai.usher.exceptions.BadRequestException;
import ai.usher.exceptions.UnauthorizedException;
import ai.usher.exceptions.Xception;
import ai.usher.model.Account;
import ai.usher.utils.Config;
import ai.usher.utils.StringHelper;

public class AccountServlet extends BaseServlet {
    private static final long serialVersionUID = -4850038776115356585L;
    private final Gson gson = new GsonBuilder().create();

    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws Xception, IOException {
        ExecutionContext context = (ExecutionContext) request.getAttribute(Config.EXECUTION_CONTEXT);
        Account account = context.getAccount();
        if (account == null) {
            throw new UnauthorizedException("No user logged in.");
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF8");
        gson.toJson(account, response.getWriter());
    }

    @Override
    public void handlePost(HttpServletRequest request, HttpServletResponse response) throws Xception, IOException {
        if (StringHelper.areStringsEqualIgnoreCase(request.getPathInfo(), "/signout")) {
            Cookie cookie = new Cookie(Config.COOKIE_SESSION, "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        } else {
            throw new BadRequestException("Invalid request");
        }
    }

}
