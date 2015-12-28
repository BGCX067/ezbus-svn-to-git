package cn.zhzhm.ezbus.core;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhzhm.ezbus.db.dao.ServiceRouteDao;

public class ResendServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4800732112562307469L;
//	private static Properties serviceProp = new Properties();
//	static {
//		serviceProp.put("org.zzm.isb.srv.demo.DemoService",
//				"http://10.3.161.128:8080/AppServer/hessian/appservice");
//		serviceProp.put("org.zzm.isb.srv.demo.DemoService2",
//				"http://10.3.161.128:8080/AppServer2/hessian/appservice");
//	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException{
		InputStream is = req.getInputStream();
		byte[] by = new byte[1024];
		int length;
		length = is.read(by);
		String name = getName(by);
		ServiceRouteDao srd = new ServiceRouteDao();
		String str = srd.getServiceUrlByServiceName(name);
		URL url = new URL(str);
		HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
		httpConnection.setDoOutput(true);
		httpConnection.setDoInput(true);
		httpConnection.setRequestMethod("POST");
		for(Enumeration<String> enu = req.getHeaderNames(); enu.hasMoreElements();){
			String key = enu.nextElement();
			String value = req.getHeader(key);
			httpConnection.addRequestProperty(key, value);
		}
		OutputStream srvOut = httpConnection.getOutputStream();
		srvOut.write(by,0,length);
		
		while((length = is.read(by))!=-1){
			srvOut.write(by,0,length);
		}
		srvOut.flush();
		srvOut.close();
		InputStream srvIn = httpConnection.getInputStream();
		OutputStream out = resp.getOutputStream();
		while((length = srvIn.read(by))!=-1){
			out.write(by,0,length);
		}
		out.flush();
	}
	private String getName(byte[] buff){
		int counter = 0,start = 0, end =0;
		for(int i = 0; i<buff.length; i++){
			if(buff[i]==0){
				if(++counter ==3){
					start = i+1;
					break;
				}
			}
		}
		for(int i = start; i<buff.length; i++){
			if(buff[i]==0){
				end = i;
				break;
			}
		}
		
		byte[] bys = new byte[end - start-2];
		System.arraycopy(buff, start+1, bys, 0, bys.length);
		return new String(bys);
	}
}
