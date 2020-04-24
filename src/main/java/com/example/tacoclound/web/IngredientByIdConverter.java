package com.example.tacoclound.web;

import com.example.tacoclound.Ingredient;
import com.example.tacoclound.data.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

@Component
public class IngredientByIdConverter implements Converter<String, Optional<Ingredient>> {

    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo){
        this.ingredientRepo = ingredientRepo;
    }

    // chua hieu tai sao ham nay lai tra ve gia tri, ko thay goi o bat cu dau
    @Override
    public Optional<Ingredient> convert(String id) {
        Optional<Ingredient> optionalIngredient = ingredientRepo.findById(id);
        return optionalIngredient.isPresent() ?
                Optional.of(optionalIngredient.get()) : null;
    }
}
