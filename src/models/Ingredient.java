package models;

import java.util.concurrent.atomic.AtomicInteger;

public class Ingredient {

  private static final AtomicInteger count = new AtomicInteger(0);
  private int ingredientId;
  private String ingredientName;
  private Long quantity;

  public Ingredient(String ingredientName, Long quantity) {
    this.ingredientId = count.incrementAndGet();
    this.ingredientName = ingredientName;
    this.quantity = quantity;
  }

  public int getIngredientId() {
    return ingredientId;
  }

  public String getIngredientName() {
    return ingredientName;
  }

  public void setIngredientName(String ingredientName) {
    this.ingredientName = ingredientName;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
}
