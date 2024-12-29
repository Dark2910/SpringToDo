/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EEspindola.ToDo_WebService.RestController;

import com.EEspindola.ToDo_WebService.JPA.Result;
import com.EEspindola.ToDo_WebService.JPA.Todo;
import com.EEspindola.ToDo_WebService.JPA.Usuario;
import com.EEspindola.ToDo_WebService.Service.TodoServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Eduardo
 */
@RestController
@RequestMapping("/todoAPI")
public class TodoRestController {

    @Autowired
    private TodoServiceImplementation todoServiceImplementation;

    @Autowired
    com.EEspindola.ToDo_WebService.JPA.TodoRepository todoRepository;

    @PostMapping
    public ResponseEntity<Result<Todo>> GetByIdUsuario(@RequestBody Usuario usuario) {
        Result result = todoServiceImplementation.GetByIdUsuario(usuario);

        if (result.isCorrect) {
            return ResponseEntity.ok().body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/getById")
    public Result<Todo> GetById(@RequestBody Todo todo) {

        Result result = new Result();

        try {
            result.object = todoRepository.findById(todo.getIdToDo());
            result.isCorrect = true;

        } catch (Exception e) {
            result.isCorrect = false;
            result.exception = e;
            result.message = e.getLocalizedMessage();
        }
        return result;
    }

    @PostMapping("/post")
    public Result<Todo> Post(@RequestBody Todo todo) {

        Result result = new Result();

        try {
            result.object = todoRepository.save(todo);
            result.isCorrect = true;

        } catch (Exception e) {
            result.isCorrect = false;
            result.exception = e;
            result.message = e.getLocalizedMessage();
        }
        return result;
    }

    @PostMapping("/put")
    public Result Put(@RequestBody Todo todo) {

        Result result = new Result();

        try {
            result.object = todoRepository.save(todo);
            result.isCorrect = true;

        } catch (Exception e) {
            result.isCorrect = false;
            result.exception = e;
            result.message = e.getLocalizedMessage();
        }
        return result;
    }

    @PostMapping("/delete")
    public Result Delete(@RequestBody Long id) {

        Result result = new Result();

        try {
            todoRepository.deleteById(id);
            result.isCorrect = true;

        } catch (Exception e) {
            result.isCorrect = false;
            result.exception = e;
            result.message = e.getLocalizedMessage();
        }
        return result;

    }
}
