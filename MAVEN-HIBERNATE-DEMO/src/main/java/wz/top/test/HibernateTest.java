package wz.top.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import wz.top.model.User;
import wz.top.utils.HibernateUtil;

public class HibernateTest {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			User user = new User();
			user.setCode("lili");
			user.setName("yuli");
			session.save(user);
			transaction.commit();
			System.out.println("save successful!");
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			System.out.println("save failure!");
		} finally {
			HibernateUtil.closeSession();
		}
		HibernateUtil.shutdown();
	}
}
