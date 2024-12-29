/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.EEspindola.ToDo_WebService.Service;

import com.EEspindola.ToDo_WebService.JPA.Result;
import com.EEspindola.ToDo_WebService.JPA.Usuario;

/**
 *
 * @author Eduardo
 */
public interface TodoService {

    public Result GetByIdUsuario(Usuario usuario);

}
