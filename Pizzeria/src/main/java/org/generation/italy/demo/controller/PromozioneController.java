package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.serv.PromozioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/promozioni")
public class PromozioneController {
	
	@Autowired
	private PromozioneService promozioneService;
	
	@GetMapping
	public String index(Model model) {
		List<Promozione> promozioni = promozioneService.findAll();
		model.addAttribute("promozioni", promozioni);
		return "promozioni";
	}
}
