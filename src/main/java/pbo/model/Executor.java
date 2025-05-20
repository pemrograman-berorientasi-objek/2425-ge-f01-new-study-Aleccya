package pbo.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.*;

public class Executor {
    private EntityManager entityManager;

    public Executor(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

     public void cleanUpTables() {
        String[] sql = {
            "DELETE FROM Student",
            "DELETE FROM Course "
        };
        entityManager.getTransaction().begin();
        for(String s : sql){
            entityManager.createQuery(s).executeUpdate();
        }
        entityManager.getTransaction().commit();  
    }

    public void addStudent(String[] data) {
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, data[1]);
        if (student == null) {
            student = new Student(data[1], data[2], data[3]);
            entityManager.persist(student);
        }
        entityManager.getTransaction().commit();
    }

    public void showAllStudents() {
        String sql = "SELECT s FROM Student s ORDER BY s.nim";
        List<Student> students = entityManager.createQuery(sql, Student.class).getResultList();
        for (Student s : students) {
            System.out.println(s.getNim() + "|" + s.getName() + "|" + s.getProdi());
        }
    }

    public void addCourse(String[] data) {
        entityManager.getTransaction().begin();
        Course course = entityManager.find(Course.class, data[1]);
        if (course == null) {
            course = new Course(data[1], data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]));
            entityManager.persist(course);
        }
        entityManager.getTransaction().commit();
    }

    public void showAllCourses() {
        String sql = "SELECT c FROM Course c ORDER BY c.semester, c.kode";
        List<Course> courses = entityManager.createQuery(sql, Course.class).getResultList();
        for (Course c : courses) {
            System.out.println(c.getKode() + "|" + c.getMatkul() + "|" + c.getSemester() + "|" + c.getKredit());
        }
    }

    public void enroll(String[] data) {
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, data[1]);
        Course course = entityManager.find(Course.class, data[2]);
        if (student != null && course != null) {
            String sql = "SELECT e FROM Enrollment e WHERE e.student.nim = :nim AND e.course.kode = :kode";
            List<Enrollment> enrollments = entityManager.createQuery(sql, Enrollment.class)
                .setParameter("nim", data[1])
                .setParameter("kode", data[2])
                .getResultList();
            if (enrollments.isEmpty()) {
                Enrollment enrollment = new Enrollment(student, course);
                entityManager.persist(enrollment);
            }
        }
        entityManager.getTransaction().commit();
    }

    public void showStudentDetail(String[] data) {
        Student student = entityManager.find(Student.class, data[1]);
        if (student != null) {
            System.out.println(student.getNim() + "|" + student.getName() + "|" + student.getProdi());
            String sql = "SELECT e FROM Enrollment e WHERE e.student.nim = :nim";
            List<Enrollment> enrollments = entityManager.createQuery(sql, Enrollment.class)
                .setParameter("nim", data[1])
                .getResultList();
            
            Collections.sort(enrollments, Comparator
                .comparing((Enrollment e) -> e.getCourse().getSemester())
                .thenComparing(e -> e.getCourse().getKode()));
            for (Enrollment e : enrollments) {
                Course c = e.getCourse();
                System.out.println(c.getKode() + "|" + c.getMatkul() + "|" + c.getSemester() + "|" + c.getKredit());

            
            }
        }
    }
}