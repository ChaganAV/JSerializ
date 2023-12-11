package ru.geekbrains.lesson3.task3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Вася",25,4));
        students.add(new Student("Маша",23,3));
        students.add(new Student("Миша",24,2));
        students.add(new Student("Вова",27,8));

        StudentList.saveToFile("Students.bin",students);
        StudentList.saveToFile("Students.json",students);
        StudentList.saveToFile("Students.xml",students);
        System.out.println("Сериализовали");
        System.out.println("=============");

        List<Student> studentsNew = StudentList.loadFromFile("students.json");
        //studentsNew.forEach(s -> System.out.println(s));
        for (Student student: studentsNew) {
            System.out.println(student);
        }
    }
}
