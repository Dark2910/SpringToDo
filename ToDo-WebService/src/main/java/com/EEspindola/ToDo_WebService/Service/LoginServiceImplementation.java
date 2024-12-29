/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EEspindola.ToDo_WebService.Service;

import com.EEspindola.ToDo_WebService.JPA.Result;
import com.EEspindola.ToDo_WebService.JPA.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eduardo
 */
@Service
public class LoginServiceImplementation implements LoginService{

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Result Login(Usuario usuarioBusqueda) {

        Result result = new Result();

        try {
            TypedQuery<Usuario> queryTodo
                    = entityManager.createQuery("FROM Usuario "
                            + "WHERE Email = :pEmail "
                            + "AND Password = :pPassword",
                            Usuario.class);

            queryTodo.setParameter("pEmail", usuarioBusqueda.getEmail());
            queryTodo.setParameter("pPassword", usuarioBusqueda.getPassword());

            Usuario usuarioRecuperado = queryTodo.getSingleResult();

            result.isCorrect = true;
            result.object = usuarioRecuperado;

        } catch (Exception e) {

            result.isCorrect = false;
            result.exception = e;
            result.message = e.getLocalizedMessage();
        }
        return result;
    }
}
