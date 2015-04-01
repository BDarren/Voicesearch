import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;


public class getSynonyms {
	
	public List<String> getSynonyms1(String wordForm){
		List<String> res = new ArrayList<String>();
		//  Concatenate the command-line arguments
		System.setProperty("wordnet.database.dir", "D:\\Applications\\WordNet\\2.1\\dict");
		WordNetDatabase database = WordNetDatabase.getFileInstance();
		Synset[] synsets = database.getSynsets(wordForm);
		//  Display the word forms and definitions for synsets retrieved
		if (synsets.length > 0)
		{
			for (int i = 0; i < synsets.length; i++)
			{
				System.out.println("");
				String[] wordForms = synsets[i].getWordForms();
				for (int j = 0; j < wordForms.length; j++)
				{
					res.add(wordForms[j]);
				}
			}
		}
		else
		{
			return null;
		}
		
		List<String> depduperes = new ArrayList<>(new LinkedHashSet<>(res));
        return depduperes;
		
	}

}
