package jp.ac.o_hara.template;

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
@WebServlet("/template")
public class TemplateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ContentBean content = (ContentBean)req.getAttribute("content");
		content.setContent("/WEB-INF/jsp/Template/content.jsp");
		req.setAttribute("content", content);
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
	}
}