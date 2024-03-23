package com.fges.todoapp.storage.files.csv;

import com.fges.todoapp.storage.StorageHandler;
import com.fges.todoapp.todo.TaskState;
import com.fges.todoapp.todo.Todo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestionnaire de stockage pour la lecture et l'écriture des tâches depuis/vers un fichier CSV.
 */
public class CsvFileHandler implements StorageHandler {

    // Noms des colonnes dans le fichier CSV
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_STATUS = "Status";

    /**
     * Écrit une liste de tâches dans un fichier CSV.
     *
     * @param todos    La liste des tâches à écrire.
     * @param filePath Le chemin du fichier CSV.
     */
    @Override
    public void write(List<Todo> todos, Path filePath) {
        // Définition du format CSV par défaut
        CSVFormat csvFormat = CSVFormat.DEFAULT;
        try (
                // Utilisation de try-with-resources pour garantir la fermeture des ressources
                FileWriter fWriter = new FileWriter(filePath.toFile(), true);
                CSVPrinter csvPrinter = new CSVPrinter(fWriter, csvFormat)
        ) {
            // Parcours de la liste des tâches
            for (Todo todo : todos) {
                List<String> rowDataList = new ArrayList<>();
                rowDataList.add(todo.getName());
                rowDataList.add((todo.getTaskState() == TaskState.DONE) ? "Done" : "Not Done");

                // Écriture des données de la tâche dans le fichier CSV
                csvPrinter.printRecord(rowDataList);
            }
        } catch (IOException e) {
            // Gestion des exceptions en cas d'erreur lors de l'écriture
            System.err.println("Error processing CSV insert: " + e.getMessage());
        }
    }

    /**
     * Lit les tâches à partir d'un fichier CSV.
     *
     * @param filePath Le chemin du fichier CSV à lire.
     * @return Une liste de tâches lues depuis le fichier CSV.
     */
    @Override
    public List<Todo> read(Path filePath) {
        List<Todo> todoList = new ArrayList<>();

        try (
                // Utilisation de try-with-resources pour garantir la fermeture des ressources
                FileReader fReader = new FileReader(filePath.toFile());
                CSVParser csvParser = CSVFormat.DEFAULT.parse(fReader)
        ) {
            // Parcours des enregistrements CSV
            for (CSVRecord csvRecord : csvParser) {
                String name = csvRecord.get(COLUMN_NAME);
                String status = csvRecord.get(COLUMN_STATUS);

                // Conversion de l'état de la tâche en enum TaskState
                TaskState taskState = status.equals("Done") ? TaskState.DONE : TaskState.NOT_DONE;
                // Création d'une nouvelle tâche et ajout à la liste
                Todo todo = new Todo(name, taskState);
                todoList.add(todo);
            }
        } catch (IOException e) {
            // Gestion des exceptions en cas d'erreur lors de la lecture
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return todoList;
    }
}
