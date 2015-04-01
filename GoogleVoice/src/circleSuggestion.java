import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
		
		String sterm = request.getParameter("final_span");
		sterm = sterm.trim();
		PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        //out.println("<div>"+ sterm +"</div>");
        
        getSynonyms aa = new getSynonyms();
		List<String> res = aa.getSynonyms1(sterm);
		
	      
        out.println("<html lang='en'><head><link rel='stylesheet' type='text/css' href='circle.css'><link href='http://getbootstrap.com/dist/css/bootstrap.min.css' rel='stylesheet'></head><body bgcolor='#eee' style='margin: 60px;'><div class='content'><h1>Suggestion</h1><hr>");
        out.println("<h3>User Input:   <a href='http://www.google.com/search?q="+sterm+"'><kbd>"+sterm+"</kbd></a></h3>");
        out.println("<a class='btn btn-default' href='index.jsp'>Return to search homepage &raquo;</a>");
        out.println("<div class = 'tablestyle'><table><tr>");
        

        if (res != null && !res.isEmpty())
        {	
    	    for(int i=0; i<Math.min(res.size(), 6); i++){
        		if(i==3)
        		{
        			out.println("</tr><tr>");
        		}
        		out.println("<th><a href='http://www.google.com/search?q="+res.get(i)+"' class='menu'><span class='tempspan'>"+(i+1)+" . "+res.get(i)+"</span></a></th>");
    	    }      	
        }
        else
        {
        	out.println("<th><a href='index.jsp' class='menu'><span class='tempspan'>No Suggestions</span></a></th>");
        }
        out.println("</tr></table></div></div>");
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
