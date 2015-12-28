package cn.zhzhm.ezbus.core;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import com.caucho.hessian.client.HessianProxyFactory;

public class HessianServiceCaller implements IServiceBusCaller {
	private static Properties serviceProp = new Properties();
	private static HessianProxyFactory factory = new HessianProxyFactory();
	private static HashMap<String, IServiceBusCaller> callerMap = new HashMap<String, IServiceBusCaller>();
	static {
		serviceProp.put("org.zzm.isb.srv.demo.DemoService",
				"http://10.3.161.128:8080/AppServer/hessian/appservice");
		serviceProp.put("org.zzm.isb.srv.demo.DemoService2",
				"http://10.3.161.128:8080/AppServer2/hessian/appservice");
		for(Enumeration<Object> e = serviceProp.keys(); e.hasMoreElements();){
			String key = e.nextElement().toString();
			String url = serviceProp.getProperty(key);
			try{
				IServiceBusCaller caller = (IServiceBusCaller) factory.create(
						IServiceBusCaller.class, url);
				callerMap.put(key, caller);
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Object invoke(String serviceName, String methodName, Object[] args) {
		try {
			
			// 取服务实际地址
//			String url = serviceProp.getProperty(serviceName, "");
//			System.out.println("in service bus:"+serviceName+",url:"+url);
			IServiceBusCaller caller = callerMap.get(serviceName);
			return caller.invoke(serviceName, methodName, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
