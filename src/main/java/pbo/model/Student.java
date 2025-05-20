package pbo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "nim", nullable = false, length = 20)
    private String nim;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "prodi", nullable = false, length = 50)
    private String prodi;

    @ManyToMany
    @JoinTable(
        name = "enrollments",
        joinColumns = @JoinColumn(name = "student_nim", referencedColumnName = "nim"),
        inverseJoinColumns = @JoinColumn(name = "course_kode", referencedColumnName = "kode")
    )
    private List<Course> courses;

    public Student() {}

    public Student(String nim, String name, String prodi) {
        this.nim = nim;
        this.name = name;
        this.prodi = prodi;
    }

    
    public String getNim() {
        return nim;
    }

    public String getName() {
        return name;
    }

    public String getProdi() {
        return prodi;
    }

    public List<Course> getCourses() {
        return courses;
    }

  
    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return nim + "|" + name + "|" + prodi;
    }
}