import java.util.List;

import org.json.simple.parser.ParseException;

import com.mashape.unirest.http.exceptions.UnirestException;


public class test {

	public static void main(String[] args) throws UnirestException, ParseException {
		// TODO Auto-generated method stub
		getSynonyms aa = new getSynonyms();
		aa.getSynonyms1("cplusplus");
	}

}
