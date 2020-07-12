package services;


public class ThreadService extends Thread {

  private BeverageService beverageService;
  private IngredientService ingredientService;

  ThreadService(BeverageService beverageService, IngredientService ingredientService) {
    this.beverageService = beverageService;
    this.ingredientService = ingredientService;
  }

  public void run() {
    try {
      int beverageId = this.beverageService.getRandomBeverageId(this.beverageService.getBeverageIds());
      while (beverageId != -1) {
        PrepareService prepareService = new PrepareService(Thread.currentThread().getId(), beverageId, this.beverageService,
            this.ingredientService);
        prepareService.prepare();
        beverageId = this.beverageService.getRandomBeverageId(this.beverageService.getBeverageIds());
      }
    }
    catch (Exception e) {
      // Throwing an exception
      e.printStackTrace();
      System.out.println ("Exception is caught");
    }
  }
}
