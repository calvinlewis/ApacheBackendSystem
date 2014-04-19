package com.social.persistence;

import com.social.models.User;
import com.social.actions.*;

public class HibernateTestCase {

	public static void main(String[] args) {
		
		HibernateUserManager manager = new HibernateUserManager();
		HibernateUtil.getCurrentSession();
		manager.setupTable();
		
		User empty = new User();
		
		empty.setId("1234");
		empty.setPassword("test");
		
		/*for (int i=0; i<50; i++)
			manager.add(empty);
		for (int i=0; i<500; i++)
			manager.add(empty);*/
		for (int i=0; i<50000; i++)
			manager.add(empty);

	}
}