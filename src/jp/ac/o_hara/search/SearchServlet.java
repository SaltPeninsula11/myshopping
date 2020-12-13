package jp.ac.o_hara.search;

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
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* 検索キーワード */
		String search = req.getParameter("search");
		req.setAttribute("search", search);
		/* タグ */
		String tag = req.getParameter("tag");
		req.setAttribute("tag", tag);
		/* ページ数 */
		int page = Integer.parseInt(req.getParameter("page"));
		req.setAttribute("page", page);
		
		ContentBean content = (ContentBean)req.getAttribute("content");
		content.setContent("/WEB-INF/jsp/Search/content.jsp");
		req.setAttribute("content", content);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
	}
}
