package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CsvEntityManager csvEntityManager = new CsvEntityManager(User.class, "usuarios.csv");

        try {
            // Guardar varios usuarios con datos únicos
            User user1 = new User(1, "Juan", 25);
            User user2 = new User(2, "Ana", 30);
            User user3 = new User(3, "Luis", 22);
            User user4 = new User(4, "Maria", 27);
            User user5 = new User(5, "Carlos", 35);

            csvEntityManager.save(user1);
            csvEntityManager.save(user2);
            csvEntityManager.save(user3);
            csvEntityManager.save(user4);
            csvEntityManager.save(user5);

            // Leer todos los usuarios y mostrarlos en consola
            List<User> users = csvEntityManager.findAll();
            users.forEach(u -> System.out.println("ID: " + u.getId() + ", Nombre: " + u.getName() + ", Edad: " + u.getAge()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
