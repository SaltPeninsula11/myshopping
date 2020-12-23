package jp.ac.o_hara.product;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class UserListener
 *
 */
@WebListener
public class ProductTestListener implements ServletContextListener {
	ProductDAO dao = null;

	/**
	 * Default constructor. 
	 */
	public ProductTestListener() {
		dao = ProductDAO.getInstance();
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
			dao.execSQL("CREATE TABLE IF NOT EXISTS producttbl"
					+ " (productId SERIAL, imgSrc VARCHAR(256), name VARCHAR(64), price INT,"
					+ "tag1 VARCHAR(16), tag2 VARCHAR(16), tag3 VARCHAR(16), tag4 VARCHAR(16), tag5 VARCHAR(16),"
					+ "PRIMARY KEY (productId))");
		} else {
			if (dao.execSQL("CREATE TABLE IF NOT EXISTS producttbl"
					+ " (productId IDENTITY, imgSrc VARCHAR(256), name VARCHAR(64), price INT,"
					+ "tag1 VARCHAR(16), tag2 VARCHAR(16), tag3 VARCHAR(16), tag4 VARCHAR(16), tag5 VARCHAR(16),"
					+ "PRIMARY KEY (productId))")) {
				System.out.println("TestUserDB is READY.");
			} else {
				System.out.println("TestUserDB is NOT READY.");
			}
		}
		if (dao.count() <= 0) {
			dao.create(new ProductBean("", "テスト", 100, "テスト", "", "", "", ""));
		}

		System.out.println("SystemStart..");
	}
	
	public int getCount() {
		return dao.count();
	}

}
