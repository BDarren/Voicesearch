import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class getSyn {
	public List<String> getSynonyms1(String q) throws UnirestException, ParseException{
		List<String> res = new ArrayList<String>();
		
		HttpResponse<JsonNode> response = Unirest.get("https://wikisynonyms.p.mashape.com/"+q)
				.header("X-Mashape-Key", "YNB6lEEFQ9mshetJhpx5xSFS0vJjp1nGjFZjsnvTpf0QvauWt4")
				.header("Accept", "application/json")
				.asJson();
		
		JSONParser parser = new JSONParser();
		String responseJSONString = response.getBody().toString();
		Object obj = parser.parse(responseJSONString);

        JSONObject jsonObject = (JSONObject) obj;

        JSONArray wordList = (JSONArray) jsonObject.get("terms");

        Iterator<JSONObject> iterator = wordList.iterator();
        
        while (iterator.hasNext()) {
        	res.add((String) iterator.next().get("term"));
        }
        System.out.println(res.get(4));
        return res;
		
	}
}
