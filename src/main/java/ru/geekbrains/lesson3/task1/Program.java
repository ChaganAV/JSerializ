package ru.geekbrains.lesson3.task1;

import java.io.*;

public class Program {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UserData user = new UserData("Alex",52,"pass");
        try(FileOutputStream fileOutputStream = new FileOutputStream("userdata.bin")){
            try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
                objectOutputStream.writeObject(user);
                System.out.println("Объект UserData сериализован.");
            }
        }
        UserData userRead;
        try(FileInputStream fileInputStream= new FileInputStream("userdata.bin")){
            try(ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
                userRead = (UserData) objectInputStream.readObject();
                System.out.println("Объект UserData десериализован");
            }
        }
        System.out.println("Name: " + userRead.getName());
        System.out.println("Age: " + userRead.getAge());
        System.out.println("Password (должен быть null, так как transient): " + userRead.getPassword());

    }
}
