package application;

import java.util.Date;

import citiesTableDAO.RegistrationDAO;
import crudCity.User;

public class Main {

	public static void main(String[] args) {
		RegistrationDAO registrationDao = new RegistrationDAO();

		// Criação do usuário
		User user = new User();
		user.setName("ana");
		user.setId(50);
		user.setRegistrationDate(new Date());

		// Definição da cidade para o usuário
		user.setCityName("colorado");

		// Definição do estado para o usuário
		user.setStateAcronym("PR");

		// Salvando o usuário no banco de dados
		registrationDao.save(user);
	}
}
