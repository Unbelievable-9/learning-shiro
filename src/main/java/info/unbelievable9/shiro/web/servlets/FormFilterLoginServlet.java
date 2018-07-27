package info.unbelievable9.shiro.web.servlets;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on : 2018/7/27
 * Author     : Unbelievable9
 **/
@WebServlet(name = "formFilterLoginServlet", urlPatterns = "/formFilterLogin")
public class FormFilterLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errorClassName = (String) req.getAttribute("shiroLoginFailure");

        if (UnknownAccountException.class.getName().equals(errorClassName) ||
                IncorrectCredentialsException.class.getName().equals(errorClassName)) {
            req.setAttribute("error", "Account/Password Wrong");
        } else if (errorClassName != null) {
            req.setAttribute("error", "Authentication Error");
        }

        req.getRequestDispatcher("/WEB-INF/jsp/formFilterLogin.jsp").forward(req,resp);
    }
}
