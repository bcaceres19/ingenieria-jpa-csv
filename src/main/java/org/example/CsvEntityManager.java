package org.example;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CsvEntityManager {
    private final Class<User> entityClass;
    private final String filePath;

    public CsvEntityManager(Class<User> entityClass, String filePath) {
        this.entityClass = entityClass;
        this.filePath = filePath;
    }

    public void save(User entity) throws IOException, IllegalAccessException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            StringBuilder line = new StringBuilder();

            for (Field field : entityClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(CsvColumn.class)) {
                    field.setAccessible(true);
                    line.append(field.get(entity)).append(",");
                }
            }

            writer.println(line.substring(0, line.length() - 1)); // Eliminar la última coma
        }
    }

    public List<User> findAll() throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException {
        List<User> entities = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length != 3) {
                    System.out.println("Línea con formato incorrecto: " + line);
                    continue;
                }

                User entity = entityClass.getDeclaredConstructor().newInstance();

                // Asumir el orden de los valores en el archivo: ID, Nombre, Edad
                entity.setId(Integer.parseInt(values[0].trim()));  // ID
                entity.setName(values[1].trim());                   // Nombre
                entity.setAge(Integer.parseInt(values[2].trim()));  // Edad

                entities.add(entity);
            }
        } catch (NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Error al crear instancia de User", e);
        }
        return entities;
    }

    private void setValue(Field field, User entity, String value) throws IllegalAccessException {
        Class<?> type = field.getType();
        if (type == int.class) {
            field.setInt(entity, Integer.parseInt(value));
        } else if (type == String.class) {
            field.set(entity, value);
        }
    }
}
