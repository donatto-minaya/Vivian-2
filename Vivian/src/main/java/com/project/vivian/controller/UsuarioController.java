package com.project.vivian.controller;

import com.project.vivian.entidad.Usuario;
import com.project.vivian.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customerusers")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private int codigo = 2;

    @GetMapping("")
    public String listar(Model model) throws Exception {
        List<Usuario> usuarios = usuarioService.obtenerCustomerUsuarios();
        model.addAttribute("usuario",new Usuario());
        model.addAttribute("customerusers", usuarios);
        model.addAttribute("verFragmento",codigo);
        return "general-summary";
    }

}
