package jp.ac.o_hara.product;
import java.io.Serializable;

import jp.ac.o_hara.site.user.UserDAO;

public class ProductBean implements Serializable {
	private String imgSrc = "";
	private String name = "";
	private int price = 0;
	private String tag1 = "";
	private String tag2 = "";
	private String tag3 = "";
	private String tag4 = "";
	private String tag5 = "";
	
	public ProductBean() {}
	public ProductBean(String imgSrc, String name, int price, String tag1, String tag2, String tag3, String tag4, String tag5) {
		this.imgSrc = imgSrc;
		this.name = name;
		this.price = price;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.tag3 = tag3;
		this.tag4 = tag4;
		this.tag5 = tag5;
	}
	
	//画像ソース
	public void setImgSrc(String imgSrc) { this.imgSrc = imgSrc; }
	public String getImgSrc() { return this.imgSrc; }
	
	//商品名
	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }
	
	//価格
	public void setPrice(int price) { this.price = price; }
	public int getPrice() { return this.price; }
	
	//タグA
	public void setTag1(String tag1) { this.tag1 = tag1; }
	public String getTag1() { return this.tag1; }
	//タグB
	public void setTag2(String tag2) { this.tag2 = tag2; }
	public String getTag2() { return this.tag2; }
	//タグC
	public void setTag3(String tag3) { this.tag3 = tag3; }
	public String getTag3() { return this.tag3; }
	//タグD
	public void setTag4(String tag4) { this.tag4 = tag4; }
	public String getTag4() { return this.tag4; }
	//タグE
	public void setTag5(String tag5) { this.tag5 = tag5; }
	public String getTag5() { return this.tag5; }
	
	//件数
	public int getCount() {
		ProductDAO dao = ProductDAO.getInstance();
		return dao.count();
	}
	//No.〇〇〇の情報を取得
	public String[] getInformation(int id) {
		ProductDAO dao = ProductDAO.getInstance();
		return dao.getInformation(id, 0, "", "");
	}
	//No.〇〇〇で検索キーワードが入っている情報を取得
	public String[] search(int id, String keyword) {
		ProductDAO dao = ProductDAO.getInstance();
		return dao.getInformation(id, 1, keyword, "");
	}
	//No.〇〇〇で検索タグが入っている情報を取得
	public String[] searchTag(int id, String tag) {
		ProductDAO dao = ProductDAO.getInstance();
		return dao.getInformation(id, 2, "", tag);
	}
}