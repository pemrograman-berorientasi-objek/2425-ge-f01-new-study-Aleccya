package pbo;

import java.util.Scanner;

/**
 * 12S22002 - Fernando Silitonga
 * 12S22044 - Gracia Pardede
 */

import javax.persistence.*;
import pbo.model.Executor;

public class App {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main(String[] _args) {
        entityManagerFactory = Persistence.createEntityManagerFactory("study_plan_pu");
        entityManager = entityManagerFactory.createEntityManager();
        
        Executor executor = new Executor(entityManager);
        executor.cleanUpTables();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String input = sc.nextLine();
            if(input.equals("---")){
                break;
            }else{
                String data[] = input.split("#");
                switch (data[0]){
                    case "course-add":
                        executor.addCourse(data);
                        break;
                    case "course-show-all":
                        executor.showAllCourses();
                        break;
                    case "student-add":
                        executor.addStudent(data);
                        break;
                    case "student-show":
                        executor.showStudentDetail(data);
                        break;
                    case "student-show-all":
                        executor.showAllStudents();
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            }
        }
        sc.close();
        entityManager.close();
        entityManagerFactory.close();
    }
}