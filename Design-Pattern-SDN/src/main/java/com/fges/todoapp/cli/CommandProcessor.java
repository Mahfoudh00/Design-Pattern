package com.fges.todoapp.cli;

import com.fges.todoapp.cli.options.OptionHandler;
import com.fges.todoapp.cli.options.OutputOptionHandler;
import com.fges.todoapp.cli.options.SourceOptionHandler;
import com.fges.todoapp.cli.options.StatusOptionHandler;
import com.fges.todoapp.todo.TaskState;
import com.fges.todoapp.util.PositionalArgumentValidator;
import org.apache.commons.cli.CommandLine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe chargée de traiter les commandes et les options passées à l'application CLI.
 * Elle initialise et utilise différents gestionnaires pour chaque type d'option,
 * et gère les arguments positionnels de la commande.
 */
public class CommandProcessor {
    private final Map<String, OptionHandler> optionHandlers = new HashMap<>();
    private final CommandLine cmd;

    /**
     * Constructeur de CommandProcessor qui initialise les gestionnaires d'options.
     *
     * @param cmd L'objet CommandLine contenant les options et arguments passés à l'application.
     */
    public CommandProcessor(CommandLine cmd) {
        this.cmd = cmd;
        initializeOptionHandlers();
    }

    /**
     * Initialise les gestionnaires d'options pour les différentes options supportées par l'application.
     */
    private void initializeOptionHandlers() {
        optionHandlers.put("s", new SourceOptionHandler());
        optionHandlers.put("done", new StatusOptionHandler());
        optionHandlers.put("output", new OutputOptionHandler());
    }

    // Méthodes privées pour obtenir les gestionnaires d'options spécifiques.
    private SourceOptionHandler getSourceOptionHandler() {
        return (SourceOptionHandler) optionHandlers.get("s");
    }

    private StatusOptionHandler getStatusOptionHandler() {
        return (StatusOptionHandler) optionHandlers.get("done");
    }

    private OutputOptionHandler getOutputOptionHandler() {
        return (OutputOptionHandler) optionHandlers.get("output");
    }

    /**
     * Traite la commande en utilisant les gestionnaires d'options et valide les arguments positionnels.
     *
     * @return Un code indiquant le succès (0) ou l'échec (1) du traitement de la commande.
     */
    public int processCommand() {
        // Traite chaque option en utilisant son gestionnaire dédié.
        for (Map.Entry<String, OptionHandler> entry : optionHandlers.entrySet()) {
            String option = entry.getKey();
            OptionHandler handler = entry.getValue();
            if (cmd.hasOption(option)) {
                handler.handleOption(cmd.getOptionValue(option));
            }
        }

        // Valide les arguments positionnels de la commande.
        PositionalArgumentValidator argumentValidator = new PositionalArgumentValidator();
        if (!argumentValidator.validateArguments(cmd, 0)) {
            System.err.println("Argument manquant");
            return 1;
        }

        return 0; // Retourne 0 si la commande a été traitée avec succès.
    }

    // Méthodes publiques pour accéder à diverses informations traitées par les gestionnaires d'options et les arguments positionnels.
    public String getCommand() { return cmd.getArgList().get(0); }
    public String getTodoContent() { return cmd.getArgList().get(1); }
    public String getFileName() { return getSourceOptionHandler().getFileName(); }
    public List<String> getPositionalArgs() { return cmd.getArgList(); }
    public TaskState getTaskState() { return getStatusOptionHandler().getTaskState(); }
    public String getOutputFile() { return getOutputOptionHandler().getOutputFile(); }
}
