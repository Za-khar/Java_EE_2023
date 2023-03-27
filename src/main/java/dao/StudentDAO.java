package dao;

import connection.MyConnectionPool;
import entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static StudentDAO instance;
    private MyConnectionPool basicConnectionPool;

    private StudentDAO() {
        basicConnectionPool = MyConnectionPool.getInstance();
    }

    public static StudentDAO getInstance() {
        if (instance == null) {
            instance = new StudentDAO();
        }
        return instance;
    }

    public Student save(Student student) {
        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO student (name, surname, age, email, group, faculty) VALUES (?, ?, ?, ?, ?, ?)");

            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setString(3, student.getAge());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getGroup());
            statement.setString(6, student.getFaculty());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }

        return student;
    }

    public List<Student> getAll() {
        List<Student> studentList = new ArrayList<>();

        Connection connection = basicConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from student");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();

                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setAge(resultSet.getString("age"));
                student.setEmail(resultSet.getString("email"));
                student.setGroup(resultSet.getString("group"));
                student.setFaculty(resultSet.getString("faculty"));

                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            basicConnectionPool.releaseConnection(connection);
        }

        return studentList;
    }
}
