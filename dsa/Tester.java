
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
        System.out.println("\nWelcome to Flower Shop!");
        System.out.println("1. Add Order");
        System.out.println("2. Exit");
        System.out.print("Choice: ");
        int choice = sc.nextInt();
        if (choice == 1){
        order();
	    } else if (choice == 2){
	    System.out.println("\nThank you! Have a nice day!");
	    System.exit(0);
	        }else {
	        	
	            System.out.println("Invalid Input");
	            welcome();
	        }
    }
    void vcart() {
    	System.out.println("\nWelcome to Flower Shop!");
        System.out.println("1. Add Order");
        System.out.println("2. View Cart");
        System.out.println("3. Exit");
        System.out.print("Choice: ");
        int choice = sc.nextInt();
        if (choice == 1){
        order();
        } else if (choice == 2){
        cart();
	    } else if (choice == 3){
	    System.out.println("\nThank you! Have a nice day!");
	    System.exit(0);
	        }else {
	            System.out.println("Invalid Input");
	            welcome();
	        }
    	
    }
    void mergeDupes() {
        FlowerNode current = fHead;
        while (current != null) {
            FlowerNode dupe = current;
            while (dupe.next != null) {
                if (dupe.next.flower.equals(current.flower)) {
                    current.amount += dupe.next.amount;
                    current.price += dupe.next.price;
                    dupe.next = dupe.next.next;
                } else {
                	dupe = dupe.next;
                }
            }
            current = current.next;
        }
    }
     void order(){
         String flower = " "; 
         int price = 0; 
         System.out.println("\nAdd an Order "); 
         System.out.println(" 1. Daisy\t100 PHP");
         System.out.println(" 2. Rose\t150 PHP");
         System.out.println(" 3. Tulip\t110 PHP");
         System.out.println(" 4. Lily\t170 PHP");
         System.out.println(" 5. Peony\t90 PHP");
         System.out.println(" 6. Orchid\t140 PHP");
         System.out.println(" 7. Back");
         System.out.print("Choice: ");
         int choice = sc.nextInt();
         if(choice == 7) {
        	 welcome();
         }
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
         System.out.print("Amount: ");
         int amount = sc.nextInt();
         
                       
        System.out.print("Flower Added to Cart: "+flower+" "+amount+"pcs\n");
        FlowerNode fObjNode = new FlowerNode(flower, amount, (amount*price));  
            if (fHead == null ) {
                fHead = fObjNode;
                fTail = fObjNode;       
            } else {
                fTail.next = fObjNode;
                fTail = fObjNode;
            }
            vcart();
         } else {
             System.out.println("Invalid Input");
             vcart();
     }
}
     void sortByPrice() {
         if (fHead == null) return;
         boolean swapped;
         do {
             swapped = false;
             FlowerNode price = fHead;
             while (price.next != null) {
                 if (price.price > price.next.price) {
                     String tempFlower = price.flower;
                     int tempAmount = price.amount;
                     int tempPrice = price.price;
                     price.flower = price.next.flower;
                     price.amount = price.next.amount;
                     price.price = price.next.price;

                     price.next.flower = tempFlower;
                     price.next.amount = tempAmount;
                     price.next.price = tempPrice;

                     swapped = true;
                 }
                 price = price.next;
             }
         } while (swapped);
     }
     void sortByAmount() {
         if (fHead == null) return;
         boolean swapped;
         do {
             swapped = false;
             FlowerNode amount = fHead;
             while (amount.next != null) {
                 if (amount.amount < amount.next.amount) {
                     String tempFlower = amount.flower;
                     int tempAmount = amount.amount;
                     int tempPrice = amount.price;
                     amount.flower = amount.next.flower;
                     amount.amount = amount.next.amount;
                     amount.price = amount.next.price;

                     amount.next.flower = tempFlower;
                     amount.next.amount = tempAmount;
                     amount.next.price = tempPrice;

                     swapped = true;
                 }
                 amount = amount.next;
             }
         } while (swapped);
     }

     void cart(){
    	    subtotal = 0;
    	    mergeDupes();

    	    System.out.println("\nFlower \t Amount\t Price");
    	    FlowerNode fCurrent = fHead;

    	    while (fCurrent != null) {
    	        System.out.println(fCurrent.flower + " \t " + fCurrent.amount + " \t " + fCurrent.price); 
    	        subtotal += fCurrent.price;
    	        fCurrent = fCurrent.next;
    	    }

    	    System.out.println("Sub Total: \t" + subtotal);
    	    System.out.println(" ");
    	    System.out.println("1. Sort by Price");
    	    System.out.println("2. Sort by Amount");
    	    System.out.println("3. Add Order");
    	    System.out.println("4. Check Out");
    	    System.out.print("Choice: ");
    	    int choice = sc.nextInt();
    	    if (choice == 1){
    	        sortByPrice();
    	        cart();	
    	    } else if (choice == 2){
    	        sortByAmount();
    	        cart();
    	    } else if (choice == 3){    
    	        order();
    	    } else if (choice == 4){    
    	        checkOut();
    	    } else {
                System.out.println("Invalid Input");
                cart();
            }
    	}

     void checkOut(){
         System.out.println("Enter Cash: ");
         int cash = sc.nextInt();
         if (cash >= subtotal){
         int change = cash - subtotal;
             System.out.println("Change: ₱" + change);
             welcome();
         } else {
             System.out.println("Invalid Amount\n");
             cart();
         }
     }
}

public class Tester{
   public static void main(String[] args) {
   Functions f = new Functions();
   f.welcome();
   }
    
}