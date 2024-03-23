package com.fges.todoapp.commands.local;

import com.fges.todoapp.commands.Command;
import com.fges.todoapp.todo.Todo;
import com.fges.todoapp.todo.TaskState;
import com.fges.todoapp.storage.DataAccessor;

import java.io.IOException;
import java.util.List;

/**
 * Commande pour insérer une nouvelle tâche dans la liste des tâches à faire.
 * Cette commande utilise un accesseur de données pour sauvegarder la nouvelle tâche
 * et prend en compte l'état initial de la tâche lors de sa création.
 */
public class InsertCommand implements Command {
    private final DataAccessor dataAccessor; // L'accesseur de données pour interagir avec le stockage des tâches.
    private final TaskState taskState; // L'état initial de la tâche à insérer.

    /**
     * Constructeur de la commande d'insertion.
     *
     * @param dataAccessor L'accesseur de données pour sauvegarder la tâche.
     * @param taskState L'état initial de la tâche à créer.
     */
    public InsertCommand(DataAccessor dataAccessor, TaskState taskState) {
        this.dataAccessor = dataAccessor;
        this.taskState = taskState;
    }

    /**
     * Exécute la commande d'insertion en utilisant les arguments positionnels fournis.
     * Crée une nouvelle tâche et la sauvegarde à l'aide de l'accesseur de données.
     *
     * @param positionalArgs Les arguments positionnels de la commande, où positionalArgs.get(1)
     *                       est censé être le nom de la tâche à insérer.
     * @return 0 si la tâche a été insérée avec succès, 1 en cas d'erreur (par exemple, si le nom de la tâche est manquant).
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la sauvegarde de la tâche.
     */
    @Override
    public int execute(List<String> positionalArgs) throws IOException {
        if (positionalArgs.size() < 2) {
            System.err.println("Missing TODO name");
            return 1; // Retourne 1 pour indiquer une erreur si le nom de la tâche est manquant.
        }

        String task = positionalArgs.get(1); // Récupère le nom de la tâche à partir des arguments positionnels.
        Todo todo = new Todo(task, taskState); // Crée une nouvelle instance de Todo avec le nom de la tâche et l'état initial.
        dataAccessor.insert(todo); // Sauvegarde la nouvelle tâche à l'aide de l'accesseur de données.

        return 0; // Retourne 0 pour indiquer le succès de l'opération.
    }
}
