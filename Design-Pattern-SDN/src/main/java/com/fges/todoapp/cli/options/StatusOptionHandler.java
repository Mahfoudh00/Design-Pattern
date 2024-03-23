package com.fges.todoapp.cli.options;

import com.fges.todoapp.todo.TaskState;

/**
 * Classe gérant l'option de statut dans l'application CLI de gestion de tâches.
 * Cette classe implémente l'interface {@link OptionHandler} pour traiter l'option
 * qui spécifie l'état d'une tâche (par exemple, faite ou non faite).
 */
public class StatusOptionHandler implements OptionHandler {
    // État de la tâche déterminé par l'option.
    private TaskState taskState;

    /**
     * Traite la valeur de l'option spécifiée par l'utilisateur pour définir l'état de la tâche.
     * L'option est supposée indiquer si la tâche est faite ou non. Par défaut, si aucune valeur
     * n'est fournie (optionValue est null), l'état est considéré comme DONE (fait).
     *
     * @param optionValue La valeur de l'option, qui détermine l'état de la tâche.
     */
    @Override
    public void handleOption(String optionValue) {
        // Attention : cette logique pourrait être contre-intuitive et mérite une vérification.
        // Une approche plus explicite serait de vérifier la valeur de `optionValue`
        // et de définir `taskState` en conséquence.
        taskState = optionValue == null ? TaskState.DONE : TaskState.NOT_DONE;
    }

    /**
     * Retourne l'état actuel de la tâche configuré par l'option.
     *
     * @return L'état de la tâche, soit DONE soit NOT_DONE.
     */
    public TaskState getTaskState() {
        return taskState;
    }
}
