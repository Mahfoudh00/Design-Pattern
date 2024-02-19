// CommandLineHandler.java

package com.fges.todoapp;

import org.apache.commons.cli.Options;

// Classe pour gérer les options de la ligne de commande
public class CommandLineHandler {

    // Créer les options de la ligne de commande
    public static Options createCliOptions() {
        Options cliOptions = new Options();
        cliOptions.addRequiredOption("s", "source", true, "Fichier contenant les tâches");
        cliOptions.addOption("d", "done", false, "Afficher uniquement les tâches terminées");
        cliOptions.addOption("o", "output", true, "Fichier de sortie pour la commande migrate");
        return cliOptions;
    }
}
