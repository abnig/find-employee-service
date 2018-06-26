package rest;

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
public class EmployeeController {

	@Autowired
	private EmployeeRepositoryService employeeRepositoryService;

	@HystrixCommand(fallbackMethod = "reliable")
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Employee greeting(@PathVariable(value = "id") Long id) {
		System.out.println("Recieved ID:" + id);
		return this. employeeRepositoryService.findById(id);
	}

	public Employee reliable(Long id) {
		Employee e =  new Employee();
		e.setDepartment("failover");
		e.setFirstName("failover");
		return e;
	}
}