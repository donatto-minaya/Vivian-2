package com.project.vivian.controller;

import com.project.vivian.entidad.UsuarioSpring;
import com.project.vivian.service.UsuarioSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping
public class UsuarioController {

	@Autowired
	private UsuarioSpringService usuarioSpringService;

	// ENDPOINT PARA PROBAR EL SERVER
	@GetMapping("/usuarios")
	public String listar(Model model) {
		List<UsuarioSpring> usuarios = usuarioSpringService.findAll();
		model.addAttribute("usuarios", usuarios);
		return "pruebausuarios";
	}

	@GetMapping({"/","/login"})
	public String login(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getName().equals("anonymousUser")){
			return "index";
		}else{
			return "redirect:/principal";
		}
	}

	@GetMapping("/principal")
	public String principal(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth);
		return "initial-progress";
	}

	@GetMapping(value="/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth);
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
}
