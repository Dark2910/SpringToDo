/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EEspindola.ToDo_WebService.RestController;

import com.EEspindola.ToDo_WebService.JPA.Result;
import com.EEspindola.ToDo_WebService.JPA.Usuario;
import com.EEspindola.ToDo_WebService.Service.LoginServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/loginAPI")
public class LoginRestController {

    @Autowired
    private LoginServiceImplementation loginServiceImplementation;

    @PostMapping
    public ResponseEntity<Result<Usuario>> Get(@RequestBody Usuario usuarioBusqueda) {

        Result result = loginServiceImplementation.Login(usuarioBusqueda);

        if (result.isCorrect) {
            return ResponseEntity.status(HttpStatus.FOUND).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

}
