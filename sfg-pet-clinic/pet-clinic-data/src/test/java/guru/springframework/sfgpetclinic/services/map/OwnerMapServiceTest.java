package guru.springframework.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.model.Owner;

class OwnerMapServiceTest {

	static OwnerMapService ownerMapService;
	final static Long ownerId = 1L;
	final static String lastName = "Smith";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {		
		ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
		
		Owner owner = new Owner();
		owner.setId(ownerId);
		owner.setLastName(lastName);
		ownerMapService.save(owner);
	}

	@Test
	void testOwnerMapService() {
		Set<Owner> ownerSet = ownerMapService.findAll();
		assertEquals(1, ownerSet.size());
	}


	@Test
	void testFindByIdLong() {
		Owner owner = ownerMapService.findById(ownerId);
		assertEquals(ownerId,  owner.getId());
	}

	@Test
	void testSaveOwner() {
		Long id = 2L;
		
		Owner owner2 = new Owner();
		owner2.setId(id);
		Owner savedOwner = ownerMapService.save(owner2);
		assertEquals(id,  savedOwner.getId());
	}

	@Test
	void testSaveNoId() {

		Owner owner3 = new Owner();
		Owner savedOwner = ownerMapService.save(owner3);
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
	}
	
	@Test
	void testFindByLastName() {
		
		Owner smith = ownerMapService.findByLastName(lastName);
		assertNotNull(smith);
		//assertEquals(ownerId, smith.getId());
	}
	
	@Test
	void testFindByLastNameNotFound() {
		
		Owner smith = ownerMapService.findByLastName("foo");
		assertNotNull(smith);
	}
	
//	@Test
//	void testDeleteOwner() {
//		ownerMapService.delete(ownerMapService.findById(ownerId));
//		assertEquals(0, ownerMapService.findAll().size());
//	}
//
//	@Test
//	void testDeleteByIdLong() {
//		ownerMapService.deleteById(ownerId);
//		assertEquals(0, ownerMapService.findAll().size());
//	}



}
