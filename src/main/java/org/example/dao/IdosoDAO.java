package org.example.dao;

import org.example.models.Idoso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IdosoDAO {

    public void salvar(Idoso idoso) throws SQLException {
        String sql = "INSERT INTO idoso (nome) VALUES (?)";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idoso.getNome());
            stmt.executeUpdate();
        }
    }

    public List<Idoso> listar() throws SQLException {
        List<Idoso> idosos = new ArrayList<>();
        String sql = "SELECT * FROM idoso";
        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Idoso idoso = new Idoso();
                idoso.setId(rs.getLong("id"));
                idoso.setNome(rs.getString("nome"));
                idosos.add(idoso);
            }
        }
        return idosos;
    }

    public Idoso buscarPorId(long id) throws SQLException {
        String sql = "SELECT * FROM idoso WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Idoso idoso = new Idoso();
                    idoso.setId(rs.getLong("id"));
                    idoso.setNome(rs.getString("nome"));
                    return idoso;
                }
            }
        }
        return null;
    }

}
