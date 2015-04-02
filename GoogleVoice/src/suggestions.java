import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author canpan
 */
public class suggestions {
    public List<String> getSuggestionList(String keyword){
        List<String> suggestionList=new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(keyword);
        String q=null;
        while (st.hasMoreElements()) {
            if(q==null){
                q=(String) st.nextElement();
            }
            else{
                q=q+"+"+(String) st.nextElement();
            }
                
			
		}
        //System.out.println(q);
        try {
        String urlName = "http://google.com/complete/search?q=" + q + "&output=toolbar";
        System.out.println(urlName);
        URL url = new URL(urlName);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty(
                "User-Agent",
                "Mozilla/5.0 (X11; U; Linux x86_64; en-GB; rv:1.8.1.6) Gecko/20070723                       Iceweasel/2.0.0.6 (Debian-2.0.0.6-0etch1)");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = in.readLine();
        in.close();
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(line);
        while (m.find()) {
            suggestionList.add(m.group(1));
            System.out.println(m.group(1));
        }
        suggestionList.remove(0);
        //System.out.println(line);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
        return suggestionList;
    }
    
}
