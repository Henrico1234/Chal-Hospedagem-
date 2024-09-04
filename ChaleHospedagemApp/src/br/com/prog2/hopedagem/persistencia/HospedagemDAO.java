package br.com.prog2.hopedagem.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.prog2.hopedagem.model.Hospedagem;

public class HospedagemDAO {

    public void inserirHospedagem(Hospedagem hospedagem) {
        String sql = "INSERT INTO Hospedagem (codChale, codCliente, estado, dataInicio, dataFim, qtdPessoas, desconto, valorFinal) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = FabricaDeConexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, hospedagem.getCodChale());
            stmt.setInt(2, hospedagem.getCodCliente());
            stmt.setString(3, hospedagem.getEstado());
            stmt.setDate(4, new java.sql.Date(hospedagem.getDataInicio().getTime()));
            stmt.setDate(5, new java.sql.Date(hospedagem.getDataFim().getTime()));
            stmt.setInt(6, hospedagem.getQtdPessoas());
            stmt.setDouble(7, hospedagem.getDesconto());
            stmt.setDouble(8, hospedagem.getValorFinal());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}