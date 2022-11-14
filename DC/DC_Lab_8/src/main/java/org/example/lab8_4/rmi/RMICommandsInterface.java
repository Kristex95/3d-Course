package org.example.lab8_4.rmi;

import org.example.common.Group;
import org.example.common.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMICommandsInterface extends Remote {

    void createNewGroup(Group group) throws RemoteException;

    void createNewStudent(Student student) throws RemoteException;

    void deleteGroup(Integer id) throws RemoteException;

    void deleteStudent(Integer id) throws RemoteException;

    void updateGroup(Group group) throws RemoteException;

    void updateStudent(Student student) throws RemoteException;

    Group findGroupById(Integer id) throws RemoteException;

    Student findStudentById(Integer id) throws RemoteException;

    ArrayList<Student> findAllStudentsWithGroupId(Integer id) throws RemoteException;

    ArrayList<Group> findAllGroups() throws RemoteException;

    void connectToDataBase() throws RemoteException;

    public void closeConnection() throws RemoteException;
}