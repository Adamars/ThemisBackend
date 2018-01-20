package com.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class SavedRequestAwareAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private RequestCache requestCache = new HttpSessionRequestCache();

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication)
      throws ServletException, IOException {

    SavedRequest savedRequest
        = requestCache.getRequest(request, response);

    String targetUrlParam = getTargetUrlParameter();
    if (savedRequest != null && (isAlwaysUseDefaultTargetUrl() || areUrlParamAccordingly(request, targetUrlParam))) {
      requestCache.removeRequest(request, response);
    }

    clearAuthenticationAttributes(request);
  }

  private boolean areUrlParamAccordingly(HttpServletRequest request, String targetUrlParam) {
    return targetUrlParam != null
        && StringUtils.hasText(request.getParameter(targetUrlParam));
  }

  public void setRequestCache(RequestCache requestCache) {
    this.requestCache = requestCache;
  }
}
