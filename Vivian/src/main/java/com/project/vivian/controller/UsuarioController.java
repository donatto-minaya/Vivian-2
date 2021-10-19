package com.project.vivian.controller;

import com.project.vivian.entidad.Categoria;
import com.project.vivian.entidad.Tipo;
import com.project.vivian.entidad.UsuarioSpring;
import com.project.vivian.service.CategoriaService;
import com.project.vivian.service.TipoService;
import com.project.vivian.service.UsuarioSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping
public class UsuarioController {

	@Autowired
	private UsuarioSpringService usuarioSpringService;

	@Autowired
	private CategoriaService categoriaService;
	
	public void obtenerDatosUsuario(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioSpring usuarioSpring = usuarioSpringService.obtenerPorEmail(auth.getName());
		model.addAttribute("nombreCompleto",usuarioSpring.getNombresUsuario() + " " + usuarioSpring.getApellidosUsuario());
	}

	// ENDPOINT PARA PROBAR EL SERVER
	@GetMapping("/usuarios")
	public String listar(Model model) throws Exception {
		List<UsuarioSpring> usuarios = usuarioSpringService.obtenerAdminUsuarios();
		model.addAttribute("usuarios", usuarios);
		List<Categoria> listaCategorias = categoriaService.obtenerCategorias();
		model.addAttribute("categorias",listaCategorias);
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

	private int codigo = 0;
	@GetMapping("/principal")
	public String principal(Model model) {
		
		obtenerDatosUsuario(model);
		
		codigo = 0;
		model.addAttribute("verUsuarios", codigo);
		return "general-summary";
	}

	@GetMapping(value="/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	
	@GetMapping(value="/adminusers")
	public String adminUsers(Model model) throws Exception {
		obtenerDatosUsuario(model);
		List<UsuarioSpring> listaAdminUsuarios = usuarioSpringService.obtenerAdminUsuarios();
		model.addAttribute("usuarioSpring",new UsuarioSpring());
		model.addAttribute("adminusers",listaAdminUsuarios);
		codigo = 1;
		model.addAttribute("verUsuarios", codigo);
		return "general-summary";
	}

	@PostMapping(value="/adminusers")
	public String insertarAdminUser(UsuarioSpring usuarioSpring, Model model) throws Exception {
		try{
			usuarioSpringService.crearUsuario(usuarioSpring);
			model.addAttribute("msgInsert","Usuario ingresado correctamente");
			System.out.println("Usuario ingresado correctamente");
		}catch (Exception ex){
			model.addAttribute("msgInsert",ex.getMessage());
			System.out.println(ex.getMessage());
		}
		return "redirect:/adminusers";
	}

}
