import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    ArvBin arvBin = new ArvBin();
    ArvBpb arvBpb = new ArvBpb();
    ArvAvl arvAvl = new ArvAvl();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine().trim();
      if (line.isEmpty()) continue;

      String[] parts = line.split(" ", 2);
      String command = parts[0].toLowerCase();
      String value = parts.length > 1 ? parts[1] : null;

      if (command.equals("i")){ 
        arvBin.insert(value);
        arvBpb.insert(value);
        arvAvl.insert(value);
      }
      else if (command.equals("d")) {
        arvBin.remove(value);
        arvBpb.remove(value);
        arvAvl.remove(value);
      }

    }
    System.out.println(arvBin.toString());
    System.out.println(arvBpb.toString());
    System.out.println(arvAvl.toString());
    
    scanner.close();
  }
}