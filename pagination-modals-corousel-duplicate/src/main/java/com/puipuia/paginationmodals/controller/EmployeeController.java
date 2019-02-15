package  com.puipuia.paginationmodals.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puipuia.paginationmodals.dao.EmployeeRepository;
import com.puipuia.paginationmodals.entity.Employee;


@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeController(EmployeeRepository theEmployeeRepository) {
		
		employeeRepository=theEmployeeRepository;
		
	}

	
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel,@RequestParam(defaultValue="0") int page ) {
		
	
	
		theModel.addAttribute("employees", employeeRepository.findAll(PageRequest.of(page, 4)));
		
		
		theModel.addAttribute("currentPage", page);
		
		return "/employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "/employees/employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		// save the employee
		employeeRepository.save(theEmployee);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
	


	@GetMapping("/findOne")
	@ResponseBody
	public Optional<Employee> findOne(Integer id) {
		
		
		
		return employeeRepository.findById(id);
				
	}

	
	
	@GetMapping("/delete")
	public String delete(Integer id){
		
		 employeeRepository.deleteById(id);
		 
		return "redirect:/employees/list";
	}
	

	
	@GetMapping("/carousel")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getImage(@RequestParam("background") String background) throws IOException {

	ClassPathResource imgFile;
	
	switch(background){
		case "back":
			imgFile = new ClassPathResource("/images/slider-1.jpg");
			break;
		case "green":
			imgFile = new ClassPathResource("/images/slider-2.jpg");
			break;
		case "blue":
			imgFile = new ClassPathResource("/images/slider-3.jpg");
			break;
		default:
			imgFile = new ClassPathResource("/images/slider-4.jpg");
	}
	
	
	return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_JPEG)
            .body(new InputStreamResource(imgFile.getInputStream()));
}
	
    
}












