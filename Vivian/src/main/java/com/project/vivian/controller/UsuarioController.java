package com.project.vivian.controller;

import com.project.vivian.entidad.*;
import com.project.vivian.entidad.general.Confirmacion;
import com.project.vivian.service.CategoriaService;
import com.project.vivian.service.UsuarioSpringService;
import com.project.vivian.service.constants.ResponseEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

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
	public ResponseEntity<Confirmacion> insertarAdminUser(UsuarioSpring usuarioSpring, Model model) throws Exception {
		Confirmacion confirmacion = new Confirmacion();
		try{
			UsuarioSpring usuarioEmail = usuarioSpringService.obtenerPorEmail(usuarioSpring.getUsername());
			if (usuarioEmail != null){
				confirmacion.setEstado(ResponseEstado.ERROR_NEGOCIO);
				confirmacion.setMensaje("El usuario ya existe.");
			} else {
				UsuarioSpring usuarioDni = usuarioSpringService.obtenerPorDni(usuarioSpring.getDni());
				if (usuarioDni != null){
					confirmacion.setEstado(ResponseEstado.ERROR_NEGOCIO);
					confirmacion.setMensaje("El DNI ya existe.");
				} else {
					UsuarioSpring usuarioCreated = usuarioSpringService.crearAdminUsuario(usuarioSpring);
					if (usuarioCreated != null){
						confirmacion.setEstado(ResponseEstado.OK);
						confirmacion.setMensaje("Usuario ingresado correctamente.");
					}
				}
			}
			return ResponseEntity.accepted().body(confirmacion);
		}catch (Exception ex){
			System.out.println(ex.getMessage());
			confirmacion.setEstado(ResponseEstado.ERROR_APLICACION);
			confirmacion.setMensaje("Error en el servidor.");
			return ResponseEntity.badRequest().body(confirmacion);
		}
	}

	@DeleteMapping(value="/adminusers")
	public ResponseEntity<Confirmacion> deleteAdminUser(Model model, @RequestParam Integer id) throws Exception {
		Confirmacion confirmacion = new Confirmacion();
		try{
			if (usuarioSpringService.eliminarPorId(id)){
				confirmacion.setEstado(ResponseEstado.OK);
				confirmacion.setMensaje("Usuario eliminado correctamente.");
			} else {
				confirmacion.setEstado(ResponseEstado.ERROR_NEGOCIO);
				confirmacion.setMensaje("Error al eliminar el usuario.");
			}
			return ResponseEntity.accepted().body(confirmacion);
		}catch (Exception ex){
			System.out.println(ex.getMessage());
			confirmacion.setEstado(ResponseEstado.ERROR_APLICACION);
			confirmacion.setMensaje("Error en el servidor.");
			return ResponseEntity.badRequest().body(confirmacion);
		}
	}

	@PutMapping(value="/adminusers")
	public ResponseEntity<Confirmacion> updateAdminUser(Model model, UsuarioSpring usuarioSpring) throws Exception {
		Confirmacion confirmacion = new Confirmacion();
		try{
			UsuarioSpring searchEmail = usuarioSpringService.obtenerPorEmail(usuarioSpring.getUsername());
			UsuarioSpring searchDni = usuarioSpringService.obtenerPorDni(usuarioSpring.getDni());
			Optional<UsuarioSpring> usuarioActualizar = usuarioSpringService.obtenerPorId(usuarioSpring.getId());

			if (searchEmail == null && searchDni == null){
				Integer valido = usuarioSpringService.actualizarAdminUsuario(usuarioSpring.getId(), usuarioSpring.getDni(),
						usuarioSpring.getNombresUsuario(), usuarioSpring.getApellidosUsuario(), usuarioSpring.getUsername(), usuarioSpring.getTelefono(), usuarioSpring.getEstado());
				if (valido == 1){
					confirmacion.setEstado(ResponseEstado.OK);
					confirmacion.setMensaje("Usuario actualizado correctamente.");
				}
			} else if (searchEmail == null && searchDni != null){
				if (searchDni.getId() == usuarioActualizar.get().getId()){
					Integer valido = usuarioSpringService.actualizarAdminUsuario(usuarioSpring.getId(), usuarioSpring.getDni(),
							usuarioSpring.getNombresUsuario(), usuarioSpring.getApellidosUsuario(), usuarioSpring.getUsername(), usuarioSpring.getTelefono(), usuarioSpring.getEstado());
					if (valido == 1){
						confirmacion.setEstado(ResponseEstado.OK);
						confirmacion.setMensaje("Usuario actualizado correctamente.");
					}
				} else {
					confirmacion.setEstado(ResponseEstado.ERROR_NEGOCIO);
					confirmacion.setMensaje("El DNI ya existe.");
				}
			} else if (searchEmail != null && searchDni == null){
				if (searchEmail.getId() == usuarioActualizar.get().getId()){
					Integer valido = usuarioSpringService.actualizarAdminUsuario(usuarioSpring.getId(), usuarioSpring.getDni(),
							usuarioSpring.getNombresUsuario(), usuarioSpring.getApellidosUsuario(), usuarioSpring.getUsername(), usuarioSpring.getTelefono(), usuarioSpring.getEstado());
					if (valido == 1){
						confirmacion.setEstado(ResponseEstado.OK);
						confirmacion.setMensaje("Usuario actualizado correctamente.");
					}
				} else {
					confirmacion.setEstado(ResponseEstado.ERROR_NEGOCIO);
					confirmacion.setMensaje("El Usuario ya existe.");
				}
			} else {
				if (searchEmail.getId() == usuarioActualizar.get().getId()){
					if (searchDni.getId() == usuarioActualizar.get().getId()){
						Integer valido = usuarioSpringService.actualizarAdminUsuario(usuarioSpring.getId(), usuarioSpring.getDni(),
								usuarioSpring.getNombresUsuario(), usuarioSpring.getApellidosUsuario(), usuarioSpring.getUsername(), usuarioSpring.getTelefono(), usuarioSpring.getEstado());
						if (valido == 1){
							confirmacion.setEstado(ResponseEstado.OK);
							confirmacion.setMensaje("Usuario actualizado correctamente.");
						}
					} else {
						confirmacion.setEstado(ResponseEstado.ERROR_NEGOCIO);
						confirmacion.setMensaje("El DNI ya existe.");
					}
				} else {
					confirmacion.setEstado(ResponseEstado.ERROR_NEGOCIO);
					confirmacion.setMensaje("El Usuario ya existe.");
				}
			}
			return ResponseEntity.accepted().body(confirmacion);
		}catch (Exception ex){
			System.out.println(ex.getMessage());
			confirmacion.setEstado(ResponseEstado.ERROR_APLICACION);
			confirmacion.setMensaje("Error en el servidor.");
			return ResponseEntity.badRequest().body(confirmacion);
		}
	}

}
