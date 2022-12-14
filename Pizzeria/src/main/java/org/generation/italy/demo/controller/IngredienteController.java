package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Ingrediente;
import org.generation.italy.demo.serv.IngredienteService;
import org.generation.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String index(Model model) {

		List<Ingrediente> ingredienti = ingredienteService.findAllWPizza();
		model.addAttribute("ingredienti", ingredienti);
		return "ingredienti";
	}

}
