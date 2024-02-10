package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TodoFileManager {
    private final Path filePath;
    private String fileContent;

    // Constructeur de TodoFileManager
    public TodoFileManager(Path filePath, String fileContent) {
        this.filePath = filePath;
        this.fileContent = fileContent;
    }

    // Méthode pour l'insertion de tâches dans le fichier
    public void insertTodo(String todo, boolean isDone) throws IOException {
        if (filePath.toString().endsWith(".json")) {
            handleJsonInsert(todo, isDone);
        }
    }

    // Méthode privée pour gérer l'insertion dans les fichiers JSON
    private void handleJsonInsert(String todo, boolean isDone) throws IOException {
        JsonNode actualObj = new ObjectMapper().readTree(fileContent);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            ObjectNode todoNode = JsonNodeFactory.instance.objectNode();
            todoNode.put("task", todo);
            todoNode.put("done", isDone);
            arrayNode.add(todoNode);
        }

        fileContent = actualObj.toString();
        Files.writeString(filePath, fileContent);
    }

    // Méthode pour lister les tâches dans le fichier avec l'option --done
    public void listTodos(boolean showDone) {
        if (filePath.toString().endsWith(".json")) {
            handleJsonList(showDone);
        }
    }

    // Méthode privée pour gérer la liste dans les fichiers JSON avec l'option --done
    private void handleJsonList(boolean showDone) {
        JsonNode actualObj = parseJsonNode();
        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.forEach(node -> {
                boolean isDone = node.get("done").asBoolean();
                String task = node.get("task").asText();

                if (showDone && isDone) {
                    System.out.println("- [Done] " + task);
                } else if (!showDone && !isDone) {
                    System.out.println("- " + task);
                }
            });
        }
    }

    // Méthode privée pour lire et parser le contenu JSON
    private JsonNode parseJsonNode() {
        try {
            return new ObjectMapper().readTree(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonNodeFactory.instance.arrayNode(); // Par défaut, retourne un tableau JSON vide en cas d'erreur
    }
}
