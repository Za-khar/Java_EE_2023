package servlet;

import dao.StudentDAO;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-student")
public class StudentAddServlet extends HttpServlet {

    private StudentDAO studentDAO = StudentDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("students", studentDAO.getAll());
        request.getRequestDispatcher(request.getContextPath() + "add-student.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(request.getParameter("name").length() > 0 && request.getParameter("surname").length() > 0){
            Student student = new Student(request.getParameter("name"),
                    request.getParameter("surname"),
                    request.getParameter("age"),
                    request.getParameter("email"),
                    request.getParameter("group"),
                    request.getParameter("faculty"));
            studentDAO.save(student);
        }
        response.sendRedirect(request.getContextPath() + "/add-student");
    }
}
