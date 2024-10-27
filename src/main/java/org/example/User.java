package org.example;

public class User {

    @CsvColumn(name = "ID")
    private int id;

    @CsvColumn(name = "Nombre")
    private String name;

    @CsvColumn(name = "Edad")
    private int age;

    // Constructor sin argumentos (requerido para la creación mediante reflexión)
    public User() {
    }

    // Constructor con argumentos
    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
