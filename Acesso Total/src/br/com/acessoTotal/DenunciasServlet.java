package br.com.acessoTotal;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class DenunciasServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Gson gson = new Gson();
		JDOUtils jdo = new JDOUtils();
		List<Denuncia> denuncias = jdo.findAll(Denuncia.class);
		resp.getWriter().println(gson.toJson(denuncias));
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		JDOUtils jdo = new JDOUtils();

		List<Denuncia> denuncias = jdo.findAll(Denuncia.class);
		for (Denuncia denuncia : denuncias) {
			jdo.delete(denuncia);
		}

	}
}
