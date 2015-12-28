package cn.zhzhm.ezbus.srv;

public interface IServiceBusCaller {
	Object invoke(String serviceName, String methodName, Object[] args);
}
