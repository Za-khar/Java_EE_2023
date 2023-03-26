import configuration.MySpringConfiguration;
import entity.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/add-student")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(request.getContextPath()).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MySpringConfiguration.class);
        Student newStudent = context.getBean(Student.class);

        HttpSession session = request.getSession();
        List<Student> studentList = (List<Student>) session.getAttribute("students");

        if(studentList == null){
            studentList = new ArrayList<Student>();
        }

        if(request.getParameter("name").length() > 0 && request.getParameter("surname").length() > 0){
            newStudent = new Student(request.getParameter("name"),
                    request.getParameter("surname"),
                    request.getParameter("age"),
                    request.getParameter("email"),
                    request.getParameter("group"),
                    request.getParameter("faculty"));
        }
        studentList.add(newStudent);
        session.setAttribute("students", studentList);
        response.sendRedirect(request.getContextPath() + "/add-student");
    }
}
