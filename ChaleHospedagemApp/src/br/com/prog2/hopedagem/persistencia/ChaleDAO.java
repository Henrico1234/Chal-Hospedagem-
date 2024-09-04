package br.com.prog2.hopedagem.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import br.com.prog2.hopedagem.model.Chale;

public class ChaleDAO {
    // Método para inserir chalé
    public void inserirChale(Chale chale) {
        String sql = "INSERT INTO Chale (localizacao, capacidade, valorAltaEstacao, valorBaixaEstacao) VALUES (?, ?, ?, ?)";

        try (Connection con = FabricaDeConexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, chale.getLocalizacao());
            stmt.setInt(2, chale.getCapacidade());
            stmt.setDouble(3, chale.getValorAltaEstacao());
            stmt.setDouble(4, chale.getValorBaixaEstacao());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obter o valor do chalé com base na data de início e fim
    public double getValorChale(int codChale, java.util.Date dataInicio, java.util.Date dataFim) {
        double valorChale = 0.0;
        String sql = "SELECT valorAltaEstacao, valorBaixaEstacao FROM Chale WHERE codChale = ?";
        
        try (Connection con = FabricaDeConexao.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, codChale);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                double valorAltaEstacao = rs.getDouble("valorAltaEstacao");
                double valorBaixaEstacao = rs.getDouble("valorBaixaEstacao");
                
                // Verifica se alguma das datas está no período de alta estação
                if (isAltaEstacao(dataInicio, dataFim)) {
                    valorChale = valorAltaEstacao;
                } else {
                    valorChale = valorBaixaEstacao;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valorChale;
    }

    // Verifica se a data está no período de alta estação
    private boolean isAltaEstacao(java.util.Date dataInicio, java.util.Date dataFim) {
        // Define os meses de alta estação
        int[] mesesAltaEstacao = {1, 2, 7, 11, 12};
        
        // Formata a data para obter o mês
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        int mesInicio = Integer.parseInt(sdf.format(dataInicio));
        int mesFim = Integer.parseInt(sdf.format(dataFim));

        // Verifica se algum dos meses está na alta estação
        for (int mes : mesesAltaEstacao) {
            if (mesInicio == mes || mesFim == mes) {
                return true;
            }
        }
        
        return false;
 
       
    }
    // Verifica se o chalé está disponível nas datas fornecidas
    public boolean isChaleDisponível(int codChale, java.util.Date dataInicio, java.util.Date dataFim) {
        String sql = "SELECT COUNT(*) FROM Hospedagem WHERE codChale = ? AND "
                   + "(dataInicio <= ? AND dataFim >= ?)";
        try (Connection con = FabricaDeConexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, codChale);
            stmt.setDate(2, new java.sql.Date(dataFim.getTime())); // dataFim é a data mais recente
            stmt.setDate(3, new java.sql.Date(dataInicio.getTime())); // dataInicio é a data mais antiga
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0; // Retorna true se o chalé estiver disponível
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Obtém a capacidade do chalé
    public int getCapacidadeChale(int codChale) {
        int capacidade = 0;
        String sql = "SELECT capacidade FROM Chale WHERE codChale = ?";
        try (Connection con = FabricaDeConexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, codChale);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    capacidade = rs.getInt("capacidade");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return capacidade;
    }
}