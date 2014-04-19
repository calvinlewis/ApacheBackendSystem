package com.social.persistence;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.JDBCConnectionException;

import com.social.common.BookingLogger;
import com.social.common.Messages;
import com.social.models.User;

public class HibernateUserManager extends AbstractHibernateDatabaseManager {

	private static String USER_TABLE_NAME = "USER";
	private static String USER_CLASS_NAME = "User";
	
	private static String SELECT_ALL_USERS = "from "
			+ USER_CLASS_NAME + " as user";

	private static String SELECT_NUMBER_USERS = "select count (*) from "
			+ USER_CLASS_NAME;
	
	private static String SELECT_USER_WITH_ID = "from "
			+ USER_CLASS_NAME + " as user where user.id = :id";
	
	private static String SELECT_USER_WITH_TOKEN = "from "
			+ USER_CLASS_NAME + " as user where user.token = :token";
	
	private static final String DROP_TABLE_SQL = "drop table "
			+ USER_TABLE_NAME + ";";
	
	private static final String CREATE_TABLE_SQL = "create table " + USER_TABLE_NAME + "(USER_PRIMARY_KEY char(36) primary key,"
			+ "ID tinytext, PASSWORD tinytext, FIRST_NAME tinytext, LAST_NAME tinytext, POSITION tinytext, GENDER tinytext, TOKEN tinytext);";
	
	public HibernateUserManager() {
		super();
	}

	@Override
	public boolean setupTable() {
		HibernateUtil.executeSQLQuery(DROP_TABLE_SQL);
		return HibernateUtil.executeSQLQuery(CREATE_TABLE_SQL);
	}

	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return USER_CLASS_NAME;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return USER_TABLE_NAME;
	}
	
	public synchronized User getUserById(String id) {
		
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery(SELECT_USER_WITH_ID);
			query.setParameter("id", id);
			List<User> users = query.list();
			transaction.commit();

			if (users.isEmpty()) {
				return null;
			} else {
				User user = users.get(0);
				return user;
			}
		} catch (HibernateException exception) {
			/*BookingLogger.getDefault().severe(this,
					Messages.METHOD_GET_USER_BY_ID,
					"error.getUserById", exception);*/
			return null;
		} finally {
			closeSession();
		}
	}
	
	/*public synchronized User getUserByToken(String token) {
		
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getCurrentSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery(SELECT_USER_WITH_TOKEN);
			query.setParameter("token", token);
			List<User> users = query.list();
			transaction.commit();

			if (users.isEmpty()) {
				return null;
			} else {
				User user = users.get(0);
				return user;
			}
		} catch (HibernateException exception) {
			BookingLogger.getDefault().severe(this,
					Messages.METHOD_GET_USER_BY_TOKEN,
					"error.getUserByToken", exception);
			return null;
		} finally {
			closeSession();
		}
	}*/
	
	public synchronized  List<User> getAllUsers() {
		List<User> errorResult = null;
		Session session = null;
		try {
			session = HibernateUtil.getCurrentSession();
			Query query = session.createQuery(SELECT_ALL_USERS);
			List<User> users = query.list();

			return users;
		} catch (ObjectNotFoundException exception) {
			return errorResult;
		}  catch (JDBCConnectionException exception) {
			HibernateUtil.clearSessionFactory();
			return errorResult;
		} catch (HibernateException exception) {
			return errorResult;
		} catch (RuntimeException exception) {
			return errorResult;
		} finally {
			closeSession();
		}
	}
	
	public synchronized int getNumberOfUsers() {
		
		Session session = null;
		Long aLong;

		try {
			session = HibernateUtil.getCurrentSession();
			Query query = session
					.createQuery(SELECT_NUMBER_USERS);
			aLong = (Long) query.uniqueResult();
			return aLong.intValue();
		} catch (ObjectNotFoundException exception) {

			return 0;
		} catch (HibernateException exception) {

			return 0;
		} catch (RuntimeException exception) {

			return 0;
		} finally {
			closeSession();
		}
	}
}
