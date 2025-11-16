
package tester;
import java.util.Scanner;


class FlowerNode{
    String flower;
    int amount;
    int price;
    FlowerNode next;
    public FlowerNode(String flower,int amount, int price) {
        this.flower = flower;
        this.amount = amount;
        this.price = price;
        this.next = null;
    }
} 


class Functions {
    Scanner sc =  new Scanner(System.in);
    FlowerNode fHead = null;
    FlowerNode fTail = null; 
    int subtotal = 0;
    public void welcome(){   
        subtotal = 0;
        System.out.println("Welcome to Flower Shop!");
        System.out.println("1. Add Order");
        System.out.println("2. View Cart");
        System.out.print("Choice: ");
        int choice = sc.nextInt();
        if (choice == 1){
        order();
        } else if (choice == 2){
        cart();
        } else {
            System.out.println("Invalid Input");
            welcome();
        }
    }
     void order(){
         String flower = " "; 
         int price = 0; 
         System.out.println("Add an Order "); 
         System.out.println(" 1. Daisy\t100 PHP");
         System.out.println(" 2. Rose\t150 PHP");
         System.out.println(" 3. Tulip\t110 PHP");
         System.out.println(" 4. Lily\t170 PHP");
         System.out.println(" 5. Peony\t90 PHP");
         System.out.println(" 6. Orchid\t140 PHP");
         System.out.println(" 7. Back");
         System.out.print("Choice: ");
         int choice = sc.nextInt();
         System.out.print("Amount: ");
         int amount = sc.nextInt();
         
         if(choice <= 6 && choice > 0 ){
         if (choice == 1){
             flower = "Daisy"; 
             price = 100;    
         } else if(choice == 2){
             flower = "Rose"; 
             price = 150; 
             
         } else if(choice == 3){
             flower = "Tulip"; 
             price = 110; 
         } else if(choice == 4){
             flower = "Lily"; 
             price = 170; 
         } else if(choice == 5){
             flower = "Peony"; 
             price = 90; 
         } else if(choice == 6){
             flower = "Orchid"; 
             price = 140; 
         }                   
        FlowerNode fObjNode = new FlowerNode(flower, amount, (amount*price));  
            if (fHead == null ) {
                fHead = fObjNode;
                fTail = fObjNode;       
            } else {
                fTail.next = fObjNode;
                fTail = fObjNode;
            }
            welcome();
     } else if(choice == 7) {
         welcome();
         } else {
             System.out.println("Invalid Input");
             welcome();
     }
}
     void cart(){
        System.out.println("Flower \t Amount\t Price");
        FlowerNode fCurrent = fHead;

        
        while (fCurrent != null) {
            System.out.println(fCurrent.flower + " " +"\t " +fCurrent.amount+ " " +" \t " + fCurrent.price); 
            subtotal = subtotal+fCurrent.price;
            fCurrent = fCurrent.next;
          
        }
        System.out.println("Sub Total: \t"+subtotal);
        System.out.println(" ");
        
         System.out.println("1. Sort by Price");
         System.out.println("2. Sort by Amount");
         System.out.println("3. Check Out");
         System.out.print("Choice: ");
         int choice = sc.nextInt();
         if (choice == 1){
     
        } else if (choice == 2){
        cart();
        } else if (choice == 3){    
            checkOut();
        }
        }
     void checkOut(){
         System.out.println("Enter Cash: ");
         int cash = sc.nextInt();
         if (cash >= subtotal){
         int change = subtotal - cash;
             System.out.println("Change: " + change);
             welcome();
         } else {
             System.out.println("Invalid Amount");
             welcome();
         }
     }
}

public class Tester{
   public static void main(String[] args) {
   Functions f = new Functions();
   f.welcome();
   }
    
}