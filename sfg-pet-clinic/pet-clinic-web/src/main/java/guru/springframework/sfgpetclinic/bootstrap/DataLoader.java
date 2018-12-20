package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner{

	private final OwnerService ownerService;
	private final VetService vetService;
	private final PetTypeService petTypeService;
	private final SpecialtyService specialtyService;
	
	// no need for @Autowired annotation since latest spring using constructor injection
	public DataLoader(OwnerService ownerService, VetService vetService, 
			PetTypeService petTypeService, SpecialtyService specialtyService) {
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialtyService = specialtyService;
	}
	

	@Override
	public void run(String... args) throws Exception {
		
		int count = petTypeService.findAll().size();
		
		if (count ==0) {
			loadData();
		}

	}


	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");		
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");		
		PetType savedCatPetType = petTypeService.save(cat);
		
		System.out.println("Loading Pet Types...");
		

		Speciality radiology = new Speciality();
		radiology.setDescription("radiology");
		Speciality savedRadiology = specialtyService.save(radiology);
		
		Speciality surgery = new Speciality();
		surgery.setDescription("surgery");
		Speciality savedSurgery = specialtyService.save(surgery);
		
		Speciality dentistry = new Speciality();
		dentistry.setDescription("dentistry");
		Speciality savedDentistry = specialtyService.save(dentistry);
		
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
		vet1.getSpecialities().add(savedRadiology);
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setFirstName("Jessie");
		vet2.setLastName("Porter");		
		vet2.getSpecialities().add(savedSurgery);
		vetService.save(vet2);
		
		System.out.println("Loaded Vets ...");
	}

}
