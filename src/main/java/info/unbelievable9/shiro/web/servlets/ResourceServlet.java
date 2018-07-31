package info.unbelievable9.shiro.web.servlets;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on : 2018/7/31
 * Author     : Unbelievable9
 **/
@WebServlet(name = "resourceServlet", urlPatterns = "/resource")
public class ResourceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated()) {
            req.getRequestDispatcher("/WEB-INF/jsp/resource.jsp").forward(req, resp);
        }
    }
}
