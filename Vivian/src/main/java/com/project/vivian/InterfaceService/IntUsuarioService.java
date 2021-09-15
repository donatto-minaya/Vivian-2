package com.project.vivian.InterfaceService;

import java.util.List;
import java.util.Optional;

import com.project.vivian.models.Usuario;

public interface IntUsuarioService {
	public List<Usuario> listarUsuarios();
	public Optional<Usuario> listarUsuarioPorId(int id);
	
	public int save(Usuario u);
	public void delete(int id);

}
