package org.generation.italy.demo;

import java.time.LocalDate;
import java.util.List;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.serv.DrinkService;
import org.generation.italy.demo.serv.PizzaService;
import org.generation.italy.demo.serv.PromozioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzeriaApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private DrinkService drinkService;
	@Autowired
	private PromozioneService promozioneService;
	
	public static void main(String[] args) {
		SpringApplication.run(PizzeriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//INSERIMENTO PROMOZIONI
		Promozione promo1 = new Promozione(LocalDate.parse("2022-12-01"), LocalDate.parse("2022-12-31"), "Sconto Dicembre");
		Promozione promo2 = new Promozione(LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-31"), "Promo Gennaio");

		promozioneService.save(promo1);
		promozioneService.save(promo2);
		
		
		// INSERIMENTO PIZZE

		Pizza p1 = new Pizza("Margherita", "Mozzarella, olio e pomodoro", 4, promo1);
		Pizza p2 = new Pizza("Marinara", "Pomodoro, aglio, origano", 3, promo1);
		Pizza p3 = new Pizza("Diavola", "Mozzarella, pomodoro e salame piccante", 5, promo2);
		Pizza p4 = new Pizza("Pizza fritta", "Ripieno fritto con mozzarella e pomodoro", 7, null);

		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);
		
		// INSERIMENTO DRINK

		Drink d1 = new Drink("Coca Cola", "Bevanda gassata analcolica", 3);
		Drink d2 = new Drink("Vino", "Rosso o bianco della casa", 6);
		Drink d3 = new Drink("Sprite", "Bevanda gassata analcolica", 3);
		Drink d4 = new Drink("Birra", "Birra chiara artigianale", 7);

		drinkService.save(d1);
		drinkService.save(d2);
		drinkService.save(d3);
		drinkService.save(d4);


		// LETTURA
		
		List<Drink> drinks = drinkService.findAll();
		System.out.println(drinks);
		
		System.out.println("---------------------------");
		List<Pizza> pizze = pizzaService.findAll();
		for (Pizza pizza : pizze) {
			System.err.println(pizza + "\n\t" + pizza.getPromozione());
		}

		System.out.println("---------------------------");
		
		List<Promozione> promozioni = promozioneService.findAllWithPizza();

		for (Promozione promozione : promozioni) {
			System.err.println(promozione);
			for (Pizza pizza : promozione.getPizze()) {
				System.err.println("\t" + pizza);
			}
		}
	}
}
