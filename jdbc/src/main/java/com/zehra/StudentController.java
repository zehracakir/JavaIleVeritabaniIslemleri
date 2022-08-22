package com.zehra;

import java.util.List;

public class StudentController {
    

    private StudentDao StudentDao;

    public StudentController() {
        StudentDao = new StudentDao();
    }
    public List<com.zehra.StudentDao> getAllStudent(){
        return StudentDao.getStudent();
    }
    public void addStudent(Student student){
        StudentDao.addStudent(student);
    }
    public void updateStudent(Student student){
        StudentDao.updateStudent(student);
    }
    public void deleteStudent(Student student){
        StudentDao.deleteStudent(student);
    }
    public com.zehra.StudentDao getStudent(int id){
        return StudentDao.getStudent(id);
    }

}

