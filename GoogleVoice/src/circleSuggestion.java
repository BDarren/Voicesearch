import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class circleSuggestion extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException, UnirestException, ParseException {
		String sterm = request.getParameter("sterm");
		PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        out.println("<div>"+ sterm +"</div>");
        
        getSynonyms aa = new getSynonyms();
		List<String> res = aa.getSynonyms1(sterm);

        out.println("<html lang='en'><head><link rel='stylesheet' type='text/css' href='circle.css'></head><body>");
        for(int i=0; i<res.size(); i++){
        	out.println("<a href='http://www.google.com/search?q="+res.get(i)+"' class='menu'>"+res.get(i)+"</a></h1>");
        }
        	out.println("</body></html>");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		        try {
					processRequest(request, response);
				} catch (UnirestException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    
		 	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		        try {
					processRequest(request, response);
				} catch (UnirestException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    
		    public String getServletInfo() {
		        return "Short description";
		    }
}
