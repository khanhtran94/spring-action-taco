package com.example.tacoclound.web;

import com.example.tacoclound.Ingredient;
import com.example.tacoclound.Ingredient.Type;
import com.example.tacoclound.Order;
import com.example.tacoclound.Taco;
import com.example.tacoclound.data.IngredientReposioty;
import com.example.tacoclound.data.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    private final IngredientReposioty ingredientReposioty;
    private TacoRepository designRepo;

    @ModelAttribute(name="order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @Autowired
    public DesignTacoController(IngredientReposioty ingredientReposioty, TacoRepository designRepo){
        this.ingredientReposioty = ingredientReposioty;
        this.designRepo = designRepo;
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientReposioty.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Type.values();

        for (Type type : types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order){

        // save the taco design
        // we'll do this in chap 3
        if (errors.hasErrors()){
            return "design";
        }
        Taco saved = designRepo.save(design);
        order.addDesign(saved);

        log.info("Processing desing: " + design );
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
