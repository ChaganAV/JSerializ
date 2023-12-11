package ru.geekbrains.lesson3.task3;

import java.io.*;

public class Program {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student("Вася",25,4);
        try(FileOutputStream fileOutputStream = new FileOutputStream("student.bin")){
            try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
                objectOutputStream.writeObject(student);
                System.out.println("Объект student сериализовали");
            }
        }
        Student studentNew;
        try(FileInputStream fileInputStream = new FileInputStream("student.bin")){
            try(ObjectInputStream objectInputStream = new ObjectInputStream((fileInputStream))){
                studentNew = (Student) objectInputStream.readObject();
                System.out.println("десериализуем");
                System.out.println(studentNew);
                System.out.println("GPA не сериализовался, потому что transient");
            }
        }
    }
}
