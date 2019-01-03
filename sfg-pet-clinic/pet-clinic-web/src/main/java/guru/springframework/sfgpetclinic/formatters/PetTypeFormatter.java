package guru.springframework.sfgpetclinic.formatters;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@Component
public class PetTypeFormatter implements Formatter<PetType>{

	private final PetTypeService petTypService;
		
	public PetTypeFormatter(PetTypeService petTypService) {
		this.petTypService = petTypService;
	}

	public String print(PetType petType, Locale locale) {
		return petType.getName();
	}
	
	public  PetType parse(String text, Locale locale) throws ParseException {
		Collection<PetType> findPetTypes = petTypService.findAll();
		for (PetType type : findPetTypes) {
			if(type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}
}
