package org.example.dao;

import org.example.models.Idoso;
import org.example.models.Vacina;
import org.example.models.VacinaAgendada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacinaAgendadaDAO {

    public void salvar(VacinaAgendada vacina) throws SQLException {
        String sql = "INSERT INTO vacina_agendada (idoso_id, vacina_id, data_agendamento) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, vacina.getIdoso().getId());
            stmt.setLong(2, vacina.getVacina().getId());
            stmt.setDate(3, java.sql.Date.valueOf(vacina.getDataAgendamento()));
            stmt.executeUpdate();
        }
    }

    public List<VacinaAgendada> listarTodas() throws SQLException {
        List<VacinaAgendada> vacinasAgendadas = new ArrayList<>();
        String sql = "SELECT va.id, va.data_agendamento, i.id as idoso_id, i.nome as idoso_nome, v.id as vacina_id, v.nome as vacina_nome " +
                "FROM vacina_agendada va " +
                "JOIN idoso i ON va.idoso_id = i.id " +
                "JOIN vacina v ON va.vacina_id = v.id";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                VacinaAgendada vacinaAgendada = new VacinaAgendada();
                vacinaAgendada.setId(rs.getLong("id"));
                vacinaAgendada.setDataAgendamento(rs.getDate("data_agendamento").toLocalDate());

                Idoso idoso = new Idoso();
                idoso.setId(rs.getLong("idoso_id"));
                idoso.setNome(rs.getString("idoso_nome"));
                vacinaAgendada.setIdoso(idoso);

                Vacina vacina = new Vacina();
                vacina.setId(rs.getLong("vacina_id"));
                vacina.setNome(rs.getString("vacina_nome"));
                vacinaAgendada.setVacina(vacina);

                vacinasAgendadas.add(vacinaAgendada);
            }
        }
        return vacinasAgendadas;
    }
}
