package cn.zhzhm.ezbus.core;

public interface IServiceBusCaller {
	Object invoke(String serviceName, String methodName, Object[] args);
}
