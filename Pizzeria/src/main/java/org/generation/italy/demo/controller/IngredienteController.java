package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingrediente;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.IngredienteService;
import org.generation.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

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
	
	@GetMapping("/ingrediente/create")
	public String createIngrediente(Model model) {

		Ingrediente ingrediente = new Ingrediente();
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("ingrediente", ingrediente);
		model.addAttribute("pizze", pizze);
		
		return "ingrediente-create";
	}
	
	@PostMapping("/ingrediente/create")
	public String storeIngrediente(@Valid Ingrediente ingrediente) {
		
		List<Pizza> ingredientePizze = ingrediente.getPizze();
		for (Pizza pizza : ingredientePizze) {
			pizza.getIngredienti().add(ingrediente);
		}
		ingredienteService.save(ingrediente);
		
		return "redirect:/ingredienti";
	}
	
	@GetMapping("/ingrediente/update/{id}")
	public String editIngrediente(@PathVariable("id") int id, Model model) {
		
		Optional<Ingrediente> optIngrediente = ingredienteService.findIngredienteById(id);
		Ingrediente ingrediente = optIngrediente.get();
		
		List<Pizza> pizze = pizzaService.findAll();
		
		model.addAttribute("ingrediente", ingrediente);
		model.addAttribute("pizze", pizze);
		return "ingrediente-update";
	}
	
	@PostMapping("/ingrediente/update")
	public String updateIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/ingrediente/update/" + ingrediente.getId();
		}
		
		//Optional<Ingrediente> optIngrediente = ingredienteService.findIngredienteById(ingrediente.getId());
		//Ingrediente ing = optIngrediente.get();

		for (Pizza pizza : ingrediente.getPizze()) {
			pizza.removeIngredienti(ingrediente);
		}

		List<Pizza> ingredientePizze = ingrediente.getPizze();
		for (Pizza p : ingredientePizze)
			p.getIngredienti().add(ingrediente);
		for (Pizza p : ingredientePizze) {
			p.addIngredienti(ingrediente);
		}

		ingredienteService.save(ingrediente);
		return "redirect:/ingredienti";
	}

}
