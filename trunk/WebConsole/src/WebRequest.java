

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Hashtable;


/**
 * Simple class to make HTTP requests using standar java.net package
 * @author Pello Xabier Altadill Izura
 * @greetz 4 u
 */
public class WebRequest {
	private String userAgent;
	private String responseString;
	private int responseCode;
	private String exceptionMessage;
	private Hashtable<String,String> cookies;
	
	/**
	 * default constructor
	 */
	public WebRequest () {
		this.userAgent = "EvilBlackDeathOfDoom browser v1.0";
		cookies = new Hashtable<String,String>();
	}

	/**
	 * makes GET request to URL
	 * @param urlString to request
	 * @return true if everything went fine, false otherwise
	 */
	public boolean get (String urlString) {
		boolean result = false;
		responseString = "";
		exceptionMessage = "";
		String line = "";
		
		
		try {
				// Create an URL instance
			   URL url = new URL(urlString);
			   
		       // Create the HttpConnection
			   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		       connection.setRequestProperty("User-Agent", userAgent); 
		       connection.setRequestMethod("GET");
		       setCookies(connection);

		        // Get input stream from server
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                                    connection.getInputStream()));
		    // Read response from server
		    while ((line = in.readLine()) != null) {
		    	responseString += line;
		    }
		    
		    in.close();
		return true;
		
	    } catch (IOException e) {
		  exceptionMessage = e.getMessage();
		  e.printStackTrace();
		} catch (Exception e) {
		  exceptionMessage = e.getMessage();	    	
		}
		
		return false;
		
	}
	
	/**
	 * makes POST request to URL
	 * @param url to request
	 * @param parameters for POST
	 * @return true if everything went fine, false otherwise
	 */
	public boolean post (String urlString, Hashtable<String,String> parameters) {
		boolean result = false;
	    String line = "";
	    String postString = "";
	    String parameterValue = "";
		responseString = "";
		exceptionMessage = "";

			try {

		        URL url = new URL(urlString);
		        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		        connection.setRequestMethod("POST");
		        connection.setRequestProperty("User-Agent", userAgent); 
		        connection.setDoOutput(true);
		        setCookies(connection);

		        OutputStreamWriter output = new OutputStreamWriter(
		                                         connection.getOutputStream());
		        
		        
		        // We set parameters one by one
			    for (String parameterName : parameters.keySet()) {
			    	parameterValue = URLEncoder.encode(parameters.get(parameterName),"UTF-8");
			    	postString += parameterName + "=" +parameterValue +"&";
			     }
			    
			    output.write(postString);
		        output.close();

		        // Now we get the response
		        BufferedReader in = new BufferedReader(
		                                    new InputStreamReader(
		                                    connection.getInputStream()));
		        
		      getCookies(connection);
		      responseCode = connection.getResponseCode();
		      
		      while ((line = in.readLine()) != null) {
		        responseString += line;
		      }
		      in.close();
		     return true;
		     
		    } catch (IOException e) {
		      exceptionMessage = e.getMessage();
		      e.printStackTrace();
		    } catch (Exception e) {
			  exceptionMessage = e.getMessage();	    	
		      e.printStackTrace();
		    }
			return false;
	}
	
	/**
	 * sends previously saved cookies to server.
	 * This method restores cookie name=value pairs from cookies hashtable
	 * and puts them in request header:
	 *  cookiename1=value1; cookiename2=value2;..
	 * @param connection
	 */
	private void setCookies(HttpURLConnection connection) {
		String cookieString = "";

		// Get cookies name=value pair from hashtable
		for (String cookieName : cookies.keySet()) {
			cookieString += cookieName + "=" + cookies.get(cookieName) + ";";
		}
		
		// and put them in the request header
		System.out.println("Sending cookies to server: " + cookieString);
		connection.setRequestProperty("Cookies", cookieString);
	}

	/**
	 * retrieves Cookies sent by server
	 * Cookies come in this form:
	 *  Set-Cookie: name1=value1;
	 *  Set-Cookie: name2=value2;
	 *  ...
	 * So we have to retrieve every Set-Cookie line and parse cookie name and value
	 * This method stores cookie data in the cookies Hashtable for further use
	 * @param connection
	 */
	private void getCookies(HttpURLConnection connection) {
		String headerName=null;
		String cookieString = "";
        String cookieName = "";
        String cookieValue = "";        
        
		// We look up for Set-Cookie entries in header
		for (int i=1; (headerName = connection.getHeaderFieldKey(i))!=null; i++) {
		 	if (headerName.equals("Set-Cookie")) {                  
		 		cookieString = connection.getHeaderField(i);   
		        cookieString = cookieString.substring(0, cookieString.indexOf(";"));
		        cookieName = cookieString.substring(0, cookieString.indexOf("="));
		        cookieValue = cookieString.substring(cookieString.indexOf("=") + 1, cookieString.length());
		        cookies.put(cookieName, cookieValue);
		        System.out.println("One cookie, mmm yummy: " + cookieName + "=" + cookieValue);
		    }
		}
	}

	/**
	 * @return the userAgent
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * @param userAgent the userAgent to set
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return the responseString
	 */
	public String getResponseString() {
		return responseString;
	}

	/**
	 * @param responseString the responseString to set
	 */
	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}

	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the exceptionMessage
	 */
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	/**
	 * @param exceptionMessage the exceptionMessage to set
	 */
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
