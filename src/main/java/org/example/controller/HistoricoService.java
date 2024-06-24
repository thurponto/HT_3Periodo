package org.example.controller;

import org.example.dao.HistoricoDAO;
import org.example.models.Historico;

import java.sql.SQLException;
import java.util.List;

public class HistoricoService {

    private HistoricoDAO historicoDAO;

    public HistoricoService() {
        this.historicoDAO = new HistoricoDAO();
    }

    public void salvarHistorico(Historico historico) throws SQLException {
        historicoDAO.salvar(historico);
    }

    public List<Historico> listarHistoricos() throws SQLException {
        return historicoDAO.listar();
    }

    public Historico buscarPorId(long id) throws SQLException {
        return historicoDAO.buscarPorId(id);
    }
}
