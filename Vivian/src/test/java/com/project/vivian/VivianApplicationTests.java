package com.project.vivian;

import com.project.vivian.dao.UsuarioSpringDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class VivianApplicationTests {

	@Autowired
	UsuarioSpringDAO usuarioDAO;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Test
	void obtenerContraseñaEncriptada() {
		System.out.println(passwordEncoder.encode("1234"));
	}

}
