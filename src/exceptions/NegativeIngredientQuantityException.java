package exceptions;

public class NegativeIngredientQuantityException extends RuntimeException {

  public NegativeIngredientQuantityException(String message) {
    super(message);
  }
}
