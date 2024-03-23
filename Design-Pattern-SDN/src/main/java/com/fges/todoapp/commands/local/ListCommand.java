package com.fges.todoapp.commands.local;

import com.fges.todoapp.commands.Command;
import com.fges.todoapp.storage.DataAccessor;
import com.fges.todoapp.todo.TaskState;
import com.fges.todoapp.todo.Todo;

import java.io.IOException;
import java.util.List;

/**
 * Commande pour lister les tâches stockées selon leur état.
 * Cette commande permet d'afficher les tâches soit complétées, soit non complétées, en fonction
 * de l'état passé au constructeur de la commande.
 */
public class ListCommand implements Command {
    private final DataAccessor dataAccessor; // Accesseur de données pour lire les tâches.
    private final TaskState taskState; // L'état des tâches à lister.

    /**
     * Constructeur de la commande de liste.
     *
     * @param dataAccessor L'accesseur de données pour lire les tâches.
     * @param taskState L'état des tâches à lister (DONE pour les tâches complétées, autrement toutes les tâches).
     */
    public ListCommand(DataAccessor dataAccessor, TaskState taskState) {
        this.dataAccessor = dataAccessor;
        this.taskState = taskState;
    }

    /**
     * Exécute la commande de liste en récupérant et en affichant les tâches selon leur état.
     *
     * @param positionalArgs Les arguments positionnels de la commande, non utilisés ici.
     * @return 0 indiquant que la commande a été exécutée avec succès.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la lecture des tâches.
     */
    @Override
    public int execute(List<String> positionalArgs) throws IOException {
        List<Todo> todoList = this.dataAccessor.read(); // Lit les tâches stockées.
        printTodoList(todoList, this.taskState); // Affiche les tâches selon l'état spécifié.
        return 0;
    }

    /**
     * Affiche les tâches stockées selon leur état.
     *
     * @param todoList La liste des tâches à afficher.
     * @param taskState L'état des tâches à filtrer pour l'affichage.
     */
    private void printTodoList(List<Todo> todoList, TaskState taskState) {
        for (Todo todo : todoList) {
            // Filtre et affiche les tâches selon leur état.
            if (taskState == TaskState.DONE && todo.getTaskState() == TaskState.DONE) {
                System.out.println("- Done: " + todo.getName());
            } else if (taskState != TaskState.DONE) {
                if (todo.getTaskState() == TaskState.DONE) {
                    System.out.println("- Done: " + todo.getName());
                } else {
                    System.out.println("- " + todo.getName());
                }
            }
        }
    }
}
