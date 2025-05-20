package pbo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(name = "kode", nullable = false, length = 20)
    private String kode;

    @Column(name = "nama", nullable = false, length = 50)
    private String matkul;

    @Column(name = "semester", nullable = false)
    private int semester;

    @Column(name = "kredit", nullable = false)
    private int kredit;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    public Course() {}

    public Course(String kode, String matkul, int semester, int kredit) {
        this.kode = kode;
        this.matkul = matkul;
        this.semester = semester;
        this.kredit = kredit;
    }

    // GETTER
    public String getKode() {
        return kode;
    }

    public String getMatkul() {
        return matkul;
    }

    public int getSemester() {
        return semester;
    }

    public int getKredit() {
        return kredit;
    }

    public List<Student> getStudents() {
        return students;
    }

    // SETTER
    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setNama(String matkul) {
        this.matkul = matkul;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setKredit(int kredit) {
        this.kredit = kredit;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return kode + "|" + matkul + "|" + semester + "|" + kredit;
    }
}