package com.thiagocostafatec.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagocostafatec.employee.model.EmployeeModel;
import com.thiagocostafatec.employee.repository.EmployeeRepository;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeRepository Repository;

	@GetMapping
	public ResponseEntity<List<EmployeeModel>> findAll() {
		List<EmployeeModel> result = Repository.findAll();
		return ResponseEntity.ok(result);

	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<EmployeeModel>> findAll(Pageable pageable) {
		Page<EmployeeModel> result = Repository.findAll(pageable);
		return ResponseEntity.ok(result);

	}

	@PostMapping(value = "/cadastrar")
	public ResponseEntity<EmployeeModel> post(@RequestBody EmployeeModel Employee) {

		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(Repository.save(Employee));
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
