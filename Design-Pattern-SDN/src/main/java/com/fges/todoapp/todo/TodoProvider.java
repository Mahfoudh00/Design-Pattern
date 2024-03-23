package com.fges.todoapp.todo;

import com.fges.todoapp.dto.TodoDTO;
import com.fges.todoapp.storage.DataAccessor;
import fr.anthonyquere.dumbcrud.CrudProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Fournit les fonctionnalités CRUD pour les tâches à partir d'un DataAccessor.
 */
public class TodoProvider implements CrudProvider<TodoDTO> {
    private final DataAccessor dataAccessor;

    /**
     * Constructeur prenant un DataAccessor.
     *
     * @param dataAccessor Le DataAccessor à utiliser pour l'accès aux données.
     */
    public TodoProvider(DataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    /**
     * Ajoute une nouvelle tâche à partir d'un TodoDTO.
     *
     * @param todoDTO Le TodoDTO contenant les informations de la tâche à ajouter.
     * @throws Exception Si une erreur se produit lors de l'ajout de la tâche.
     */
    @Override
    public void add(TodoDTO todoDTO) throws Exception {
        Todo todo = new Todo(todoDTO.getContent(), todoDTO.getStatus());
        this.dataAccessor.insert(todo);
        System.err.println("Todo added from the server");
    }

    /**
     * Récupère la liste des tâches et les convertit en une liste de TodoDTO.
     *
     * @return Une liste de TodoDTO représentant les tâches.
     * @throws Exception Si une erreur se produit lors de la récupération de la liste des tâches.
     */
    @Override
    public List<TodoDTO> list() throws Exception {
        List<Todo> todos = this.dataAccessor.read();
        List<TodoDTO> todoDTOs = new ArrayList<>();
        for (Todo todo : todos) {
            todoDTOs.add(new TodoDTO(todo.getName(), todo.getStatus()));
        }
        System.err.println("Todo listed to the server");
        return todoDTOs;
    }
}
