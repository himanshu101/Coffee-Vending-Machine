package repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import models.Beverage;
import models.Ingredient;

public class BeverageRepository {

  HashMap<Integer, Beverage> beveragesHashMap;

  public BeverageRepository() {
    this.beveragesHashMap = new HashMap<>();
  }

  public Beverage findByBeverageId(int beverageId) {
    return this.beveragesHashMap.get(beverageId);
  }

  public Beverage addBeverage(String beverageName) {
    Beverage beverage = new Beverage(beverageName);
    this.beveragesHashMap.put(beverage.getBeverageId(), beverage);

    return beverage;
  }

  public void addIngredientInBeverage(int beverageId, Ingredient ingredient) {
    this.beveragesHashMap.get(beverageId).addIngredient(ingredient);
  }

  public List<Integer> getBeverageIds() {
    return new ArrayList<>(this.beveragesHashMap.keySet());
  }
}
