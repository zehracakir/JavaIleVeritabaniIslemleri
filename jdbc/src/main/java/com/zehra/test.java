package com.zehra;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) {
        StudentController StudentController = new StudentController();
        // Veri ekleme
         Student student = new Student("Yusuf", "Dagdeviren", 3);
         Student student2 = new Student("Zehra", "Cakir", 3);
         StudentController.addStudent(student);
         StudentController.addStudent(student2);
        // Bütün verileri çekme
         List<StudentDao> list = new ArrayList<>();
         list = StudentController.getAllStudent();
         for(StudentDao e:list){
             System.out.println("Adi: "+e.getfirstName()+" Soyadi: "+e.getlastName()+" Sınıfı: "+e.getclass());
         }
        // Id ye göre Veri çekme
         int id = 6;
         StudentDao e = StudentController.getStudent(id);
         System.out.println("Adi: "+e.getfirstName()+" Soyadi: "+e.getlastName()+" Sınıfı: "+e.getclass());
        // Veri Silme
         Student Student1 = new Student(5,"Yusuf", "Dagdeviren", 3);
         StudentController.deleteStudent(Student1);
        // Veri updateleme
        Student Student3 = new Student(6,"Zehra", "Cakir", 4);
        StudentController.updateStudent(Student3);
    }
}
