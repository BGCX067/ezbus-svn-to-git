package cn.zhzhm.ezbus.web;

import java.io.Reader;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import cn.zhzhm.ezbus.db.bo.ServiceRoute;
import cn.zhzhm.ezbus.db.dao.ServiceRouteDao;

@Path("/router/")
public class ServiceRouteSer {
	private static Logger logger = Logger.getLogger(ServiceRouteSer.class);

	@GET
	@Produces("application/json")
	@Path("list/")
	public List<ServiceRoute> list(String serviceName) {
		SqlSession sqlSession = ServiceRouteDao.getSqlSessionFactory()
				.openSession();
		List<ServiceRoute> routes;
		routes = sqlSession.selectList("selectAll");
		sqlSession.close();
		return routes;
	}

	@GET
	@Produces("application/json")
	@Path("query/{serviceName}/")
	public List<ServiceRoute> queryByServiceName(
			@PathParam("serviceName") String serviceName) {
		SqlSession sqlSession = ServiceRouteDao.getSqlSessionFactory()
				.openSession();
		List<ServiceRoute> routes;
		ServiceRoute sr = new ServiceRoute();
		logger.debug("seviceName:" + serviceName);
		sr.setserviceName(serviceName);
		routes = sqlSession.selectList("selectByExample", sr);
		sqlSession.close();
		return routes;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("add/")
	public boolean add(ServiceRoute sr) {
		SqlSession sqlSession = ServiceRouteDao.getSqlSessionFactory()
				.openSession();
		long id;
		try {
			id = sqlSession.selectOne("genPrimaryKey");
		} catch (NullPointerException e) {
			id = 1;
		}
		sr.setid(id);
		try {
			int i = sqlSession.insert("insert", sr);
			sqlSession.commit();
			return i == 1;
		} catch (Exception e) {
			return false;
		} finally {
			sqlSession.close();
		}
	}

	@GET
	@Path("delete/{id}/")
	@Produces("application/json")
	public boolean delete(@PathParam("id") Long id) {
		SqlSession sqlSession = ServiceRouteDao.getSqlSessionFactory()
				.openSession();
		try {
			int i = sqlSession.delete("deleteByPrimaryKey", id);
			sqlSession.commit();

			return i == 1;
		} finally {
			sqlSession.close();
		}
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("update/")
	public boolean update(ServiceRoute sr) {
		SqlSession sqlSession = ServiceRouteDao.getSqlSessionFactory()
				.openSession();
		try {
			int i = sqlSession.update("updateByPrimaryKeySelective", sr);
			sqlSession.commit();
			return i == 1;
		} catch (Exception e) {
			return false;
		} finally {
			sqlSession.close();
		}
	}

}