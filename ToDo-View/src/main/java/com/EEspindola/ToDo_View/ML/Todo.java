/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EEspindola.ToDo_View.ML;

/**
 *
 * @author Eduardo
 */
public class Todo {

    private int IdToDo;
    private String Nombre;
    private int Status;
    public Usuario Usuario;

    public int getIdToDo() {
        return IdToDo;
    }

    public void setIdToDo(int IdToDo) {
        this.IdToDo = IdToDo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

}
