package com.fges.todoapp.cli.options;

/**
 * Interface représentant un gestionnaire d'option pour l'application CLI de gestion de tâches.
 * Chaque implémentation de cette interface doit gérer une option spécifique de l'application.
 */
public interface OptionHandler {

    /**
     * Traite une option passée à l'application CLI.
     * Cette méthode doit être implémentée pour gérer spécifiquement l'option concernée.
     *
     * @param option L'option à gérer, sous forme de chaîne de caractères. Il s'agit généralement
     *               d'un mot-clé ou d'une commande entrée par l'utilisateur.
     */
    void handleOption(String option);
}
