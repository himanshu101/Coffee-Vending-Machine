package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import models.Beverage;
import models.Ingredient;
import repository.BeverageRepository;

public class BeverageService implements Cloneable{

  private BeverageRepository beverageRepository;

  private List<Integer> usedBeverageIds;

  private static Random random;

  BeverageService() {
    this.beverageRepository = new BeverageRepository();
    random = new Random();
    this.usedBeverageIds = new ArrayList<>();
  }

  public Beverage findByBeverageId(int beverageId) {
    return this.beverageRepository.findByBeverageId(beverageId);
  }

  public Beverage addBeverage(String beverageName) {
    return this.beverageRepository.addBeverage(beverageName);
  }

  public void addIngredientInBeverage(int beverageId, String ingredientName, Long quantity) {
    Ingredient ingredient = new Ingredient(ingredientName, quantity);
    this.beverageRepository.addIngredientInBeverage(beverageId, ingredient);
  }

  public List<Integer> getBeverageIds() {
    return this.beverageRepository.getBeverageIds();
  }

  public synchronized int getRandomBeverageId(List<Integer> beverageIds) {
    if (beverageIds.size() == 0)
      return -1;
    int n = random.nextInt(beverageIds.size());
    if(this.usedBeverageIds.stream().noneMatch(beverageId -> beverageId == n)) {
      this.usedBeverageIds.add(n);

      return beverageIds.get(n);
    } else {
      Collections.swap(beverageIds, n, beverageIds.size() - 1);
      beverageIds.remove(beverageIds.size() - 1);

      return getRandomBeverageId(beverageIds);
    }
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
