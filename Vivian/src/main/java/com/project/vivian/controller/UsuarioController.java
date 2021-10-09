package com.project.vivian.controller;

import com.project.vivian.entidad.Usuario;
import com.project.vivian.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	// METODO PARA PROBAR EL SERVER
	@GetMapping("/usuarios")
	public String listar(Model model) {
		List<Usuario> usuarios = usuarioService.findAll();
		model.addAttribute("usuarios", usuarios);
		return "pruebausuarios";
	}

	@PostMapping("/principal")
	public String login(Model model, @RequestParam("email") String email, @RequestParam("clave")String clave, RedirectAttributes redirectAttributes){
		try{
			Usuario usuario = usuarioService.findByEmailPassword(email, clave);
			if (usuario != null && usuario.getIdTipo().getId() == 3){
				return "initial-progress";
			}
//			redirectAttributes.addFlashAttribute("errorMsg","Usuario y/o contraseña incorrectos");
			model.addAttribute("errorMsg","Usuario y/o contraseña incorrectos");
			model.addAttribute("email",email);
			return "index";
		}catch (Exception ex){
			System.out.println(ex.getMessage());
			return "index";
		}

	}
}
