package jp.ac.o_hara.site.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.ac.o_hara.site.SimpleDAO;

public class UserDAO extends SimpleDAO {
	// Singletonパターン（GoFデザインパターン）
	// 用途：一つのインスタンスを共有する、複数のインスタンス生成を認めない
	private static UserDAO dao = new UserDAO(); // 唯一のインスタンス
	
	private UserDAO() {} // privateにすることで、外部からアクセスできなくなる
	
	public static UserDAO getInstance() { // staticメソッドでインスタンス（へのポインタ）を得る
		return dao;
	}
	
	public int count() {
		Connection db = this.createConnection();
		//PreparedStatement ps = null;
		int result = 0;
		try (PreparedStatement ps = db.prepareStatement("SELECT COUNT(*) AS COUNT FROM usertbl")) {
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
	
	public boolean create(UserBean user) {
		Connection db = this.createConnection();
		//PreparedStatement ps = null;
		boolean result = false;
		try (PreparedStatement ps = db.prepareStatement("INSERT INTO usertbl(realName, userID, passwd, money, point) VALUES(?, ?, ?, ?, ?)")) {
			ps.setString(1, user.getRealName());
			ps.setString(2, user.getUserId());
			ps.setString(3, user.getPass());
			ps.setInt(4, user.getMoney());
			ps.setInt(5, user.getPoint());
			ps.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConnection(db);
		}
		return result;
	}

//	public String find(String id, String pass) {
//		return "ほげ";
//	}


	public String find(String userId, String pass) {
		Connection db = this.createConnection();
		PreparedStatement ps = null;
		String result = null;
		try {
			ps = db.prepareStatement("SELECT * FROM usertbl WHERE userID=? AND passwd=?");
			ps.setString(1, userId);
			ps.setString(2, pass);
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
	
	public String getRealName(String userId) {
		Connection db = this.createConnection();
		PreparedStatement ps = null;
		String result = "";
		try {
			ps = db.prepareStatement("SELECT realName FROM usertbl WHERE userID=?");
			ps.setString(1, userId);
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
	public int getMoney(String userId) {
		Connection db = this.createConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = db.prepareStatement("SELECT money FROM usertbl WHERE userID=?");
			ps.setString(1, userId);
			ResultSet rst = ps.executeQuery();
			if (rst.next()) {
				result = rst.getInt("money");
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
	public int getPoint(String userId) {
		Connection db = this.createConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = db.prepareStatement("SELECT point FROM usertbl WHERE userID=?");
			ps.setString(1, userId);
			ResultSet rst = ps.executeQuery();
			if (rst.next()) {
				result = rst.getInt("point");
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
	
	public void userUpdate(String userId, int money, int point) {
		Connection db = this.createConnection();
		PreparedStatement ps = null;
		try {
			ps = db.prepareStatement("UPDATE usertbl SET money = ?, "
					+ "point = ? WHERE userID = ?");
			ps.setInt(1, money);
			ps.setInt(2, point);
			ps.setString(3, userId);
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
	}

}
