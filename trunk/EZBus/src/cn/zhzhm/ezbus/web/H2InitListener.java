package cn.zhzhm.ezbus.web;

import java.io.Reader;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.tools.Server;

import cn.zhzhm.ezbus.db.bo.ServiceRoute;

/**
 * Application Lifecycle Listener implementation class H2InitListener
 * 
 */
@WebListener
public class H2InitListener implements ServletContextListener {
	private Server server;
	/**
	 * Default constructor.
	 */
	public H2InitListener() {
		super();
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			org.apache.ibatis.logging.LogFactory.useLog4JLogging(); 
			server = Server.createTcpServer();
			server.start();
//			JdbcConnectionPool cp = JdbcConnectionPool.create(
//					"jdbc:h2:~/test", "sa", "");
//			Connection conn = cp.getConnection();
//			Statement sql = conn.createStatement();
//			sql.execute("create table service_route (id int(18),project_name varchar(20), service_name varchar(20), service_url varchar(256))");
//			Reader reader = Resources.getResourceAsReader("MybatisConf.xml");
//			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder()
//					.build(reader);
//			SqlSession sqlSession = sqlMapper.openSession();
//			ServiceRoute sr = new ServiceRoute();
//			sr.setid(0l);
//			sr.setprojectName("p1");
//			sr.setserviceName("s1");
//			sr.setserviceUrl("http://localhost:8080/AService");
//			sqlSession.insert("insert",sr);
//			sqlSession.commit();
			

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		if(server != null){
			server.shutdown();
		}
	}

}
