package com.project.vivian.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.vivian.InterfaceService.IntUsuarioService;
import com.project.vivian.models.Usuario;

@Controller
@RequestMapping
public class UsuarioController {
	
	@Autowired
	private IntUsuarioService service;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Usuario> usuarios = service.listarUsuarios();
		model.addAttribute("usuarios", usuarios);
		
		return "index";
	}
}
