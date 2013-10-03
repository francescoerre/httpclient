import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;


public class MyHttpClient2 {
	
	private static final String PROTOCOL="http";
	private static final String HOST="localhost";
	private static final int PORT=8080;
	
	
	
	public static void main(String [] args){
		String baseUrl = PROTOCOL+"://"+HOST+":"+PORT+"/MyHttpServlet";
    	
    	/*GET JSESSIONID TO GENERATE SAME SESSION*/
//    	String jsessionid=CommonUtility.getJSESSIONID(request);
		HttpClient httpclient = null;
		HttpPost httpget = null;
		System.out.println("Preparo il metodo GET");
		URIBuilder builder = new URIBuilder();
		builder.setScheme(PROTOCOL).setHost(HOST).setPort(PORT).setPath("/MyHttpServlet/MyServlet")
		    .setParameter("name", MyHttpClient2.class.getName())
		    .setParameter("date", String.valueOf(GregorianCalendar.getInstance().getTimeInMillis()));
		try {
			URI uri = builder.build();
			httpget = new HttpPost(uri);
			
			
	    	//HttpGet method = new HttpGet(baseUrl+"/MyServlet");
	    	
	    	//HttpParams params = method.getParams();
			HttpParams params = httpget.getParams();
		
			//params.setParameter("name", MyHttpClient.class.getName());
			System.out.println("Parameter: name="+params.getParameter("name"));
			//params.setParameter("date", GregorianCalendar.getInstance().getTimeInMillis());
			System.out.println("Parameter: date="+params.getParameter("date"));
			
			//method.setParams(params);
			
	//		method.setRequestHeader("Cookie", "JSESSIONID="+jsessionid);
	
			System.out.println("Instanzio un http client");
			httpclient = new DefaultHttpClient();

		
//			System.out.println("Invoco la servlet: "+method.getURI());
			System.out.println("Invoco la servlet: "+httpget.getURI());
			
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			
			System.out.println("Risposta: "+EntityUtils.toString(entity));
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            httpget.releaseConnection();
            httpclient.getConnectionManager().shutdown();
        }
	}

}
