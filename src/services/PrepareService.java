package services;


import exceptions.NegativeIngredientQuantityException;
import java.util.ArrayList;
import java.util.List;

import models.Beverage;
import models.Ingredient;

public class PrepareService {

  private long threadId;

  private int beverageId;

  private BeverageService beverageService;

  private IngredientService ingredientService;

  PrepareService(long threadId, int beverageId, BeverageService beverageService, IngredientService ingredientService) {
    this.threadId = threadId;
    this.beverageId = beverageId;
    this.beverageService = beverageService;
    this.ingredientService = ingredientService;
  }

  synchronized void prepare() {
    Beverage beverage = this.beverageService.findByBeverageId(this.beverageId);
    List<String> lessIngredientNames = this.canPrepare(beverage);
    if (lessIngredientNames.size() > 0) {
      System.out.println(beverage.getBeverageName() + " cannot be prepared because " + lessIngredientNames.get(0) + " is not available");
    } else {
      List<Ingredient> useIngredients = new ArrayList<>();
      try {
        for (Ingredient ingredient: beverage.getIngredients()) {
          this.ingredientService.updateIngredient(beverage.getBeverageName(), ingredient.getIngredientName(), ingredient.getQuantity(), true);
          useIngredients.add(ingredient);
        }
        System.out.println(beverage.getBeverageName() + " is prepared");
      } catch (NegativeIngredientQuantityException ex) {
        System.out.println(ex.getMessage());
        for (Ingredient ingredient: useIngredients) {
          this.ingredientService.updateIngredient(beverage.getBeverageName(), ingredient.getIngredientName(), ingredient.getQuantity(),
              false);
        }
      }
    }
  }

  private  List<String> canPrepare(Beverage beverage) {
    List<Ingredient> ingredients = beverage.getIngredients();

    return this.ingredientService.isIngredientSufficient(ingredients);
  }
}
