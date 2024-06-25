package org.example.dao;

import org.example.models.Vacina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacinaDAO {

    public void salvar(Vacina vacina) throws SQLException {
        String sql = "INSERT INTO vacina (nome) VALUES (?)";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vacina.getNome());
            stmt.executeUpdate();
        }
    }

    public List<Vacina> listar() throws SQLException {
        List<Vacina> vacinas = new ArrayList<>();
        String sql = "SELECT * FROM vacina";
        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vacina vacina = new Vacina();
                vacina.setId(rs.getLong("id"));
                vacina.setNome(rs.getString("nome"));
                vacinas.add(vacina);
            }
        }
        return vacinas;
    }

    public Vacina buscarPorId(long id) throws SQLException {
        String sql = "SELECT * FROM vacina WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Vacina vacina = new Vacina();
                    vacina.setId(rs.getLong("id"));
                    vacina.setNome(rs.getString("nome"));
                    return vacina;
                }
            }
        }
        return null;
    }
    public List<Vacina> listarComNome() throws SQLException {
        List<Vacina> vacinas = new ArrayList<>();
        String sql = "SELECT id, nome FROM vacina"; // Ajuste a consulta SQL para incluir apenas o ID e o nome da vacina
        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vacina vacina = new Vacina();
                vacina.setId(rs.getLong("id"));
                vacina.setNome(rs.getString("nome"));
                vacinas.add(vacina);
            }
        }
        return vacinas;
    }

}
