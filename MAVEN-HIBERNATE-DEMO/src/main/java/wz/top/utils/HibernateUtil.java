package wz.top.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	static {
		try {
			Configuration configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			throw new ExceptionInInitializerError();
		}
	}
	
	private static final ThreadLocal<Session> THREAD_LOCAL = new ThreadLocal<Session>();
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session getSession() {
		Session session = THREAD_LOCAL.get();
		if (session == null) {
			session = sessionFactory.openSession();
			THREAD_LOCAL.set(session);
		}
		return session;
	}
	
	public static void closeSession() {
		Session session = THREAD_LOCAL.get();
		if (session != null)
			session.close();
		THREAD_LOCAL.set(null);
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}
	
}
