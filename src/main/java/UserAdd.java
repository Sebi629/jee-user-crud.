import utils.DbUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/useradd")
public class UserAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/users/add.jsp")
                .forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        user.setUserName(request.getParameter("userName"));
        user.setEmail(request.getParameter("userEmail"));
        user.setPassword(request.getParameter("userPassword"));
        UserDao userDao = new UserDao();
        userDao.create(user);
        response.sendRedirect(request.getContextPath() + "/user/list");



    }
}