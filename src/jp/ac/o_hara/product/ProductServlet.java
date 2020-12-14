package jp.ac.o_hara.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ac.o_hara.site.ContentBean;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* 商品名 */
		int productId = Integer.parseInt(req.getParameter("productId"));
		req.setAttribute("productId", productId);
		/*
		SELECT 商品名、価格、タグA、タグB、タグC、タグD、タグE
		FROM 商品テーブル
		WHERE 商品ID = 指定商品ID
		*/
		
		ContentBean content = (ContentBean)req.getAttribute("content");
		content.setContent("/WEB-INF/jsp/Product/content.jsp");
		req.setAttribute("content", content);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
	}
}