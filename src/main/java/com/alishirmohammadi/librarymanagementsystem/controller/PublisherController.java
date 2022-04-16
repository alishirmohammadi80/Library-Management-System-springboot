package com.alishirmohammadi.librarymanagementsystem.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.alishirmohammadi.librarymanagementsystem.entity.Publisher;
import com.alishirmohammadi.librarymanagementsystem.excelExporter.PublisherExcelExporter;
import com.alishirmohammadi.librarymanagementsystem.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.alishirmohammadi.librarymanagementsystem.service.PublisherService;
import javax.servlet.http.HttpServletResponse;
@Controller
public class PublisherController {
@Autowired
PublisherService publisherService;

@GetMapping("/publishers")
	public String findAllPublishers(Model model) {
		 List<Publisher> publishers = publisherService.findAllPublishers();
		model.addAttribute("publishers", publishers);
		return "list-publishers";
	}
@GetMapping("/publisher/{id}")
	//@RequestMapping("/publisher/{id}")
	public String findPublisherById(@PathVariable("id") Long id, Model model) {
		 Publisher publisher = publisherService.findPublisherById(id);
		model.addAttribute("publisher", publisher);
		return "list-publishers";
	}

	@GetMapping("/addPublisher")
	public String showCreateForm(Publisher publisher) {
		return "add-publisher";
	}
@PostMapping("/add-publisher")
	//@RequestMapping("/add-publisher")
	public String createPublisher(Publisher publisher, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-publisher";
		}
		publisherService.createPublisher(publisher);
		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}

	@GetMapping("/updatePublisher/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		 Publisher publisher = publisherService.findPublisherById(id);
		model.addAttribute("publisher", publisher);
		return "update-publisher";
	}
@PostMapping("/update-publisher/{id}")
	//@RequestMapping("/update-publisher/{id}")
	public String updatePublisher(@PathVariable("id") Long id, Publisher publisher, BindingResult result, Model model) {
		if (result.hasErrors()) {
			publisher.setId(id);
			return "update-publisher";
		}
		publisherService.updatePublisher(publisher);
		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}
@GetMapping("/remove-publisher/{id}")
	//@RequestMapping("/remove-publisher/{id}")
	public String deletePublisher(@PathVariable("id") Long id, Model model) {
		publisherService.deletePublisher(id);
		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}

	@Autowired
	PublisherRepository repository;
	@GetMapping("/publisher/export/excel")
	public void exportToExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=publishers_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<Publisher> listPublishers = repository.findAll();
		PublisherExcelExporter excelExporter = new PublisherExcelExporter(listPublishers);
		excelExporter.export(response);
	}
}
