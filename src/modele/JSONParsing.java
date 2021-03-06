package modele;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.sql.Date;
import org.json.JSONArray;
import org.json.JSONObject;


public class JSONParsing {
	
	public static void updateDatabase() {
		updateDatabaseWithURL("http://api.football-data.org/v2/competitions/FL1/matches");
		updateDatabaseWithURL("http://api.football-data.org/v2/competitions/PL/matches");
		updateDatabaseWithURL("http://api.football-data.org/v2/competitions/SA/matches");
		updateDatabaseWithURL("http://api.football-data.org/v2/competitions/BL1/matches");
		updateDatabaseWithURL("http://api.football-data.org/v2/competitions/PPL/matches");
		updateDatabaseWithURL("http://api.football-data.org/v2/competitions/PD/matches");
	}
	
	public static void updateDatabaseWithURL(String apiUrl) {
		
		try
		{
			URL url = new URL(apiUrl);
			//Parse URL into HttpURLConnection in order to open the connection in order to get the JSON data
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			//Set the request to GET or POST as per the requirements
			conn.setRequestMethod("GET");
			//Set the header property with api token
			conn.setRequestProperty("X-Auth-Token", "50d17d77916b4129aada257cf1cb242d");
			//Use the connect method to create the connection bridge
			conn.connect();
			//Get the response status of the Rest API
			int responsecode = conn.getResponseCode();
			System.out.println("Response code is: " + responsecode);
			
			//Iterating condition to if response code is not 200 then throw a runtime exception
			//else continue the actual process of getting the JSON data
			if(responsecode != 200)
				throw new RuntimeException("HttpResponseCode: " +responsecode);
			else
			{	
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();
				System.out.println("\nJSON Response : \n"); 
				//System.out.println(content);
				
				JSONObject obj = new JSONObject(content.toString());
				JSONArray matches = obj.getJSONArray("matches");
				for (int i = 0; i < matches.length(); i++) {
					Rencontre r = new Rencontre();
					//id -> auto
					r.setId(i);
					
					r.setCompetition(obj.getJSONObject("competition").getString("name"));
					r.setTour(String.valueOf(matches.getJSONObject(i).getInt("matchday")));
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss'Z'");
					r.setDate(new Date(sdf.parse(matches.getJSONObject(i).getString("utcDate")).getTime()));
					
					r.setStatus(matches.getJSONObject(i).getString("status"));
					
					r.setHomeTeam(matches.getJSONObject(i).getJSONObject("homeTeam").getString("name"));
					r.setAwayTeam(matches.getJSONObject(i).getJSONObject("awayTeam").getString("name"));
					
					if(r.getStatus().equals("FINISHED"))
					{
						r.setScoreHomeTeam(matches.getJSONObject(i).getJSONObject("score").getJSONObject("fullTime").getInt("homeTeam"));
						r.setScoreAwayTeam(matches.getJSONObject(i).getJSONObject("score").getJSONObject("fullTime").getInt("awayTeam"));
					} else {
						r.setScoreHomeTeam(new Integer(0));
						r.setScoreAwayTeam(new Integer(0));
					}
					
					switch (TraitementRencontre.isRencontreAlreadyIn(r)) {
					case Missing:
						TraitementRencontre.ajouterRencontre(r);
						System.out.println("MISSING");
						break;
					case NeedUpdate:
						TraitementRencontre.modifierRencontre(r);
						System.out.println("UPDATE");
						break;
					case AlreadyIn:
						System.out.println("RIEN");
						break;
					default:
						break;
					}
					
					System.out.println(r.toString());
				}
			}
			
			//Disconnect the HttpURLConnection stream
			conn.disconnect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/*public static void main(String[] args) {
		JSONParsing.updateDatabase();
	}*/
	
}