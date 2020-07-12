package repository;

import exceptions.NegativeIngredientQuantityException;
import java.util.HashMap;
import java.util.List;
import models.Ingredient;

public class IngredientRepository {

  private HashMap<String, Ingredient> ingredientsHashMap;

  public IngredientRepository() {
    this.ingredientsHashMap = new HashMap<>();
  }

  public Ingredient add(String ingredientName, Long quantity) {
    Ingredient ingredient = new Ingredient(ingredientName, quantity);
    this.ingredientsHashMap.put(ingredient.getIngredientName(), ingredient);

    return ingredient;
  }

  public void update(String beverageName, String ingredientName, Long quantity, boolean subtract) throws NegativeIngredientQuantityException {
    if(subtract) {
      if (this.getIngredientsHashMap().get(ingredientName).getQuantity() < quantity)
        throw new NegativeIngredientQuantityException(beverageName + " cannot be prepared because " + ingredientName + " is not " +
            "available\n");
      this.ingredientsHashMap.get(ingredientName).setQuantity(this.getIngredientsHashMap().get(ingredientName).getQuantity() - quantity);
    } else {
      this.ingredientsHashMap.get(ingredientName).setQuantity(this.getIngredientsHashMap().get(ingredientName).getQuantity() + quantity);
    }
  }

  public HashMap<String, Ingredient> getIngredientsHashMap() {
    return ingredientsHashMap;
  }

  public HashMap<String, Ingredient> getIngredients(List<String> ingredientNames) {
    HashMap<String, Ingredient> ingredientHashMap = new HashMap<>();
    ingredientNames.forEach(ingredientName -> ingredientHashMap.put(ingredientName, this.ingredientsHashMap.get(ingredientName)));

    return ingredientHashMap;
  }

  public Long getIngredientQuantity(String ingredientName) {
    return this.ingredientsHashMap.get(ingredientName).getQuantity();
  }
}
