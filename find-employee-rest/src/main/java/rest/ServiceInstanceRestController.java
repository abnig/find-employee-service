package rest;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping(value = "/service-instances", produces = "application/json")
public class ServiceInstanceRestController {

	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private EurekaClient eurekaClient;

	@RequestMapping("/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
		List<ServiceInstance> serviceInstances = this.discoveryClient.getInstances(applicationName); 
		Collections.rotate(serviceInstances, 1);//serviceInstances.stream().filter(o -> o.)
		return serviceInstances;
		
	}
	
	@RequestMapping("eureka/{applicationName}")
	public InstanceInfo instanceInfoByApplicationName(@PathVariable String applicationName) {
		InstanceInfo instanceInfo = this.eurekaClient.getNextServerFromEureka(applicationName, false);
		return instanceInfo;
	}
	
	@RequestMapping("/services")
	public List<String> services() {
		return this.discoveryClient.getServices();
	}
	
	@RequestMapping("/description")
	public String description() {
		return this.discoveryClient.description();
	}
}