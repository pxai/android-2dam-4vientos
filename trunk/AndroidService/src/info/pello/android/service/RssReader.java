package info.pello.android.service;

import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.XmlResourceParser;
import android.renderscript.Element;
import android.util.Log;

/**
 * Rss reader and parser. Two tasks in the same class mmm not too serious
 * @author Pello Xabier Altadill Izura
 * @greetz For any, producer of quality rss resources  
 */
public class RssReader extends Thread {

	private String source;
	
	/**
	 * constructor with rss source argument
	 * @param source
	 */
	public RssReader (String source) {
		this.source = source;
	}
	
	/**
     * cargarXML
     * Carga un contenido XML de un fichero
     * y lo parsea para sacar unos datos concretos
     * @return String
     */
    private Vector<RssData> cargarXML (String xmlcontent) {
    	Vector<RssData> result = new Vector<RssData>();
    	
    	result.add(new RssData("Angular","Angular bla bla","http://angularjs.org"));
    	result.add(new RssData("Backbone","Backbone bla bla","http://backbonejs.org"));
    	result.add(new RssData("Node.js","Nodejs bla bla","http://nodejs.org"));
    	
    	/*
    	DocumentBuilder builder=DocumentBuilderFactory
    	.newInstance()
    	.newDocumentBuilder();
    	Document doc=builder.parse(xmlcontent);
    	NodeList times=doc.getElementsByTagName("start-valid-time");
    	
    	for (int i=0;i<times.getLength();i++) {
    		Element time=(Element)times.item(i);
    		Forecast forecast=new Forecast();
    		forecasts.add(forecast);
    		forecast.setTime(time.getFirstChild().getNodeValue());
    	}
    	
    	NodeList temps=doc.getElementsByTagName("value");
    	for (int i=0;i<temps.getLength();i++) {
    		Element temp=(Element)temps.item(i);
    		Forecast forecast=forecasts.get(i);
    		forecast.setTemp(new Integer(temp.getFirstChild().getNodeValue()));
    	}
    	
    	NodeList icons=doc.getElementsByTagName("icon-link");
    	
    	for (int i=0;i<icons.getLength();i++) {
    		Element icon=(Element)icons.item(i);
    		Forecast forecast=forecasts.get(i);
    		forecast.setIcon(icon.getFirstChild().getNodeValue());
    	}
    	return(forecasts);
    	 */
    	return result;
    }


    /**
     * Makes a GET request to an URL
     * @param url to be requested
     * @return contents from url
     */
    public String get (String url) {
    	// It makes use of apache's HttpClient, available on Android
    	HttpGet request = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		String result = "";
		
    	try {
			ResponseHandler<String> response = new BasicResponseHandler();
			String responseContents = httpClient.execute(request,response);

			if (responseContents != null && responseContents.length() > 0) {
				result += responseContents;
			} else {
				result += "Error\n"+responseContents;
			}

		} catch (ClientProtocolException e) {
			result += "Unexpected ClientProtocolException" + e;
		} catch (IOException e) {
			result += "Unexpected IOException" + e;
		}
		Logger.getInstance().log("RssReader> Result"+ result);
		return result;
    }
}

