package org.example.service;

import org.example.dao.IdosoDAO;
import org.example.models.Idoso;

import java.sql.SQLException;
import java.util.List;

public class IdosoService {
    private IdosoDAO idosoDAO;

    public IdosoService() {
        this.idosoDAO = new IdosoDAO();
    }

    public void salvar(Idoso idoso) throws SQLException {
        idosoDAO.salvar(idoso);
    }

    public List<Idoso> listar() throws SQLException {
        return idosoDAO.listar();
    }

    public Idoso buscarPorId(long id) throws SQLException {
        return idosoDAO.buscarPorId(id);
    }
}
