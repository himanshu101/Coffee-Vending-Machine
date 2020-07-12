package services;

import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import models.Beverage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InputService {

  private IngredientService ingredientService;
  private OutletService outletService;
  private BeverageService beverageService;

  public void read(String filename) {
    JSONParser parser = new JSONParser();
    try {
      Object obj = parser.parse(new FileReader(filename));

      JSONObject jsonObject = (JSONObject) obj;
      JSONObject machine= (JSONObject) jsonObject.get("machine");
      this.addOutlets((JSONObject) machine.get("outlets"));
      this.addIngredient((JSONObject) machine.get("total_items_quantity"));
      this.addBeverages(((JSONObject) machine.get("beverages")));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void addOutlets(JSONObject noOfOutlets) {
    this.outletService.assignOutlets(noOfOutlets);
  }

  private void addIngredient(JSONObject ingredient) {
    Set keys = ingredient.keySet();
    for (String key : (Iterable<String>) keys) {
      ingredientService.addIngredient(key, (Long) ingredient.get(key));
    }
  }

  private void addBeverages(JSONObject beverages) {
    Set keys = beverages.keySet();
    for (String key : (Iterable<String>) keys) {
      Beverage beverage = beverageService.addBeverage(key);
      JSONObject beverageIngredients = (JSONObject) beverages.get(key);
      Set beverageIngredientsKeys = beverageIngredients.keySet();
      for (String beverageIngredientsKey: (Iterable<String>) beverageIngredientsKeys) {
        this.beverageService.addIngredientInBeverage(beverage.getBeverageId(), beverageIngredientsKey,
            (Long) beverageIngredients.get(beverageIngredientsKey));
      }

    }
  }

  public void reset(String filename) {
    this.ingredientService = new IngredientService();
    this.outletService = new OutletService();
    this.beverageService = new BeverageService();
    this.read(filename);
    this.startThreads();
  }

  private void startThreads() {
    List<ThreadService> threadServices = new ArrayList<>();
    for(int i = 0; i < this.outletService.getCountOfOutlets(); i++) {
      ThreadService threadService = new ThreadService(this.beverageService, this.ingredientService);
      threadServices.add(threadService);
    }
    for(ThreadService threadService: threadServices) {
      threadService.start();
    }
    try {
      for(ThreadService threadService: threadServices) {
        threadService.join();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }
}
