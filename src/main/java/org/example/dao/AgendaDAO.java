package org.example.dao;

import org.example.models.Agenda;
import org.example.models.AgenteSaude;
import org.example.models.Idoso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaDAO {

    public void salvar(Agenda agenda) throws SQLException {
        String sql = "INSERT INTO agenda (agente_saude_id, idoso_id, data, horario) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, agenda.getAgenteSaude().getId());
            stmt.setLong(2, agenda.getIdoso().getId());
            stmt.setDate(3, new java.sql.Date(agenda.getData().getTime()));
            stmt.setString(4, agenda.getHorario());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                agenda.setId(id);
            }
        }
    }


    public void editar(Agenda agenda) throws SQLException {
        String sql = "UPDATE agenda SET agente_saude_id = ?, idoso_id = ?, data = ?, horario = ? WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, agenda.getAgenteSaude().getId());
            stmt.setLong(2, agenda.getIdoso().getId());
            stmt.setDate(3, new java.sql.Date(agenda.getData().getTime()));
            stmt.setString(4, agenda.getHorario());
            stmt.setLong(5, agenda.getId());
            stmt.executeUpdate();
        }
    }

    public void cancelar(long id) throws SQLException {
        String sql = "DELETE FROM agenda WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Agenda> listar() throws SQLException {
        List<Agenda> agendas = new ArrayList<>();
        String sql = "SELECT * FROM agenda";
        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Agenda agenda = new Agenda();
                agenda.setId(rs.getLong("id"));
                AgenteSaude agenteSaude = new AgenteSaudeDAO().buscarPorId(rs.getLong("agente_saude_id"));
                agenda.setAgenteSaude(agenteSaude);
                Idoso idoso = new IdosoDAO().buscarPorId(rs.getLong("idoso_id"));
                agenda.setIdoso(idoso);
                agenda.setData(rs.getDate("data"));
                agenda.setHorario(rs.getString("horario"));
                agendas.add(agenda);
            }
        }
        return agendas;
    }

    public Agenda buscarPorId(long id) throws SQLException {
        String sql = "SELECT * FROM agenda WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Agenda agenda = new Agenda();
                    agenda.setId(rs.getLong("id"));
                    AgenteSaude agenteSaude = new AgenteSaudeDAO().buscarPorId(rs.getLong("agente_saude_id"));
                    agenda.setAgenteSaude(agenteSaude);
                    Idoso idoso = new IdosoDAO().buscarPorId(rs.getLong("idoso_id"));
                    agenda.setIdoso(idoso);
                    agenda.setData(rs.getDate("data"));
                    agenda.setHorario(rs.getString("horario"));
                    return agenda;
                }
            }
        }
        return null;
    }
}
