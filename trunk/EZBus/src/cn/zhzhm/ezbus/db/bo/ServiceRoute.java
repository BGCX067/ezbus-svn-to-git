package cn.zhzhm.ezbus.db.bo;

public class ServiceRoute {
	private Long id;

	public Long getid() {
		return this.id;
	}

	public void setid(Long id) {
		this.id = id;
	}

	private String projectName;

	public String getprojectName() {
		return this.projectName;
	}

	public void setprojectName(String projectName) {
		this.projectName = projectName;
	}

	private String serviceName;

	public String getserviceName() {
		return this.serviceName;
	}

	public void setserviceName(String serviceName) {
		this.serviceName = serviceName;
	}

	private String serviceUrl;

	public String getserviceUrl() {
		return this.serviceUrl;
	}

	public void setserviceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
}
