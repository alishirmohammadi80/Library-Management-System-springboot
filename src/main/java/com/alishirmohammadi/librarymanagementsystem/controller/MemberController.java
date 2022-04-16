package com.alishirmohammadi.librarymanagementsystem.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.alishirmohammadi.librarymanagementsystem.entity.Member;
import com.alishirmohammadi.librarymanagementsystem.excelExporter.MemberExcelExporter;
import com.alishirmohammadi.librarymanagementsystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.alishirmohammadi.librarymanagementsystem.service.MemberService;
import javax.servlet.http.HttpServletResponse;
@Controller
public class MemberController {
@Autowired
MemberService memberService;

@GetMapping("/members")
	//@RequestMapping("/members")
	public String findAllMembers(Model model) {
		 List<Member> members = memberService.findAllMembers();
		model.addAttribute("members", members);
		return "list-members";
	}
@GetMapping("/member/{id}")
	//@RequestMapping("/member/{id}")
	public String findMemberById(@PathVariable("id") Long id, Model model) {
		 Member member = memberService.findMemberById(id);
		model.addAttribute("member", member);
		return "list-members";
	}

	@GetMapping("/addmember")
	public String showCreateForm(Member member) {
		return "add-member";
	}
@PostMapping("/add-member")
	//@RequestMapping("/add-member")
	public String createMember(Member member,  Model model) {
		memberService.createMember(member);
		model.addAttribute("member",memberService.findAllMembers());
		return "redirect:/members";
	}

	@GetMapping("/updatemember/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		 Member member = memberService.findMemberById(id);
		model.addAttribute("member",member);
		return "update-member";
	}
@PostMapping("/update-member/{id}")
	//@RequestMapping("/update-member/{id}")
	public String updateMember(@PathVariable("id") Long id, Member member, BindingResult result, Model model) {
		if (result.hasErrors()) {
			member.setId(id);
			return "update-member";
		}
		memberService.updateMember(member);
		model.addAttribute("member", memberService.findAllMembers());
		return "redirect:/members";
	}
@GetMapping("/remove-member/{id}")
//	@RequestMapping("/remove-member/{id}")
	public String deleteMember(@PathVariable("id") Long id, Model model) {
		memberService.deleteMember(id);
		model.addAttribute("member", memberService.findAllMembers());
		return "redirect:/members";
	}
	@Autowired
	MemberRepository repository;
	@GetMapping("/member/export/excel")
	public void exportToExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=members_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<Member> listMembers = repository.findAll();
		MemberExcelExporter	excelExporter = new MemberExcelExporter(listMembers);
		excelExporter.export(response);
	}
}
