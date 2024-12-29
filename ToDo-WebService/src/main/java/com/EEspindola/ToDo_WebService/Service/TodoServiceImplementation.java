/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EEspindola.ToDo_WebService.Service;

import com.EEspindola.ToDo_WebService.JPA.Result;
import com.EEspindola.ToDo_WebService.JPA.Todo;
import com.EEspindola.ToDo_WebService.JPA.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduardo
 */
@Service
public class TodoServiceImplementation implements TodoService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetByIdUsuario(Usuario usuario) {
        Result result = new Result();

        try {
            TypedQuery<Todo> queryTodo
                    = entityManager.createQuery("FROM Todo "
                            + "WHERE Usuario.IdUsuario = :pIdUsuario",
                            Todo.class);

            queryTodo.setParameter("pIdUsuario", usuario.getIdUsuario());

            List<Todo> listTodo = queryTodo.getResultList();

            result.isCorrect = true;
            result.objects = listTodo;

        } catch (Exception e) {
            result.isCorrect = false;
            result.exception = e;
            result.message = e.getLocalizedMessage();
        }
        return result;
    }
}
