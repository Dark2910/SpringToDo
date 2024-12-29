/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EEspindola.ToDo_View.ML;

import java.util.List;

/**
 *
 * @author Eduardo
 */
public class Result<T> {

    public boolean isCorrect;
    public List<T> objects;
    public T object;
    public Exception exception;
    public String message;

}
