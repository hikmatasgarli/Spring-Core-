package az.orient.course.dao;

import az.orient.course.model.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getStudentList() throws Exception;

    Student getStudentById(Long studentId) throws Exception;

    void addStudent(Student student) throws Exception;

    Integer studentCount();
}
