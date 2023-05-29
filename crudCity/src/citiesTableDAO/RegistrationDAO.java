package citiesTableDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import crudCity.User;
import tableIntegration.ConnectingTable;

public class RegistrationDAO {
	
	public void save(User user) {
		String sql = "INSERT INTO usuario (nome, idade, estado_sigla, data_cadastro, cidade_id) VALUES (?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			// Conexão com o banco
			conn = ConnectingTable.creatingCoonnectionSql();
			
			// Prepared statement para inserir usuário
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getName());
			pstm.setInt(2, user.getPersonAge());
			pstm.setString(3, user.getStateAcronym());
			pstm.setDate(4, new Date(user.getRegistrationDate().getTime()));
			
			// Perguntar a cidade do usuário
			int cidadeId = askUserCity(conn);
			pstm.setInt(5, cidadeId);
			
			pstm.execute();
			
			System.out.println("Usuário registrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fechando conexão aberta
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private int askUserCity(Connection conn) {
		int cidadeId = 0;
		Scanner scanner = new Scanner(System.in);
		
		try {
			// Perguntar a cidade do usuário
			System.out.println("Informe a cidade do usuário:");
			String cidadeNome = scanner.nextLine();
			
			// Verificar se a cidade já existe
			String sql = "SELECT id FROM cidade WHERE nome = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, cidadeNome);
			ResultSet rs = pstm.executeQuery();
			
			if (rs.next()) {
				cidadeId = rs.getInt("id");
			} else {
				// Se a cidade não existe, inserir na tabela
				sql = "INSERT INTO cidade (nome) VALUES (?)";
				pstm = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				pstm.setString(1, cidadeNome);
				pstm.executeUpdate();
				
				ResultSet generatedKeys = pstm.getGeneratedKeys();
				if (generatedKeys.next()) {
					cidadeId = generatedKeys.getInt(1);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		
		return cidadeId;
	}
}
