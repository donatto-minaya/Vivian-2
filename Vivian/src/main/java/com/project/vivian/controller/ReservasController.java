package com.project.vivian.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.vivian.entidad.Mesa;
import com.project.vivian.entidad.Reserva;
import com.project.vivian.entidad.Usuario;
import com.project.vivian.entidad.general.Confirmacion;
import com.project.vivian.service.MesaService;
import com.project.vivian.service.ReservacionesService;
import com.project.vivian.service.UsuarioService;
import com.project.vivian.service.constants.ResponseEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservaciones")
public class ReservasController {

	@Autowired
	private ReservacionesService reservacionesService;
	
	@Autowired
	private MesaService mesaService;

	@Autowired
	private UsuarioService usuarioService;

	private int codigo = 3;

	public void obtenerDatosUsuario(Model model) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Optional<Usuario> usuarioSpring = usuarioService.obtenerPorEmail(auth.getName());
		model.addAttribute("nombreCompleto",
				usuarioSpring.get().getNombresUsuario() + " " + usuarioSpring.get().getApellidosUsuario());
	}

	@GetMapping("")
	public String listar(Model model) throws Exception {

		List<Reserva> reservas = reservacionesService.obtenerReservaciones();
		List<Mesa> mesas = mesaService.listarMesasActivas();
		
		model.addAttribute("reserva", new Reserva());
		model.addAttribute("mesas", mesas);
		model.addAttribute("reservas", reservas);
		model.addAttribute("verFragmento", codigo);
		return "general-summary";
	}

	@PostMapping("")
	public ResponseEntity<Confirmacion> insertarReserva(@RequestParam String json) throws Exception {
		Confirmacion confirmacion = new Confirmacion();
		try {
			Reserva reserva = new ObjectMapper().readValue(json, Reserva.class);
			String dni = reserva.getUsuario().getDni();
			reserva.setUsuario(usuarioService.obtenerPorDni(dni).get());
			reservacionesService.crearReserva(reserva);
			confirmacion.setEstado(1);
			confirmacion.setMensaje("Se guardo correctamente");

			return ResponseEntity.accepted().body(confirmacion);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			confirmacion.setEstado(ResponseEstado.ERROR_APLICACION);
			confirmacion.setMensaje(ex.getMessage());
			return ResponseEntity.badRequest().body(confirmacion);
		}
	}

	@PutMapping("")
	public ResponseEntity<Confirmacion> updateReserva(String json) throws Exception {
		Confirmacion confirmacion = new Confirmacion();
		try {
			Reserva reserva = new ObjectMapper().readValue(json, Reserva.class);
			String dni = reserva.getUsuario().getDni();
			reserva.setUsuario(usuarioService.obtenerPorDni(dni).get());

			reservacionesService.actualizarReserva(reserva);
			confirmacion.setEstado(1);
			confirmacion.setMensaje("Actualizado correctamente");

			return ResponseEntity.accepted().body(confirmacion);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			confirmacion.setEstado(ResponseEstado.ERROR_APLICACION);
			confirmacion.setMensaje(ex.getMessage());
			return ResponseEntity.badRequest().body(confirmacion);
		}
	}

	@DeleteMapping("")
	public ResponseEntity<Confirmacion> deleteReserva(@RequestParam Integer id) throws Exception {
		Confirmacion confirmacion = new Confirmacion();
		try {
			if (reservacionesService.eliminarReserva(id)) {
				confirmacion.setEstado(ResponseEstado.OK);
				confirmacion.setMensaje("Usuario eliminado correctamente.");
			} else {
				confirmacion.setEstado(ResponseEstado.ERROR_NEGOCIO);
				confirmacion.setMensaje("Error al eliminar el reservacion.");
			}
			return ResponseEntity.accepted().body(confirmacion);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			confirmacion.setEstado(ResponseEstado.ERROR_APLICACION);
			confirmacion.setMensaje(ex.getMessage());
			return ResponseEntity.badRequest().body(confirmacion);
		}
	}

}
