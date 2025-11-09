package com.RecipeBook.RecipeBookApplication.converter;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(List<String> list) {
		try {
			return objectMapper.writeValueAsString(list);
		}
		catch(Exception e) {
			throw new RuntimeException("Error converting list to Json -> ", e);
		}
	}

	@Override
	public List<String> convertToEntityAttribute(String json) {
		try {
			return objectMapper.readValue(json, new TypeReference<List<String>>() {});
		}
		catch(Exception e) {
			throw new RuntimeException("Error converting json to List -> ", e);
		}
	}
	
	
}
