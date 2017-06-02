package ai.usher;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import ai.usher.model.Account;
import ai.usher.utils.Config;
import ai.usher.utils.StringHelper;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ExecutionContext context = new ExecutionContext();
        if (request instanceof HttpServletRequest) {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            Cookie[] cookies = servletRequest.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (StringHelper.areStringsEqualIgnoreCase(Config.COOKIE_SESSION, c.getName())) {
                        String accountId = c.getValue();
                        try {
                            long aid = Long.parseLong(accountId);
                            Account account = Account.get(aid);
                            if (account != null) {
                                context.setAccount(account);
                            }
                        } catch (Exception e) {

                        }
                    }
                }
            }

            //if (response instanceof HttpServletResponse) {
            //    HttpServletResponse servletResponse = (HttpServletResponse) response;
            //    String origin = servletRequest.getHeader("Origin");
            //    if (StringHelper.isNotBlank(origin) && (origin.startsWith("http://127.0.0.1") || origin.startsWith("http://localhost"))) {
            //        servletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE");
            //        servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            //        servletResponse.setHeader("Access-Control-Allow-Origin", origin);
            //    }
            //}
        }
        request.setAttribute(Config.EXECUTION_CONTEXT, context);

        chain.doFilter(request, response);
    }
}
