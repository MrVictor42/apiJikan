package com.api.jikan.apiJikan.services.impl;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

import com.api.jikan.apiJikan.services.interfaces.SlugService;

public class SlugServiceImpl implements SlugService {
	
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
	@Override
	public String makeSlug(String input) {
		
		if (input == null)
            throw new IllegalArgumentException();

        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        
        return slug.toLowerCase(Locale.ENGLISH);
	}
}
