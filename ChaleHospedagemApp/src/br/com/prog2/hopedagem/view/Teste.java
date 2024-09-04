package br.com.prog2.hopedagem.view;

import java.sql.Connection;
import br.com.prog2.hopedagem.persistencia.FabricaDeConexao;

public class Teste {

	public static void main(String[] args) {
		Connection con = FabricaDeConexao.getConnection();
		if(con != null){
		System.out.println("OK");
		FabricaDeConexao.close(con);
		}
		}

	}


