//Program by Ella Withington 
import java.util.HashMap;
import java.io.*;
import java.util.*;
public class getOrder {
    static HashMap<String, String> data = new HashMap<String, String>();
    
public static void main(String[] args) {
    displayOrder();
}
public static void displayOrder(){
    data.put("45", "Widget");
    data.put("91", "Thingamajig");
    data.put("10", "Doohickey");
    System.out.println("Available items are: ");
    System.out.println("Stock #   Description ");
    System.out.println("-------   ----------- ");
    for (String key : data.keySet()) {
        System.out.println(key + "        " + data.get(key));
        }
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter your user ID:");
    int id = scan.nextInt();
    System.out.println("Enter stock number:");
    int stockNumber = scan.nextInt();
    System.out.println("Enter quantity:");
    int quantity = scan.nextInt();
    scan.close();
     try{
        OutputStream out = new FileOutputStream("Binary.data", true);
        out.write(id);
        out.write(stockNumber);
        out.write(quantity);
        out.close();
     }
     catch (IOException e) {
         System.out.println("Error in output file.");
         System.exit(1);
     }
    
    
}
}



