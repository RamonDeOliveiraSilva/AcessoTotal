package br.com.acessoTotal;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class DenunciaServlet extends HttpServlet {

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// instanciando o Gson e o JDO
		Gson gson = new Gson();
		JDOUtils jdo = new JDOUtils();

		// pegando o json do req pelo parametro "data" e salvando na String data
		String data = req.getParameter("data");

		// gson converte a string data em um objeto d do tipo denuncia
		Denuncia d = gson.fromJson(data, Denuncia.class);

		if (d == null || d.getUsuario() == null
				|| d.getNomeDaDenuncia() == null || d.getDescricao() == null) {
			resp.getWriter().println(
					"ERRO: nao foi possivel cadastrar a denuncia");
		} else {
			List<Denuncia> dLista = jdo.findByAttribute(Denuncia.class, "id",
					d.getId());
			if (dLista.isEmpty()) {
				jdo.save(d);
				resp.getWriter().println(gson.toJson(d));
			} else {
				resp.getWriter().println("Denuncia ja cadastrada");
			}
		}

	}

}
