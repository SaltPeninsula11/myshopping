package jp.ac.o_hara.site.user;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class UserListener
 *
 */
@WebListener
public class UserTestListener implements ServletContextListener {
	UserDAO dao = null;

	/**
	 * Default constructor. 
	 */
	public UserTestListener() {
		dao = UserDAO.getInstance();
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		dao = null;
		System.out.println("SystemStop..");
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		if (System.getenv("DATABASE_URL") != null) {
			dao.execSQL("CREATE TABLE IF NOT EXISTS USERTBL"
					+ " (id SERIAL, realName VARCHAR(64), userID VARCHAR(64), passwd VARCHAR(64),"
					+ "money INT, point INT, PRIMARY KEY (id))");
		} else {
			if (dao.execSQL("CREATE TABLE IF NOT EXISTS USERTBL"
					+ " (id IDENTITY, realName VARCHAR(64), userID VARCHAR(64), passwd VARCHAR(64)"
					+ "money INT, point INT, PRIMARY KEY (id))")) {
				System.out.println("TestUserDB is READY.");
			} else {
				System.out.println("TestUserDB is NOT READY.");
			}
		}
		if (dao.count() <= 0) {
			dao.create(new UserBean("管理者", "admin", "adminpass", 1000, 0));
			dao.create(new UserBean("hogehoge", "hoge", "hogepass", 1000, 0));
			dao.create(new UserBean("piyopiyo", "piyo", "piyopass", 1000, 0));
		}

		System.out.println("SystemStart..");
	}

}
