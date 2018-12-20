package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner{

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	
	// no need for @Autowired annotation since latest spring using constructor injection
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}
	

	@Override
	public void run(String... args) throws Exception {
		
		PetType dog = new PetType();
		dog.setName("Dog");		
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");		
		PetType savedCatPetType = petTypeService.save(cat);
		
		System.out.println("Loading Pet Types...");
		

		
		
		Owner owner1 = new Owner();
		owner1.setFirstName("Michael");
		owner1.setLastName("Weston");	
		owner1.setAddress("123 Brickerel");
		owner1.setCity("Miami");
		owner1.setTelephone("132132321");
		ownerService.save(owner1);
		
		Pet mikesPet = new Pet();
		mikesPet.setPetType(savedDogPetType);
		mikesPet.setOwner(owner1);
		mikesPet.setBirthDate(LocalDate.now());
		mikesPet.setName("Rosco");
		owner1.getPets().add(mikesPet);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Fiona");
		owner2.setLastName("Glenanne");		
		owner2.setAddress("123 Brickerel");
		owner2.setCity("Miami");
		owner2.setTelephone("132132321");
		ownerService.save(owner2);
		
		Pet fionasPet = new Pet();
		fionasPet.setPetType(savedCatPetType);
		fionasPet.setOwner(owner2);
		fionasPet.setBirthDate(LocalDate.now());
		fionasPet.setName("Lucy");
		owner2.getPets().add(fionasPet);
		
		System.out.println("Loaded Owners & pets ...");
		
		
		
		Vet vet1 = new Vet();
		vet1.setFirstName("Sam");
		vet1.setLastName("Axe");		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");		
		vetService.save(vet2);
		
		System.out.println("Loaded Vets ...");

	}

}
