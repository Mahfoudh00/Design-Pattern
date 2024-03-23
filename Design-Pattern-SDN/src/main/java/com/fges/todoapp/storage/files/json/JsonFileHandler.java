package com.fges.todoapp.storage.files.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fges.todoapp.storage.StorageHandler;
import com.fges.todoapp.storage.files.FileReader;
import com.fges.todoapp.util.PathValidator;
import com.fges.todoapp.todo.TaskState;
import com.fges.todoapp.todo.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gestionnaire de stockage pour la lecture et l'écriture des tâches depuis/vers un fichier JSON.
 */
public class JsonFileHandler implements StorageHandler {

    /**
     * Écrit une liste de tâches dans un fichier JSON.
     *
     * @param todos    La liste des tâches à écrire.
     * @param filePath Le chemin du fichier JSON.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de l'écriture du fichier.
     */
    @Override
    public void write(List<Todo> todos, Path filePath) throws IOException {
        // Lecture du contenu du fichier existant
        String fileContent = FileReader.readFileContent(filePath, new PathValidator());
        ObjectMapper mapper = new ObjectMapper();
        // Conversion du contenu en arbre JSON
        JsonNode actualObj = mapper.readTree(fileContent);
        // Création d'un tableau JSON s'il n'existe pas
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        // Vérification et ajout des tâches au tableau JSON
        if (actualObj instanceof ArrayNode arrayNode) {
            for (Todo todo : todos) {
                if (todo.getTaskState() == TaskState.DONE) {
                    // Nouvelle structure avec la propriété "done"
                    arrayNode.add(JsonNodeFactory.instance.objectNode()
                            .put("text", todo.getName())
                            .put("done", true)
                    );
                } else {
                    // Structure retro compatible sans la propriété "done"
                    arrayNode.add(todo.getName());
                }
            }
        }

        // Écriture du contenu mis à jour dans le fichier JSON
        Files.writeString(filePath, actualObj.toString());
    }

    /**
     * Lit les tâches à partir d'un fichier JSON.
     *
     * @param filePath Le chemin du fichier JSON à lire.
     * @return Une liste de tâches lues depuis le fichier JSON.
     */
    @Override
    public List<Todo> read(Path filePath) {
        try {
            // Lecture du contenu du fichier JSON
            String fileContent = Files.readString(filePath);
            ObjectMapper mapper = new ObjectMapper();
            // Conversion du contenu en arbre JSON
            JsonNode actualObj = mapper.readTree(fileContent);

            List<Todo> todoList = new ArrayList<>();

            // Création d'un tableau JSON vide s'il n'existe pas
            if (actualObj instanceof MissingNode) {
                actualObj = JsonNodeFactory.instance.arrayNode();
            }

            // Parcours des éléments du tableau JSON
            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.forEach(node -> {
                    JsonNode todoNode = node.get("text");
                    JsonNode doneNode = node.get("done");

                    if (todoNode != null) {
                        // Récupération du nom de la tâche
                        String task = todoNode.asText();
                        // Vérification de l'état de la tâche
                        boolean done = doneNode != null && doneNode.asBoolean();
                        TaskState state = done ? TaskState.DONE : TaskState.NOT_DONE;
                        // Création de la tâche et ajout à la liste
                        Todo todo = new Todo(task, state);
                        todoList.add(todo);
                    } else {
                        // Structure rétro-compatible sans la propriété "done"
                        String task = node.asText();
                        Todo todo = new Todo(task, TaskState.NOT_DONE);
                        todoList.add(todo);
                    }
                });
            }

            return todoList;
        } catch (IOException e) {
            System.err.println("Error processing JSON list: " + e.getMessage());
            return Collections.emptyList();
        }

    }
}
