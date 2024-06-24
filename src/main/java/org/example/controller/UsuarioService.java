package org.example.controller;

import org.example.dao.AgenteSaudeDAO;
import org.example.models.AgenteSaude;

import java.sql.SQLException;
import java.util.List;

public class UsuarioService {
    private AgenteSaudeDAO agenteSaudeDAO;

    public UsuarioService() {
        this.agenteSaudeDAO = new AgenteSaudeDAO();
    }

    public void salvarAgenteSaude(AgenteSaude agenteSaude) throws SQLException {
        agenteSaudeDAO.salvar(agenteSaude);
    }

    public List<AgenteSaude> listarAgentesSaude() throws SQLException {
        return agenteSaudeDAO.listar();
    }
}