package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/employee/add")
	public String addEmployee() {
		return "employee/addEmpPage";
	}
	
	@PostMapping("/employee/addPost") // ModelAndView
    public String postEmp(
    		@RequestParam String name, 
    		@RequestParam String email,
    		@RequestParam Integer jobAge,
    		Model model) {
		
		Employee newEmp = new Employee();
		
		newEmp.setName(name);
		newEmp.setEmail(email);
		newEmp.setJobAge(jobAge);
		
		empService.saveEmp(newEmp);
		
		model.addAttribute("okMsg", "新增成功");
		
		return "employee/addEmpPage";
	}
	
	@GetMapping("/employee/list")
	public String findAll(Model model) {
		List<Employee> list = empService.findAllEmp();
		
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("empList", list);
//		mav.setViewName("employee/showEmpPage");
//		return mav;
		
		model.addAttribute("empList", list);
		return "employee/showEmpPage";
	}
	
	@GetMapping("/employee/update")
	public String updateEmp(@RequestParam Integer id,Model model) {
		Employee employee = empService.findEmpById(id);
		
		model.addAttribute("employee", employee);
		
		return "employee/editPage";
	}
	
	@PostMapping("/employee/updatePost")
	public String updateEmpPost(@ModelAttribute Employee employee) {
		empService.saveEmp(employee);
		return "redirect:/employee/list";
	}
	
}
