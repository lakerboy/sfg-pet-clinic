package guru.springframework.sfgpetclinic.services;

import java.util.Set;

import guru.springframework.sfgpetclinic.model.Vet;

public interface VetService {

	Vet findById(long id);
	
	Vet save(Vet vet);
	
	Set<Vet> findAll();
	
}
