package guru.springframework.sfgpetclinic;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.sfgpetclinic.controllers.OwnerController;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

	@Mock
	OwnerService ownerService;
	
	@InjectMocks
	OwnerController controller;
	
	Set<Owner> owners;
	
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		
		owners  = new HashSet<>();
		Owner owner1 = new Owner();
		Owner owner2 = new Owner();
		owner1.setId(1L);
		owner2.setId(2L);
		
		owners.add(owner1);
		owners.add(owner2);
		
		mockMvc = MockMvcBuilders
					.standaloneSetup(controller)
					.build();
	}

	
	@Test
	void testFindOwners() throws Exception {
		
		mockMvc.perform(get("/owners/find"))
				.andExpect(status().isOk())
				.andExpect(view().name("owners/findOwners"))
				.andExpect(model().attributeExists("owner"));
		
		verifyZeroInteractions(ownerService);
	}



//	@Test
//	void testShowOwner() throws Exception {
//		Owner owner = new Owner();
//		owner.setId(3L);
//		"
//		when(ownerService.findById(3L)).thenReturn(owner);
//		
//		mockMvc.perform(get("/owners/123"))
//					.andExpect(status().isOk())
//					.andExpect(view().name("owners/ownerDetails"));
//	}
}
