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
		//野菜、果物、肉等
		if (dao.count() <= 0) {
			dao.create(new ProductBean("test", "テスト", 100, "テスト", "", "", "", ""));
			dao.create(new ProductBean("strawberry", "苺", 120, "果物", "", "", "", ""));
			dao.create(new ProductBean("orange", "みかん", 120, "果物", "", "", "", ""));
			dao.create(new ProductBean("lemon", "レモン", 160, "果物", "", "", "", ""));
			dao.create(new ProductBean("grapefruit", "グレープフルーツ", 200, "果物", "", "", "", ""));
			dao.create(new ProductBean("apple", "りんご", 300, "果物", "", "", "", ""));
			dao.create(new ProductBean("grapes", "ぶどう", 300, "果物", "", "", "", ""));
			dao.create(new ProductBean("muscat", "マスカット", 350, "果物", "", "", "", ""));
			dao.create(new ProductBean("cherry", "さくらんぼ", 700, "果物", "", "", "", ""));
			dao.create(new ProductBean("melon", "メロン", 1000, "果物", "", "", "", ""));
		}

		System.out.println("SystemStart..");
	}
	
	public int getCount() {
		return dao.count();
	}

}
