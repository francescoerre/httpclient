import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println("Ricevuto: "+name);
		String dateParam = request.getParameter("date");
		
		long date = Long.parseLong(dateParam);
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.write("Ciao "+name+"\n");
		out.write("Richiesta ricevuta il: "+new Date(date));
		
		out.flush();
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
