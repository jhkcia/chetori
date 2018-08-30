package ir.chetori.user.catalogue;

import org.springframework.beans.factory.annotation.Autowired;

import ir.chetori.core.catalogue.BaseCatalogue;
import ir.chetori.user.dao.UserDAO;
import ir.chetori.user.model.User;

public class UserCatalogue extends BaseCatalogue<User> {
	@Autowired
	UserDAO dao;


	public User getUser(String personnelCode, String password) {
		// return dao.getUser(personnelCode, password);
		return null;
	}

	public void changeToken(String personnelCode, String token) {
		dao.changeToken(personnelCode, token);
	}

	public User getByToken(String token) {
		return dao.getByToken(token);
	}
}
