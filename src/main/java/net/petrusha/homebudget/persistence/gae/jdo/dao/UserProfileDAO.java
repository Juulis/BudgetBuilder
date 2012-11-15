package net.petrusha.homebudget.persistence.gae.jdo.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import net.petrusha.homebudget.persistence.gae.jdo.model.UserProfileEntity;

public class UserProfileDAO extends BaseDAO<UserProfileEntity> {

	public UserProfileDAO() {
		super(UserProfileEntity.class);
	}

	public List<UserProfileEntity> getProfilesByUserId(final String userId) {
		return executeTransactional(new Command<List<UserProfileEntity>>(){
			@SuppressWarnings("unchecked")
			public List<UserProfileEntity> run(PersistenceManager pm) {
				Query q = pm.newQuery(UserProfileEntity.class);
				q.setFilter("userId == userIdParam");
				q.declareParameters("String userIdParam");
				List<UserProfileEntity> profiles =  (List<UserProfileEntity>) q.execute(userId);
				return profiles;
			}
		});
	}

}
