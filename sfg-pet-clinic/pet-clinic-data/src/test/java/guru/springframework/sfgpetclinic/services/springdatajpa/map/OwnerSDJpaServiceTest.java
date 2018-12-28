package guru.springframework.sfgpetclinic.services.springdatajpa.map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.springdatajpa.OwnerSDJpaService;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

	private static final String LAST_NAME = "Smith";

	@Mock
	OwnerRepository ownerRepository;
	
	static Owner returnOwner;
	
	@Mock
	PetRepository petRepository;
	
	@Mock
	PetTypeRepository petTypRepository;
	
	@InjectMocks
	OwnerSDJpaService service;
	

	@BeforeEach
	void setUp() throws Exception {
		returnOwner = new Owner();
		returnOwner.setId(1L);
		returnOwner.setLastName(LAST_NAME);
	}


	@Test
	void testFindByLastName() {
		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
		
		Owner smith = service.findByLastName(LAST_NAME);
		
		assertEquals(LAST_NAME, smith.getLastName());
		
		verify(ownerRepository).findByLastName(any());
	}

	@Test
	void testFindAll() {
		Set<Owner> returnOwnersSet = new HashSet<>();
		Owner owner1 = new Owner();
		owner1.setId(1L);
		Owner owner2 = new Owner();
		owner2.setId(2L);
		
		returnOwnersSet.add(owner1);
		returnOwnersSet.add(owner2);
		
		when(ownerRepository.findAll()).thenReturn(returnOwnersSet);
		
		Set<Owner> owners = service.findAll();
		
		assertNotNull(owners);
		assertEquals(2, owners.size());
	}

	@Test
	void testFindById() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
		
		Owner owner = service.findById(1L);
		
		assertNotNull(owner);	
	}

	@Test
	void testFindByIdNotFound() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		
		Owner owner = service.findById(1L);
		
		assertNull(owner);	
	}
	
	@Test
	void testSave() {
		Owner ownerToSave = new Owner();
		ownerToSave.setId(1L);
		when(ownerRepository.save(any())).thenReturn(returnOwner);
		
		Owner savedOwner = service.save(ownerToSave);
		assertNotNull(savedOwner);
		
		verify(ownerRepository).save(any());
	}

	@Test
	void testDelete() {
		service.delete(returnOwner);
		
		verify(ownerRepository, times(1)).delete(any());
	}

	@Test
	void testDeleteById() {
		service.deleteById(1L);
		
		verify(ownerRepository).deleteById(anyLong());
	}

}
