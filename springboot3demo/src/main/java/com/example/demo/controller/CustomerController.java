package com.example.demo.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerRepository;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/customer/insert")
	public Customer insert() {
		Customer cus1 = new Customer();
		cus1.setName("傲洲來的客人");
		cus1.setLevel(0);

		Customer resCustomer = customerRepository.save(cus1);
		return resCustomer;
	}

	@GetMapping("/customer/saveAll")
	public List<Customer> testSaveAll() {

		Customer c1 = new Customer();
		c1.setName("Mary");
		c1.setLevel(2);

		Customer c2 = new Customer();
		c2.setName("Bill");
		c2.setLevel(2);

		Customer c3 = new Customer();
		c3.setName("文西");
		c3.setLevel(3);

		List<Customer> list = new LinkedList<>();
		list.add(c1);
		list.add(c2);
		list.add(c3);

		List<Customer> resultList = customerRepository.saveAll(list);

		return resultList;
	}

	@GetMapping("/customer/find")
	public Map<String, Object> findById(@RequestParam Integer id) {
		Optional<Customer> optional = customerRepository.findById(id);

		Map<String, Object> resultMap = new HashMap<>();

		if (optional.isPresent()) {
			Customer customer = optional.get();
			resultMap.put("有資料", customer);
			return resultMap;
		}

		resultMap.put("沒資料", "xxx");
		return resultMap;
	}

	@GetMapping("/customer/find2/{id}")
	public Map<String, Object> findById2(@PathVariable Integer id) {
		Optional<Customer> optional = customerRepository.findById(id);

		Map<String, Object> resultMap = new HashMap<>();

		if (optional.isPresent()) {
			Customer customer = optional.get();
			resultMap.put("有資料", customer);
			return resultMap;
		}

		resultMap.put("沒資料", "xxx");
		return resultMap;
	}

//	@RequestMapping(method = RequestMethod.GET, path = "/customer/findAll")
	@GetMapping("/customer/findAll")
	public List<Customer> testFindAll() {
		return customerRepository.findAll();
	}

	@GetMapping("/customer/delete")
	public String testDeleteById(@RequestParam Integer id) {
		customerRepository.deleteById(id);
		return "已刪除";
	}

	@GetMapping("/customer/update")
	public Customer testUpdate(@RequestParam Integer id, @RequestParam Integer level) {
		Optional<Customer> optional = customerRepository.findById(id);

		if (optional.isPresent()) {
			Customer result = optional.get();
			result.setLevel(level);
			// 有 insert or update 的功能
			Customer afterUpdate = customerRepository.save(result);
			return afterUpdate;
		}

		return null;
	}

	@GetMapping("/customer/findPage")
	public Page<Customer> testPage(@RequestParam("p") Integer pageNumber) {
		Pageable pgb = PageRequest.of(pageNumber - 1, 2, Sort.Direction.ASC, "id");
		Page<Customer> page = customerRepository.findAll(pgb);
//		return page.getContent();
		return page;
	}

	@GetMapping("/customer/findName")
	public List<Customer> customerFindName(@RequestParam("n") String name) {
//		List<Customer> result = customerRepository.findByNameQuery(name);
//		List<Customer> result = customerRepository.findByNameQuery2(name);
//		List<Customer> result = customerRepository.findByNameLikeQuery(name);
		List<Customer> result = customerRepository.findByNameNativeQuery(name);

		return result;
	}

	@GetMapping("/customer/updateName")
	public Integer updateName(@RequestParam Integer id, @RequestParam String newName) {
		return customerRepository.updateNameById(newName, id);
	}

	@GetMapping("/customer/findName2")
	public List<Customer> findByNameSnippet(@RequestParam("n") String name) {
		return customerRepository.findByName(name);
	}

	@GetMapping("/customer/findLevel")
	public List<Customer> findByLevel(@RequestParam Integer level) {
		return customerRepository.findByLevelOrderByIdDesc(level);
	}

}
