package sumdu.edu.ua.studentweb.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sumdu.edu.ua.studentweb.Support.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
public class StudentController {
    List<Student> students;
    ApplicationContext factory;
    Student student;

    @ModelAttribute
    public void modelData(Model m) {
        if (students == null) students = new LinkedList<>();
        factory = new ClassPathXmlApplicationContext("/SpringXMLConfig.xml");
        student = new Student();
    }

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping("StudentAdd")
    public String addStudent(HttpServletRequest request, Model m) {
        HttpSession session = request.getSession();
        if (request.getParameter("name") != "" || request.getParameter("surname") != "") {
            Student student = new Student();
            student.setName(request.getParameter("name"));
            student.setSurname(request.getParameter("surname"));
            student.setAge(request.getParameter("age"));
            student.setEmail(request.getParameter("email"));
            student.setGroup(request.getParameter("group"));
            student.setFaculty(request.getParameter("faculty"));
            students.add(student);
        }
        m.addAttribute("students", students);
        return "home";
    }

    @GetMapping("filterFaculty")
    public String filterF(@RequestParam("filter") String faculty, Model m) {
        List<Student> filtered = new LinkedList<>();
        for (Student st : students) {
            if (st.getFaculty().equalsIgnoreCase(faculty)) filtered.add(st);
        }
        m.addAttribute("filter", faculty);
        m.addAttribute("filtered", filtered);
        return "listed";
    }

    @GetMapping("filterGroup")
    public String filterG(@RequestParam("filter") String group, Model m) {
        List<Student> filtered = new LinkedList<>();
        for (Student st : students) {
            if (st.getGroup().equalsIgnoreCase(group)) filtered.add(st);
        }
        m.addAttribute("filter", group);
        m.addAttribute("filtered", filtered);
        return "listed";
    }

    @GetMapping("filterFacultyGroup")
    public String filterFG(@RequestParam("filter1") String group, @RequestParam("filter2") String faculty,  Model m) {
        List<Student> filtered = new LinkedList<>();
        for (Student st : students) {
            if (st.getFaculty().equalsIgnoreCase(faculty) &&
            st.getGroup().equalsIgnoreCase(group)) filtered.add(st);
        }
        m.addAttribute("filter", group + " " + faculty);
        m.addAttribute("filtered", filtered);
        return "listed";
    }
}
