package com.alishirmohammadi.librarymanagementsystem.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.alishirmohammadi.librarymanagementsystem.excelExporter.CategoryExcelExporter;
import com.alishirmohammadi.librarymanagementsystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.alishirmohammadi.librarymanagementsystem.entity.Category;
import com.alishirmohammadi.librarymanagementsystem.service.CategoryService;
import javax.servlet.http.HttpServletResponse;
@Controller
public class CategoryController {
@Autowired
CategoryService categoryService;

@GetMapping("/categories")
	//@RequestMapping("/categories")
	public String findAllCategories(Model model) {
		 List<Category> categories = categoryService.findAllCategories();
		model.addAttribute("categories", categories);
		return "list-categories";
	}
@GetMapping("/category/{id}")
	//@RequestMapping("/category/{id}")
	public String findCategoryById(@PathVariable("id") Long id, Model model) {
		 Category category = categoryService.findCategoryById(id);
		model.addAttribute("category", category);
		return "list-categories";
	}

	@GetMapping("/addCategory")
	public String showCreateForm(Category category) {
		return "add-category";
	}
@PostMapping("/add-category")
	//@RequestMapping("/add-category")
	public String createCategory(Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-category";
		}

		categoryService.createCategory(category);
		model.addAttribute("category", categoryService.findAllCategories());
		return "redirect:/categories";
	}

	@GetMapping("/updateCategory/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		 Category category = categoryService.findCategoryById(id);
		model.addAttribute("category", category);
		return "update-category";
	}
@PostMapping("/update-category/{id}")
	//@RequestMapping("/update-category/{id}")
	public String updateCategory(@PathVariable("id") Long id, Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			category.setId(id);
			return "update-category";
		}

		categoryService.updateCategory(category);
		model.addAttribute("category", categoryService.findAllCategories());
		return "redirect:/categories";
	}
@GetMapping("/remove-category/{id}")
	//@RequestMapping("/remove-category/{id}")
	public String deleteCategory(@PathVariable("id") Long id, Model model) {
		categoryService.deleteCategory(id);
		model.addAttribute("category", categoryService.findAllCategories());
		return "redirect:/categories";
	}

	@Autowired
	CategoryRepository repository;
	@GetMapping("/category/export/excel")
	public void exportToExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=categorys_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<Category> listCategorys = repository.findAll();
		CategoryExcelExporter excelExporter = new CategoryExcelExporter(listCategorys);
		excelExporter.export(response);
	}
}
