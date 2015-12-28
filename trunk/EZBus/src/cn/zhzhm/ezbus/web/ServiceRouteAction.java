package cn.zhzhm.ezbus.web;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import cn.zhzhm.ezbus.db.bo.ServiceRoute;

@Controller
@SessionAttributes
public class ServiceRouteAction {
	private List<ServiceRoute> routeList;
	private static SqlSessionFactory sqlMapper;
	static{
		try{
			Reader reader = Resources.getResourceAsReader("MybatisConf.xml");
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);

		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(value = "/genRouteList")
	public @ResponseBody List<ServiceRoute> genRouteList(){
		SqlSession sqlSession = sqlMapper.openSession();
		List<ServiceRoute> routes = sqlSession.selectList("selectAll");
		System.out.println(routes.size());
		return routes;
	}
	
	public List<ServiceRoute> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<ServiceRoute> routeList) {
		this.routeList = routeList;
	}
	
}
