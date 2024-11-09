package com.dine.interceptor;

import com.dine.constant.JwtClaimsConstant;
import com.dine.context.BaseContext;
import com.dine.properties.JwtProperties;
import com.dine.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor for JWT validation
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * Validate JWT
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Check if the current intercepted request is a Controller method or other resources
        if (!(handler instanceof HandlerMethod)) {
            // If the current interception is not a dynamic method, allow the request to proceed
            return true;
        }

        // 1. Get the token from the request header
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        // 2. Validate the token
        try {
            log.info("JWT validation: {}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            BaseContext.setCurrentId(empId);
            log.info("Current employee ID: {}", empId);
            // 3. If validation passes, allow the request to proceed
            return true;
        } catch (Exception ex) {
            // 4. If validation fails, respond with a 401 status code
            response.setStatus(401);
            return false;
        }
    }
}
