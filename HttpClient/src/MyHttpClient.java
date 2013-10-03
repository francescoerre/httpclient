import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.GregorianCalendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class MyHttpClient {
	
	private static final String PROTOCOL="http";
	private static final String HOST="localhost";
	private static final int PORT=8080;
	
	public static void main(String [] args){
		
		HttpClient httpclient = null;
		HttpPost httpPost = null;
		
		System.out.println("Preparo il metodo POST");
		URIBuilder builder = new URIBuilder();//Costruzione endpoint servlet con parametri
		builder.setScheme(PROTOCOL).setHost(HOST).setPort(PORT).setPath("/MyHttpServlet/MyServlet")
		    .setParameter("name", MyHttpClient.class.getName())
		    .setParameter("date", String.valueOf(GregorianCalendar.getInstance().getTimeInMillis()));
		
		try {
			URI uri = builder.build();
			httpPost = new HttpPost(uri);
			
			System.out.println("Instanzio un http client");
			httpclient = HttpClients.createDefault();

			System.out.println("Invoco la servlet: "+httpPost.getURI());
			
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			
			System.out.println("Risposta: "+EntityUtils.toString(entity));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
            httpPost.releaseConnection();
        }
	}
}
