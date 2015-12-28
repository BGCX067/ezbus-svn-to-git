package cn.zhzhm.ezbus.srv;

import java.lang.reflect.Method;
import java.util.HashMap;

public class HessianServiceCaller implements IServiceBusCaller {
	private static HashMap<String, Object> objMap = new HashMap<String, Object>();
	
	@Override
	public Object invoke(String serviceName, String methodName, Object[] args) {
		try{
			Class<?>[] argType = new Class<?>[args.length];
			for(short i = 0 ;i <args.length; i++){
				argType[i] = args[i].getClass();
			}
			Class<?> clazz = Class.forName(serviceName);
			Object service;
			if(objMap.containsKey(serviceName)){
				service = objMap.get(serviceName);
			}else{
				synchronized (objMap) {
					if(objMap.containsKey(serviceName)){
						service = objMap.get(serviceName);
					}else{
						service = clazz.newInstance();
						objMap.put(serviceName, service);
					}
				}
			}
			Method method = clazz.getDeclaredMethod(methodName, argType);
			return method.invoke(service, args);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
