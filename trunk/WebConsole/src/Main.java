import java.util.Hashtable;




public class Main {
	public static void main (String args[]) {
		WebRequest webRequest = new WebRequest();
		
		if (webRequest.get("http://192.168.77.104/labs/Ejemplo1DAM/index.php")) {
			System.out.println("OK: " + webRequest.getResponseString());
		} else {
			System.out.println("Error: " + webRequest.getExceptionMessage());			
		}
		
		Hashtable<String,String> parameters = new Hashtable<String,String>();
		parameters.put("usuario", "falken");
		parameters.put("password", "josua");
		
		if (webRequest.post("http://192.168.77.104/labs/Ejemplo1DAM/consultarUsuario.php", parameters)) {
			System.out.println("OK login: " + webRequest.getResponseString() + "\n" + webRequest.getResponseCode());
		} else {
			System.out.println("Error: " + webRequest.getExceptionMessage() + "\n" + webRequest.getResponseCode());			
		}
		
		if (webRequest.post("http://192.168.77.104/labs/login/main.php",parameters)) {
			System.out.println("OK: " + webRequest.getResponseString() + "\n" + webRequest.getResponseCode());
		} else {
			System.out.println("Error: " + webRequest.getExceptionMessage() + "\n" + webRequest.getResponseCode());			
		}
	}
}
