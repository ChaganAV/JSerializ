package ru.geekbrains.lesson3.task3;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentList {
    public static final String FILE_JSON = "students.json";
    public static final String FILE_BIN = "students.bin";
    public static final String FILE_XML = "students.xml";

    // для JSON
    private static final ObjectMapper objectMapper = new ObjectMapper();
    // для XML
    private static final XmlMapper xmlMapper = new XmlMapper();
    public static void saveToFile(String filename, List<Student> students){
        try{
            if(filename.endsWith(".json")){
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(filename),students);
            }else if(filename.endsWith(".bin")){
                try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                        new FileOutputStream(filename))){
                    objectOutputStream.writeObject(students);
                }
            }else if(filename.endsWith(".xml")){
                xmlMapper.writeValue(new File(filename),students);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static List<Student> loadFromFile(String filename){
        List<Student> students = new ArrayList<>();
        File file = new File(filename);
        if(file.exists()){
            try{
                if(filename.endsWith(".json")){
                    students = objectMapper.readValue(file,
                            objectMapper.getTypeFactory().constructCollectionType(List.class,Student.class));
                }else if(filename.endsWith(".bin")){
                    try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                        students = (List<Student>) objectInputStream.readObject();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }else if(filename.endsWith(".xml")){
                    students = xmlMapper.readValue(file,
                            xmlMapper.getTypeFactory().constructCollectionType(List.class,Student.class));
                }
            } catch (StreamReadException e) {
                throw new RuntimeException(e);
            } catch (DatabindException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return students;
    }
}
