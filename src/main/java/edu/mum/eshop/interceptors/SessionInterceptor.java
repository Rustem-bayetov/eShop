package edu.mum.eshop.interceptors;

import edu.mum.eshop.Session;
import edu.mum.eshop.domain.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    HttpSession httpSession;

    @Autowired
    Session session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        httpSession.setAttribute("loggedInUser", session.getUser());
        return true;
    }
}
