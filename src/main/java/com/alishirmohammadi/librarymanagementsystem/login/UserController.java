package com.alishirmohammadi.librarymanagementsystem.login;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.alishirmohammadi.librarymanagementsystem.excelExporter.UserExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.alishirmohammadi.librarymanagementsystem.emailService.EmailService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class UserController {
@Autowired
EmailService emailService;
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		emailService.send(user.getEmail(),"Account Information","username:"+user.getUserName()+"---"+"password:"+user.getPassword());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepo.save(user);
		return "index";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "list-user";
	}

	@Autowired
	UserRepository repository;
	@GetMapping("/user/export/excel")
	public void exportToExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<User> listUsers = repository.findAll();
		UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
		excelExporter.export(response);
	}
}



