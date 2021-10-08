package com.project.vivian.daoImpl;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAOImpl {
	public List<Usuario> listarUsuarios();
	public Optional<Usuario> listarUsuarioPorId(int id);
	
	public int save(Usuario u);
	public void delete(int id);
}
