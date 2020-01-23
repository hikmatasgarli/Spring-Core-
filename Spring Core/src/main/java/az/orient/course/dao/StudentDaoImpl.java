package az.orient.course.dao;

import az.orient.course.model.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private DataSource dataSource;

    public StudentDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> getStudentList() throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM STUDENT";
        List<Student> studentList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Student.class));
        return studentList;
    }

    public Student getStudentById(Long studentId) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM STUDENT WHERE ID = ?";
        List<Student> studentList = jdbcTemplate.query(sql, new Object[] {studentId}, new BeanPropertyRowMapper(Student.class));

        if (studentList.size() > 0 ) {
            return studentList.get(0);
        }
        return null;
    }

    public void addStudent(Student student) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO STUDENT (NAME,SURNAME,ADDRESS,DOB) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql,new Object[] {student.getName(),student.getSurname(),student.getAddress(),student.getDob()});

    }

    @Override
    public Integer studentCount() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT count(*) FROM STUDENT";
        Integer studentCount = jdbcTemplate.queryForObject(sql,Integer.class);
        return studentCount;
    }
}
