package tableIntegration;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectingTable { //a connecting table que faz a conexão com o meu banco

	private static final String USERNAME = "root"; // meu usuario do mysql pra acessar como adm

	// troca a senha aqui de baixo pra sua senha pra testar meu codigo
	private static final String PASSWORD = "rcip67@20!R";

	// o caminho da minha porta host e o nome da minha tabela
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/registroCidades";

	//private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/registroCidades?characterEncoding=UTF-8";

	// registroCidades é a minnha tabela no SQL

	public static Connection creatingCoonnectionSql() throws Exception {
	    Class.forName("com.mysql.cj.jdbc.Driver");

	    Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
	    return connection;
	}

	 
	public static void main(String[] args) throws Exception {
		Connection con = creatingCoonnectionSql();
		if(con != null);
		System.out.println("connected sucessfully");
	}

}
