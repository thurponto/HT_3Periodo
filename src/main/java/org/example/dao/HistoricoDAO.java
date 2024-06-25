package org.example.dao;

import org.example.models.Historico;
import org.example.models.Idoso;
import org.example.models.Agenda;
import org.example.models.Vacina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoDAO {

    public void salvar(Historico historico) throws SQLException {
        String sql = "INSERT INTO historico (idoso_id, agenda_id, vacina_id, alergias, condicoes_medicas, observacoes) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, historico.getIdoso().getId());
            stmt.setLong(2, historico.getAgenda().getId());
            stmt.setLong(3, historico.getVacina().getId());
            stmt.setString(4, historico.getAlergias());
            stmt.setString(5, historico.getCondicoesMedicas());
            stmt.setString(6, historico.getObservacoes());
            stmt.executeUpdate();
        }
    }
    public Historico buscarPorId(long id) throws SQLException {
        String sql = "SELECT * FROM historico WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Historico historico = new Historico();
                    historico.setId(rs.getLong("id"));
                    Idoso idoso = new IdosoDAO().buscarPorId(rs.getLong("idoso_id"));
                    historico.setIdoso(idoso);
                    Agenda agenda = new AgendaDAO().buscarPorId(rs.getLong("agenda_id"));
                    historico.setAgenda(agenda);
                    Vacina vacina = new VacinaDAO().buscarPorId(rs.getLong("vacina_id"));
                    historico.setVacina(vacina);
                    historico.setAlergias(rs.getString("alergias"));
                    historico.setCondicoesMedicas(rs.getString("condicoes_medicas"));
                    historico.setObservacoes(rs.getString("observacoes"));
                    return historico;
                }
            }
        }
        return null; // Retornar null se não encontrar nenhum histórico com o ID especificado
    }

   public List<Historico> listar() throws SQLException {
        List<Historico> historicos = new ArrayList<>();
        String sql = "SELECT * FROM historico";
        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Historico historico = new Historico();
                historico.setId(rs.getLong("id"));
                Idoso idoso = new IdosoDAO().buscarPorId(rs.getLong("idoso_id"));
                historico.setIdoso(idoso);
                Agenda agenda = new AgendaDAO().buscarPorId(rs.getLong("agenda_id"));
                historico.setAgenda(agenda);
                Vacina vacina = new VacinaDAO().buscarPorId(rs.getLong("vacina_id"));
                historico.setVacina(vacina);
                historico.setAlergias(rs.getString("alergias"));
                historico.setCondicoesMedicas(rs.getString("condicoes_medicas"));
                historico.setObservacoes(rs.getString("observacoes"));
                historicos.add(historico);
            }
        }
        return historicos;
    }

}