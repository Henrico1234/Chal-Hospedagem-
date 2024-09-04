package br.com.prog2.hopedagem.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.prog2.hopedagem.model.Cliente;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO() {
        this.connection = FabricaDeConexao.getConnection();
    }

    public void inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nomeCliente, rgCliente, enderecoCliente, bairroCliente, cidadeCliente, estadoCliente, cepCliente, nascimentoCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNomeCliente());
            stmt.setString(2, cliente.getRgCliente());
            stmt.setString(3, cliente.getEnderecoCliente());
            stmt.setString(4, cliente.getBairroCliente());
            stmt.setString(5, cliente.getCidadeCliente());
            stmt.setString(6, cliente.getEstadoCliente());
            stmt.setString(7, cliente.getCepCliente());
            stmt.setDate(8, new java.sql.Date(cliente.getNascimentoCliente().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existeRg(String rgCliente) {
        String sql = "SELECT COUNT(*) FROM Cliente WHERE rgCliente = ?";
        try (Connection con = FabricaDeConexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, rgCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}