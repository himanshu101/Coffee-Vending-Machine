import services.InputService;

public class Main {

  public static void main(String[] args) {
    InputService inputService = new InputService();
    for(int i = 0; i < 4; i++) {
      System.out.println("Output " + (i + 1));
      inputService.reset(args[0]);
      System.out.println("\n\n");
    }
  }
}