/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EEspindola.ToDo_View.Controller;

import com.EEspindola.ToDo_View.ML.Result;
import com.EEspindola.ToDo_View.ML.Todo;
import com.EEspindola.ToDo_View.ML.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Eduardo
 */

@Controller
@RequestMapping("/todo")
public class TodoController {
    
    
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public String GetAll(Model model, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            return ("redirect:/login");
        }

        Result result = new Result();

        try {
            String endpoint = "http://localhost:8081/todoAPI";

            Usuario usuario = (Usuario) session.getAttribute("usuario");
            HttpEntity<Usuario> httpEntity = new HttpEntity(usuario);

            ResponseEntity<Result<Todo>> response = restTemplate.exchange(
                    endpoint,
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<Result<Todo>>() {}
            );

            result = response.getBody();

            model.addAttribute("Todos", result.objects);
            model.addAttribute("NewTodo", new Todo());

            return ("Todo");

        } catch (Exception e) {
            result.isCorrect = false;
            result.exception = e;
            result.message = e.getLocalizedMessage();

            model.addAttribute("Error", result.message = e.getLocalizedMessage());

            return ("Layout");
        }
    }

    public Result GetById(@RequestBody Todo todo) {

        String endpoint = "http://localhost:8081/todoAPI/getById";

        HttpEntity<Todo> httpEntity = new HttpEntity<>(todo);

        ResponseEntity<Result<Todo>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<Result<Todo>>() {}
        );

        Result result = new Result();
        result = response.getBody();

        return result;
    }

    @PostMapping("/add")
    public String Post(Model model, @ModelAttribute Todo newTodo, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            return ("redirect:/login");
        }

        try {
            String endpoint = "http://localhost:8081/todoAPI/post";

            newTodo.Usuario = (Usuario) session.getAttribute("usuario");

            HttpEntity<Todo> httpEntity = new HttpEntity(newTodo);

            restTemplate.exchange(
                    endpoint,
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<Result<Todo>>() {}
            );
            return ("redirect:/todo");

        } catch (Exception e) {
            Result result = new Result();

            result.isCorrect = false;
            result.exception = e;
            result.message = e.getLocalizedMessage();

            model.addAttribute("Error", result.message = e.getLocalizedMessage());

            return ("Layout");
        }
    }

    @PostMapping("/update")
    public String Put(Model model, @RequestBody Todo todo, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            return ("redirect:/login");
        }

        try {
            String endpoint = "http://localhost:8081/todoAPI/put";

            todo.Usuario = (Usuario) session.getAttribute("usuario");

            HttpEntity<Todo> httpEntity = new HttpEntity<>(todo);

            restTemplate.exchange(
                    endpoint,
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<Todo>() {}
            );
            return ("redirect:/todo");

        } catch (Exception e) {
            Result result = new Result();

            result.isCorrect = false;
            result.exception = e;
            result.message = e.getLocalizedMessage();

            model.addAttribute("Error", result.message = e.getLocalizedMessage());

            return ("Layout");
        }

    }

    @PostMapping("/delete")
    public String Delete(Model model, @RequestBody Todo todo, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            return ("redirect:/login");
        }

        try {
            String endpoint = "http://localhost:8081/todoAPI/delete";

            HttpEntity<Integer> httpEntity = new HttpEntity(todo.getIdToDo());

            restTemplate.exchange(
                    endpoint,
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<Result>() {}
            );

            return ("redirect:/todo");

        } catch (Exception e) {
            Result result = new Result();

            result.isCorrect = false;
            result.exception = e;
            result.message = e.getLocalizedMessage();

            model.addAttribute("Error", result.message = e.getLocalizedMessage());

            return ("Layout");
        }
    }

    @PostMapping("/newStatus")
    public String NewStatus(Model model, @RequestBody Todo todo, HttpSession session) {

        if (session.getAttribute("usuario") == null) {
            return ("redirect:/login");
        }

        Result result = new Result();

        try {
            result = GetById(todo);

            Todo todoRecuperado = (Todo) result.object;

            todoRecuperado.setStatus(todo.getStatus());

            Put(model, todoRecuperado, session);

            return ("redirect:/todo");

        } catch (Exception e) {
            result.isCorrect = false;
            result.exception = e;
            result.message = e.getLocalizedMessage();

            model.addAttribute("Error", result.message = e.getLocalizedMessage());

            return ("Layout");
        }
    }
    
}
