package com.example.filloutsometime.services;

import com.example.filloutsometime.entities.Todo;
import com.example.filloutsometime.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if(todo == null) {
            return null;
        }
        todo.setCompleted(!todo.isCompleted());
        return todoRepository.save(todo);
    }
}
