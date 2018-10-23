package rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import model.Employee;
import model.dao.service.EmployeeRepositoryService;

@RestController
@RequestMapping(value = "/employee", produces = "application/json")
public class EmployeeController {

	@Autowired
	private EmployeeRepositoryService employeeRepositoryServiceImpl;

	@HystrixCommand(fallbackMethod = "reliableFindById")
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Employee findById(@PathVariable(value = "id") Long id) {
		System.out.println("Recieved ID:" + id);
		return this. employeeRepositoryServiceImpl.findById(id);
	}

	public Employee reliableFindById(Long id) {
		Employee e =  new Employee();
		e.setDepartment("failover");
		e.setFirstName("failover");
		e.setCircuitBreakerResponseIndicator(Boolean.TRUE);
		return e;
	}
	
	@HystrixCommand(fallbackMethod = "reliableFindAll")
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee> getAll() {
		return this. employeeRepositoryServiceImpl.findAll();
	}
	
	public List<Employee> reliableFindAll() {
		Employee e =  new Employee();
		e.setDepartment("failover");
		e.setFirstName("failover");
		e.setCircuitBreakerResponseIndicator(Boolean.TRUE);
		List<Employee> list = new ArrayList<>();
		list.add(e);
		return list;
	}
	
}