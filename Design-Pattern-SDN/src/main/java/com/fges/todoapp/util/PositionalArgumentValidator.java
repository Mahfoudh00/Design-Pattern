package com.fges.todoapp.util;

import org.apache.commons.cli.CommandLine;

import java.util.List;

/**
 * Valideur d'arguments positionnels pour vérifier si le nombre d'arguments positionnels est conforme.
 */
public class PositionalArgumentValidator implements ArgumentValidator {

    /**
     * Valide les arguments positionnels de la ligne de commande.
     *
     * @param cmd              La ligne de commande analysée.
     * @param numberOfArgument Le nombre d'arguments requis.
     * @return true si le nombre d'arguments positionnels est conforme, sinon false.
     */
    @Override
    public boolean validateArguments(CommandLine cmd, int numberOfArgument) {
        List<String> positionalArgs = cmd.getArgList();
        // Vérifie si aucun argument n'est requis
        if (numberOfArgument == 0) {
            // Retourne vrai si au moins un argument positionnel est présent
            return !positionalArgs.isEmpty();
        } else {
            // Retourne vrai si le nombre d'arguments positionnels est inférieur au nombre requis
            return (positionalArgs.size() < numberOfArgument);
        }
    }
}
