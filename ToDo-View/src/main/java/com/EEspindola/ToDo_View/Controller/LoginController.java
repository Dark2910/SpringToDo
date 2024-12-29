/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EEspindola.ToDo_View.Controller;

import com.EEspindola.ToDo_View.ML.Result;
import com.EEspindola.ToDo_View.ML.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Eduardo
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public String Login(Model model) {
        model.addAttribute("Usuario", new Usuario());
        return ("Login");
    }

    @PostMapping
    public String Login(@ModelAttribute Usuario usuario, HttpSession session) {

        Result result = new Result();

        try {
            String endpoint = "http://localhost:8081/loginAPI";
            HttpEntity<Usuario> httpEntity = new HttpEntity<Usuario>(usuario);

            ResponseEntity<Result<Usuario>> response = restTemplate.exchange(
                    endpoint,
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<Result<Usuario>>() {
            }
            );

            result = response.getBody();

            if (response.getStatusCode().equals(HttpStatus.FOUND) && result.isCorrect) {

                session.setAttribute("usuario", (Usuario) result.object);
                result.message = "redirect:/todo";
            }

        } catch (Exception e) {
            result.isCorrect = false;
            result.exception = e;
            result.message = "redirect:/login";
        }

        return result.message;
    }

}
