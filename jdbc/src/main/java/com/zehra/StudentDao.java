package com.zehra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public StudentDao(int int1, String string, String string2, int int2) {
    }

    public StudentDao() {
    }

    public List<StudentDao> getStudent() {
        Connection connection = DBUtil.getConnection();
        List<StudentDao> listStudents = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from student");

            while (resultSet.next()) {
                new StudentDao(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
            try {
                if (statement != null)
                    statement.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        return listStudents;

    }

    public void addStudent(Student student) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("insert into student(firstName,lastName,class) values (?,?,?)");
            preparedStatement.setString(1, student.getfirstName());
            preparedStatement.setString(2, student.getlastName());
            preparedStatement.setInt(3, student.getclass());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    int getclass() {
        return 0;
    }

    public void updateStudent(Student student) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection
                    .prepareStatement("update student set firstName=?,lastName=?,class=? where id=?");
            preparedStatement.setString(1, student.getfirstName());
            preparedStatement.setString(2, student.getlastName());
            preparedStatement.setInt(3, student.getclass());
            preparedStatement.setInt(4, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    public void deleteStudent(Student student) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("delete from student where id=?");
            preparedStatement.setInt(1, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public StudentDao getStudent(int id) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StudentDao student = null;
        try {
            preparedStatement = connection
                    .prepareStatement("select * from student where id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = new StudentDao(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return student;

    }

    public String getfirstName() {
        return null;
    }

    public String getlastName() {
        return null;
    }

}
