package org.example.dao;

import org.example.models.AgenteSaude;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenteSaudeDAO {

    public void salvar(AgenteSaude agenteSaude) throws SQLException {
        String sql = "INSERT INTO agente_saude (nome) VALUES (?)";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, agenteSaude.getNome());
            stmt.executeUpdate();
        }
    }

    public List<AgenteSaude> listar() throws SQLException {
        List<AgenteSaude> agentes = new ArrayList<>();
        String sql = "SELECT * FROM agente_saude";
        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                AgenteSaude agente = new AgenteSaude();
                agente.setId(rs.getLong("id"));
                agente.setNome(rs.getString("nome"));
                agentes.add(agente);
            }
        }
        return agentes;
    }

    public AgenteSaude buscarPorId(long id) throws SQLException {
        String sql = "SELECT * FROM agente_saude WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    AgenteSaude agente = new AgenteSaude();
                    agente.setId(rs.getLong("id"));
                    agente.setNome(rs.getString("nome"));
                    return agente;
                }
            }
        }
        return null;
    }

}
