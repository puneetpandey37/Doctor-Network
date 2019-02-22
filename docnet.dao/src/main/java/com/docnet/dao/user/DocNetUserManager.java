package com.docnet.dao.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.docnet.model.user.User;
@Repository("docNetUserManager")
@Transactional(propagation = Propagation.REQUIRED)
public class DocNetUserManager {

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public User getUserByMobileNo(String mobileNo) {
		List<User> results = entityManager
	            .createNamedQuery("User.findByPhone", User.class)
	            .setParameter("phone", mobileNo).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public User getUserByOtp(String mobileNumber, int otp) {
		List<User> results = entityManager
	            .createNamedQuery("User.findByMobAndOtp", User.class)
	            .setParameter("phone", mobileNumber)
	            .setParameter("otp", otp).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public void setUserOTP(String userName, int otp) {
		String sqlString = "update User u set u.otp = :otp where u.login = :login";
		entityManager
	            .createQuery(sqlString)
	            .setParameter("otp", otp).
	            setParameter("login", userName).executeUpdate();
	}

	public User getUserByUserName(String userName) {
		List<User> results = entityManager
	            .createNamedQuery("User.findByLogin", User.class)
	            .setParameter("login", userName).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public User getUserByEmail(String email) {
		List<User> results = entityManager
	            .createNamedQuery("User.findByEmail", User.class)
	            .setParameter("email", email).getResultList();
	      return results.isEmpty() ? null : results.get(0);
	}

	public User createUser(User user) {
		return entityManager.merge(user);
	}

}
