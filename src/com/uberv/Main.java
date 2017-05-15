package com.uberv;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;

public class Main {

    public static void main(String[] args) {
        Employee e = new Employee();
        e.name = "Reyan Ali";
        e.address = "Phokka Kuan, Ambehta Peer";
        e.SSN = 11122333;
        e.number = 101;

        System.out.println("Employee:");
        System.out.println(e);

        // Save to file
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("employee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in employee.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }

        e = null;
        try {
            FileInputStream fileIn = new FileInputStream("employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Employee) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }

        // Deserialized's employee will be default value as it is transient e.g. was not serialized
        System.out.println("Deserialized Employee:");
        System.out.println(e);

        // Save to byte array and String
        String serializedEmployee = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(baos);
            out.writeObject(e);
            out.close();
            serializedEmployee = Base64.getEncoder().encodeToString(baos.toByteArray());
            System.out.println("Serialized base64 string:\n" + serializedEmployee);
            String byteStr = Arrays.toString(baos.toByteArray());
            System.out.println("Raw byte string:\n" + byteStr);
        } catch (IOException i) {
            i.printStackTrace();
        }

        // Deserialize from base64 string
        String encoded;
        try {
            byte[] data = Base64.getDecoder().decode(serializedEmployee);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            Employee obj = (Employee) ois.readObject();
            System.out.println("Deserialized from string:\n"+obj);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
