package org.example.controller;

import org.example.dao.AgendaDAO;
import org.example.models.Agenda;

import java.sql.SQLException;
import java.util.List;

public class AgendamentoService {

    private AgendaDAO agendaDAO;
    public AgendamentoService() {
        this.agendaDAO = new AgendaDAO();
    }

    public void salvarAgenda(Agenda agenda) throws SQLException {
        agendaDAO.salvar(agenda);
    }

    public List<Agenda> listarAgendas() throws SQLException {
        return agendaDAO.listar();
    }
}