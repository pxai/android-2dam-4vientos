package org.cuatrovientos.stock.exchange;

import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

/**
 * Gets Stock Exchange data from an url using jsoup
 * @author pello altadill
 * @greetz 
 */
public class App 
{
    public static void main( String[] args )
    {
    	String url = "http://www.eleconomista.es/indice/NASDAQ-100";
    	Document doc = null;
    	Elements tdTags = null;
    	Element td = null;
    	
		try {
			doc = Jsoup.connect(url).get();

	        System.out.println( "Grabbing info" );
	        Elements stocks = doc.select("table.tablalista tbody tr");
	
	        System.out.println("\nTR: " + stocks.size());
	        for (Element stock : stocks) {
	        	tdTags = stock.select("td");
	        	if (tdTags.size()>1) {
	        		String tmp = tdTags.get(0).select("strong a").text() + ": ";
	        		tmp += tdTags.get(1).select("span").text() + ", ";
	        		tmp += tdTags.get(3).select("span").text();
	        		System.out.println(tmp);
	        	}

	        }
        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
