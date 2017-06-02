package ai.usher.servlets;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;

import ai.usher.exceptions.Xception;
import ai.usher.model.Account;
import ai.usher.model.Accounts;
import ai.usher.model.oauth.GoogleUserInfo;
import ai.usher.utils.Config;
import ai.usher.utils.StringHelper;

public class GoogleServlet extends BaseServlet {
    private static final long serialVersionUID = 204220324644770141L;

    private static final String SECRET = "cVyNbrSqRdQL1jmxaf5HQHvg";
    private static final String CLIENT_ID = "1021536036746-ncka8rr2df3tk6uf1s98jdjccvtkn93d.apps.googleusercontent.com";
    private static final String SCOPE = "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email";
    private static final String USER_URL = "https://www.googleapis.com/oauth2/v3/userinfo";

    private final Gson gson = new GsonBuilder().create();

    @Override
    public void handleGet(HttpServletRequest request, HttpServletResponse response) throws Xception, IOException {
        final String callback = (new URL(request.getScheme(), request.getServerName(), request.getServerPort(), "/google/callback")).toString();
        if (StringHelper.areStringsEqualIgnoreCase(request.getPathInfo(), "/login")) {
            OAuthService oas = new ServiceBuilder().provider(Google2Api.class).apiKey(CLIENT_ID).apiSecret(SECRET).scope(SCOPE).callback(callback).build();
            String authorizationUrl = oas.getAuthorizationUrl(null);
            response.sendRedirect(authorizationUrl);
        } else if (StringHelper.areStringsEqualIgnoreCase(request.getPathInfo(), "/callback")) {
            String verifierCode = request.getParameter("code");
            if (!StringHelper.isBlank(verifierCode)) {
                OAuthService oas = new ServiceBuilder().provider(Google2Api.class).apiKey(CLIENT_ID).apiSecret(SECRET).scope(SCOPE).callback(callback).build();
                Token accessToken = oas.getAccessToken(null, new Verifier(verifierCode));
                OAuthRequest oreq = new OAuthRequest(Verb.GET, USER_URL);
                oas.signRequest(accessToken, oreq);
                Response oresp = oreq.send();
                int code = oresp.getCode();
                if (code >= 200 && code < 300) {
                    String responseBody = oresp.getBody();
                    if (StringHelper.isNotBlank(responseBody)) {
                        GoogleUserInfo profile = gson.fromJson(responseBody, GoogleUserInfo.class);
                        if (profile != null) {
                            if (StringHelper.isNotBlank(profile.getEmail())) {
                                Account account = Accounts.get().getOrCreateAccount(profile.getEmail(), profile.getName(), profile.getPicture(), null);
                                if (account == null) {
                                    response.sendError(500, "Failed to create account");
                                    return;
                                } else {
                                    Cookie cookie = new Cookie(Config.COOKIE_SESSION, String.valueOf(account.getId()));
                                    cookie.setMaxAge(365 * 24 * 60 * 60);
                                    response.addCookie(cookie);
                                    response.sendRedirect(Config.property(Config.PROP_BASE_URL) + "/#embed");
                                }
                            } else {
                                response.sendError(400, "No email address provided in the google profile");
                                return;
                            }
                        }
                    }
                }
            }

            response.sendRedirect(Config.property(Config.PROP_BASE_URL));
            return;
        }
    }
}
