package com.example.tacoclound.web;

import com.example.tacoclound.Ingredient;
import com.example.tacoclound.data.IngredientReposioty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import java.lang.annotation.Annotation;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientReposioty ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientReposioty ingredientRepo){
        this.ingredientRepo = ingredientRepo;
    }

    // chua hieu tai sao ham nay lai tra ve gia tri, ko thay goi o bat cu dau
    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id);
    }
}
