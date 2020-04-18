package com.example.tacoclound.data;

import com.example.tacoclound.Ingredient;

public interface IngredientReposioty {
    Iterable<Ingredient> findAll();

    Ingredient findById(String id);
    Ingredient save(Ingredient ingredient);
}
