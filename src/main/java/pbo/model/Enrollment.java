package pbo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "enrollments")
@IdClass(EnrollmentId.class)
public class Enrollment implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "student_nim", referencedColumnName = "nim")
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_kode", referencedColumnName = "kode")
    private Course course;

    public Enrollment() {}

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }


    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

   
    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enrollment)) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(student, that.student) &&
               Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course);
    }
}