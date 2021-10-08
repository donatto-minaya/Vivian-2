package com.project.vivian.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.vivian.daoImpl.UsuarioDAOImpl;
import com.project.vivian.dao.UsuarioDAO;

public class UsuarioService implements UsuarioDAOImpl {
	
	@Autowired
	private UsuarioDAO data;

	@Override
	public List<Usuario> listarUsuarios() {		
		return (List<Usuario>) data.findAll();
	}

	@Override
	public Optional<Usuario> listarUsuarioPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Usuario u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
