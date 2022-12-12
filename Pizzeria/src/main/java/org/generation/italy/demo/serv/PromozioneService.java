package org.generation.italy.demo.serv;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.repo.PromozioneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromozioneService {
	
	@Autowired
	private PromozioneRepo promozioneRepo;
	
	public void save(Promozione promozione) {
		promozioneRepo.save(promozione);
	}
	
	public List<Promozione> findAll() {
		return promozioneRepo.findAll();
	}
	
	public Optional<Promozione> findPromozioneById(int id){
		return promozioneRepo.findById(id);
	}
	
	public void delete (Promozione promozione) {
		promozioneRepo.delete(promozione);
	}
	
	public void deleteById(int id) {
		promozioneRepo.deleteById(id);
	}
}
