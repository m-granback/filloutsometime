package com.example.filloutsometime.controllers;

import com.example.filloutsometime.entities.Todo;
import com.example.filloutsometime.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://192.168.1.172:80", "http://localhost:80", "http://127.0.0.1:80", "http://192.168.1.172:8080", "http://localhost", "http://localhost:80","http://192.168.1.85"}, methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class TodoController {
    @Autowired
    private TodoService todoService;

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }
    @PostMapping("/todo")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
        return ResponseEntity.ok(todoService.addTodo(todo));
    }
    @PutMapping("/todo/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id) {
        System.out.println("Updating todo with id: " + id);
        return ResponseEntity.ok(todoService.updateTodoById(id));
    }


}
