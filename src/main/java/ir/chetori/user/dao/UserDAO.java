package ir.chetori.user.dao;

import java.util.List;

import ir.chetori.core.BaseEntityDAO;
import ir.chetori.user.model.User;

public class UserDAO extends BaseEntityDAO<User> {

	public UserDAO() {
		super(User.class);
	}

	public void changeToken(String username, String token) {
		return ;
	}

	public User getByToken(String token) {
		return null;
	}

	public List<User> getEvaluatorUsers(Long evaluatorId) {
		return null;
	}

}
