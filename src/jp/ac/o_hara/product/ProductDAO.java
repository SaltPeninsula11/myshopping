package jp.ac.o_hara.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.ac.o_hara.site.SimpleDAO;

public class ProductDAO extends SimpleDAO {
	// Singletonパターン（GoFデザインパターン）
	// 用途：一つのインスタンスを共有する、複数のインスタンス生成を認めない
	private static ProductDAO dao = new ProductDAO(); // 唯一のインスタンス
	
	private ProductDAO() {} // privateにすることで、外部からアクセスできなくなる
	
	public static ProductDAO getInstance() { // staticメソッドでインスタンス（へのポインタ）を得る
		return dao;
	}
	public boolean create(ProductBean product) {
		Connection db = this.createConnection();
		//PreparedStatement ps = null;
		boolean result = false;
		try (PreparedStatement ps = db.prepareStatement("INSERT INTO producttbl(imgSrc, name, price, tag1, tag2, tag3, tag4, tag5) VALUES(?, ?, ?, ?, ?, ?, ?, ?)")) {
			ps.setString(1, product.getImgSrc());
			ps.setString(2, product.getName());
			ps.setInt(3, product.getPrice());
			ps.setString(4, product.getTag1());
			ps.setString(5, product.getTag2());
			ps.setString(6, product.getTag3());
			ps.setString(7, product.getTag4());
			ps.setString(8, product.getTag5());
			ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(db);
		}
		return result;
	}
	
	public int count() {
		Connection db = this.createConnection();
		//PreparedStatement ps = null;
		int result = 0;
		try (PreparedStatement ps = db.prepareStatement("SELECT COUNT(*) AS COUNT FROM producttbl")) {
			//ps.executeUpdate();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(db);
		}
		return result;
	}
	
	public String standardFind(String keyword) {
		Connection db = this.createConnection();
		PreparedStatement ps = null;
		String result = null;
		try {
			ps = db.prepareStatement("SELECT * FROM producttbl WHERE name LIKE '%?%'");
			ps.setString(1, keyword);
			ResultSet rst = ps.executeQuery();
			if (rst.next()) {
				result = rst.getString("realName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
			}
			this.closeConnection(db);
		}

		return result;
	}
	public String findWithTags(String tag) {
		Connection db = this.createConnection();
		PreparedStatement ps = null;
		String result = null;
		try {
			ps = db.prepareStatement("SELECT * FROM producttbl WHERE tag1 = ? OR tag2 = ? OR tag3 = ? OR tag4 = ? OR tag5 = ?");
			ps.setString(1, tag);
			ps.setString(2, tag);
			ps.setString(3, tag);
			ps.setString(4, tag);
			ps.setString(5, tag);
			ResultSet rst = ps.executeQuery();
			if (rst.next()) {
				result = rst.getString("realName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
			}
			this.closeConnection(db);
		}

		return result;
	}
	
	public String[] getInformation(int id, int searchNo, String keyword, String tag) {
		id += 1;
		
		Connection db = this.createConnection();
		PreparedStatement ps = null;
		String[] result = new String[8];
		try {
			switch (searchNo) {
				case 0:
					//通常抽出
					ps = db.prepareStatement("SELECT * FROM producttbl WHERE productId = ?");
					break;
				case 1:
					//検索キーワードで抽出
					ps = db.prepareStatement("SELECT * FROM producttbl WHERE productId = ? AND name LIKE '%?%'");
					ps.setString(2, keyword);
					break;
				case 2:
					//タグで抽出
					ps = db.prepareStatement("SELECT * FROM producttbl WHERE productId = ? AND (tag1 = ? OR tag2 = ? OR tag3 = ? OR tag4 = ? OR tag5 = ?)");
					ps.setString(2, tag);
					ps.setString(3, tag);
					ps.setString(4, tag);
					ps.setString(5, tag);
					ps.setString(6, tag);
					break;
			}
			ps.setInt(1, id);
			ResultSet rst = ps.executeQuery();
			if (rst.next()) {
				result[0] = rst.getString("imgSrc");
				result[1] = rst.getString("name");
				result[2] = String.valueOf(rst.getInt("price"));
				result[3] = rst.getString("tag1");
				result[4] = rst.getString("tag2");
				result[5] = rst.getString("tag3");
				result[6] = rst.getString("tag4");
				result[7] = rst.getString("tag5");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
			}
			this.closeConnection(db);
		}
		
		//価格の整理
		if (result[2] == null) {
			result[2] = "0";
		}
		
		return result;
	}
}
