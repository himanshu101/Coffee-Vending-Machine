package models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Beverage {

  private static final AtomicInteger count = new AtomicInteger(0);
  private int beverageId;
  private String beverageName;
  private List<Ingredient> ingredients;

  public Beverage(String beverageName) {
    this.beverageId = count.incrementAndGet();
    this.beverageName = beverageName;
    this.ingredients = new ArrayList<>();
  }

  public int getBeverageId() {
    return beverageId;
  }

  public void setBeverageId(int beverageId) {
    this.beverageId = beverageId;
  }

  public String getBeverageName() {
    return beverageName;
  }

  public void setBeverageName(String beverageName) {
    this.beverageName = beverageName;
  }

  public void addIngredient(Ingredient ingredient) {
    this.ingredients.add(ingredient);
  }

  public List<Ingredient> getIngredients() {
    return this.ingredients;
  }
}
