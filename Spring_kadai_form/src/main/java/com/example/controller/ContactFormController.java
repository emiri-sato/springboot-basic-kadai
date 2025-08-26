package com.example.controller;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.form.ContactForm;

@Controller
public class ContactFormController {
	
	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("contactForm",new ContactForm());
	return "contactFormView.html";
	}
	
	
	@PostMapping("/confirm")
	public String confirm(Model model, RedirectAttributes redirectAttributes, @Validated ContactForm form, BindingResult result) {
	
	//バリエーションエラーがあったら終了
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("ContactForm",form);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX
				+Conventions.getVariableName(form),result);
			return "contactFormView.html";
		}
		
		model.addAttribute("contactForm",form);
		return "confirmView.html";
	}
}
