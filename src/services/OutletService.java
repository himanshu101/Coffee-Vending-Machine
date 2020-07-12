package services;

import org.json.simple.JSONObject;
import repository.OutletRepository;

public class OutletService {

  private OutletRepository outletRepository;

  public OutletService() {
    this.outletRepository = new OutletRepository();
  }

  public void assignOutlets(JSONObject outlets) {
    this.outletRepository.setNoOfOutLets((Long) outlets.get("count_n"));
  }

  public Long getCountOfOutlets() {
    return this.outletRepository.getNoOfOutLets();
  }
}
