package com.project.vivian.serviceImpl;

import com.project.vivian.dao.TipoDAO;
import com.project.vivian.entidad.Tipo;
import com.project.vivian.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TipoServiceImpl implements TipoService {

    @Autowired
    private TipoDAO tipoDAO;

    @Override
    public List<Tipo> obtenerTipos() {
        return tipoDAO.findAll();
    }
}
