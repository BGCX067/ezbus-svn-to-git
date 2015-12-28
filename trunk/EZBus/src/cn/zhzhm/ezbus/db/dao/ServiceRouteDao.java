package cn.zhzhm.ezbus.db.dao;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import cn.zhzhm.ezbus.db.bo.ServiceRoute;


public class ServiceRouteDao {
	private static Logger logger = Logger.getLogger(ServiceRouteDao.class);
	private static SqlSessionFactory sqlMapper;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("MybatisConf.xml");
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static SqlSessionFactory getSqlSessionFactory(){
		return sqlMapper;
	}
	public String getServiceUrlByServiceName(String serviceName){
		SqlSession session = sqlMapper.openSession();
	
		String name = session.selectOne("selectUrlBySName",serviceName);
		session.close();
		return name;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServiceRouteDao srd = new ServiceRouteDao();
		System.out.println(srd.getServiceUrlByServiceName("org.zzm.isb.srv.demo.DemoService"));

	}

}
