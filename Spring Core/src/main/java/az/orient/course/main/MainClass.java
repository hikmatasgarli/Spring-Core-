//Spring Application 2 formada yaradilir
// 1 - Anotation Base
// 2 - Xml Configuration Base

package az.orient.course.main;

import az.orient.course.dao.StudentDao;
import az.orient.course.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        StudentDao studentDao = (StudentDao) applicationContext.getBean("studentDao");


        try {
            switch (scanner.next()) {
                case "view":
                    List<Student> studentList = studentDao.getStudentList();
                    for (Student student : studentList) {

                        System.out.println(student.getId()+" -- "+student.getName()+" -- "+student.getAddress()+" -- "+student.getDob());
                    }
                    break;
                case "get":
                    Student student = studentDao.getStudentById(scanner.nextLong());
                  if(student == null) {
                      System.out.println("Student Yoxdur");
                      return;
                  }
                    System.out.println(student.getName());
                    System.out.println(student.getAddress());
                    System.out.println(student.getId());
                    System.out.println(student.getDob());
                    break;
                case "add":
                    Student st = new Student();
                    st.setName(scanner.next());
                    st.setSurname(scanner.next());
                    st.setAddress(scanner.next());
                    studentDao.addStudent(st);
                    System.out.println("success");
                    break;
                case "count":
                    Integer studentCount = studentDao.studentCount();
                    System.out.println("Student Count is " + studentCount);
                    break;
                default:
                    System.out.println("Invalid method!");
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        main(args);
    }
}
