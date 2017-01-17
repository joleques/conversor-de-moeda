package conversor.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LeitorCSV {

	private static final String CARACTER_PARA_SPLIT = ";";
	

	public void read(String quotation){
	  try {
		URL url = new URL("http://www4.bcb.gov.br/Download/fechamento/20170117.csv");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		if (connection.getResponseCode() == 200) {
		  	InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
		    BufferedReader br = new BufferedReader(streamReader);
	        String line = "";
		    while ((line = br.readLine()) != null) {
                String[] country = line.split(CARACTER_PARA_SPLIT);
                System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
            }
		}
	  }catch(Exception ex){
		  
	  }
	}
}
