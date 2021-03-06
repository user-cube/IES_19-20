package payroll;


import java.util.List;
import java.util.stream.Collectors;

// tag::hateoas-imports[]
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
// end::hateoas-imports[]

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class EmployeeController {

	private final EmployeeRepository repository;

	EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	// tag::get-aggregate-root[]
	@GetMapping(value = "/employees", produces = "application/json; charset=UTF-8")
	Resources<Resource<Employee>> all() {

		List<Resource<Employee>> employees = repository.findAll().stream()
			.map(employee -> new Resource<>(employee,
				linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
				linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
			.collect(Collectors.toList());
		
		return new Resources<>(employees,
			linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
	}
	// end::get-aggregate-root[]

	@PostMapping(value="/employees", produces = "application/json; charset=UTF-8")
	Employee newEmployee(@RequestBody Employee newEmployee) {
		return repository.save(newEmployee);
	}

	// Single item

	// tag::get-single-item[]
	@GetMapping(value="/employees/{id}", produces = "application/json; charset=UTF-8")
	Resource<Employee> one(@PathVariable Long id) {
		
		Employee employee = repository.findById(id)
			.orElseThrow(() -> new EmployeeNotFoundException(id));
		
		return new Resource<>(employee,
			linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
			linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
	}
	// end::get-single-item[]

	@PutMapping(value="/employees/{id}", produces = "application/json; charset=UTF-8")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		
		return repository.findById(id)
			.map(employee -> {
				employee.setName(newEmployee.getName());
				employee.setRole(newEmployee.getRole());
				return repository.save(employee);
			})
			.orElseGet(() -> {
				newEmployee.setId(id);
				return repository.save(newEmployee);
			});
	}

	@DeleteMapping(value="/employees/{id}", produces = "application/json; charset=UTF-8")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
