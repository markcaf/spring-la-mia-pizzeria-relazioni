package org.generation.italy.demo.pojo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Promozione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotNull(message = "La data di inizio deve essere inserita")
	private LocalDate dataInizio; 

	@Column
	@NotNull(message = "La data di fine deve essere inserita")
	private LocalDate dataFine; 

	@Column(unique = true)
	@NotNull(message = "Il titolo deve contenere qualcosa")
	private String titolo;
	
	public Promozione() { }
	public Promozione(LocalDate dataInizio, LocalDate dataFine, String titolo) {
		setDataInizio(dataInizio);
		setDataFine(dataFine);
		setTitolo(titolo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	@Override
	public String toString() {
		return "(" + getId() + ")" + " " + getTitolo() + " - " + getDataInizio() + " " + getDataFine();
	}
	
	
}
