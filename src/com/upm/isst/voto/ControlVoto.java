package com.upm.isst.voto;



import java.io.IOException;  
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.*;
import javax.servlet.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.upm.isst.voto.dao.CEEDAO;
import com.upm.isst.voto.dao.CEEDAOImpl;
import com.upm.isst.voto.dao.PoliticosDAO;
import com.upm.isst.voto.dao.PoliticosDAOImpl;
import com.upm.isst.voto.model.PoliticosModel;

@SuppressWarnings("serial")
public class ControlVoto extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	
		String provincia = (String) req.getAttribute("provincia");
		int numeroPoliticos = (int) req.getAttribute("numeroPoliticos");
		resp.setContentType("text/plain");

		PoliticosDAO dao = PoliticosDAOImpl.getInstance();
		/*dao.create((long) 5555, "Pablo Iglesias", "Madrid", "Podemos");
		dao.create((long) 6666, "Rajoy",  "Salamanca", "PP");
		dao.create((long) 7777, "Sanchez",  "Madrid", "PSOE");
		dao.create((long) 8888, "Albert Rivera", "Madrid", "Ciudadanos";*/
		
		List<PoliticosModel> politicos = dao.readProvincia(provincia);
		
		//for (PoliticosModel politico: politicos){
			
		req.setAttribute("candidatos", politicos);
		req.setAttribute("numeroCandidatos", numeroPoliticos);
		RequestDispatcher rd = req.getRequestDispatcher("/Votar.jsp");
		rd.forward(req,resp);	
		//}
		
		//resp.setContentType("text/plain");
		//req.setAttribute("provincia", provincia);
		//resp.sendRedirect("votar.jsp");
	
	}
}
	