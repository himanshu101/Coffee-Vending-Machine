package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import models.Ingredient;
import repository.IngredientRepository;

public class IngredientService implements Cloneable{

  private IngredientRepository ingredientRepository;

  IngredientService() {
    this.ingredientRepository = new IngredientRepository();
  }

  public Ingredient addIngredient(String ingredientName, Long quantity) {
    return this.ingredientRepository.add(ingredientName, quantity);
  }

  public void updateIngredient(String bevergaeName, String ingredientName, Long quantity, boolean subtract) {
    this.ingredientRepository.update(bevergaeName, ingredientName, quantity, subtract);
  }

  public synchronized List<String> isIngredientSufficient(List<Ingredient> ingredients) {
    HashMap<String, Ingredient> ingredientHashMap = this.ingredientRepository.getIngredientsHashMap();
    List<String> ingredientNames = new ArrayList<>();
    for(Ingredient ingredient: ingredients){
      if(ingredientHashMap.get(ingredient.getIngredientName()) == null ||
          ingredient.getQuantity() > ingredientHashMap.get(ingredient.getIngredientName()).getQuantity()) {
        ingredientNames.add(ingredient.getIngredientName());
      }
    }

    return ingredientNames;
  }

  public Long getIngredientQuantity(String ingredientName) {
    return this.ingredientRepository.getIngredientQuantity(ingredientName);
  }

  public HashMap<String, Ingredient> getIngredientsHashMap(List<String> ingredientNames) {
    return this.ingredientRepository.getIngredientsHashMap();
  }
}
