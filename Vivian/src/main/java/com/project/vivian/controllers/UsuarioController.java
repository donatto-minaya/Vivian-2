package com.project.vivian.controllers;

import com.project.vivian.dao.UsuarioDAO;
import com.project.vivian.entidad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(path = "/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@GetMapping("")
	public String listar(Model model) {
		List<Usuario> usuarios = usuarioDAO.findAll();
		model.addAttribute("usuarios", usuarios);
		return "error";
	}

	@PostMapping("/login")
	public String login(Model model){
		return "hola";
	}
}
