//importing
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
class Main {
	
	 /////////tax method
   public static double taxCalculator(double preTaxTotal)
   {
      return preTaxTotal * 1.13;
   }	

	 //calculating subtotal method
   public static double preTaxTotalCalculator(double[][] order)
   {
      double preTaxTotal = 0;
      for (int i = 0; i < 5; i++)
      {
         preTaxTotal = preTaxTotal + order[i][3]; 
      }
      return preTaxTotal;
   }


	 /////////item input method with exception checks method
   public static double getItem()
   {
      Scanner scan = new Scanner (System.in);
      boolean checker = true;
			double itemChoice = 0;
      while(checker)
      {
         try
         {
            itemChoice = Double.parseDouble(scan.nextLine());
            if (itemChoice > 6 || itemChoice < 1)
							throw new Exception("");
            
						checker = false;
         }
         catch (Exception e)
         {
            System.out.println("Something went wrong. Enter a valid input(selection between 1 and 5, or 6 to quit)");
         }
      }
      return itemChoice;
   }
	 //////////quantity input method with exception checks
   public static double getQuantity()
   {
      Scanner scan = new Scanner (System.in);
      boolean checker = true;
			double quantity = 0;
      while(checker)
      {
         try
         {
            quantity = Double.parseDouble(scan.nextLine());
            if (quantity< 1  || quantity > 25)
               throw new Exception("");
            
						checker = false;
         }
         catch (Exception e)
         {
            System.out.println("Something went wrong. You can only order between 1 and 25 of the item");
         }
      }
      return quantity;
   }

	 ///////////name input method
   public static String[] getName(String[] splitName)
   {
      Scanner scan = new Scanner (System.in);
      String x, test;
      boolean checker = true;
      while(checker)
      {
         try
         {
            x = scan.nextLine();
            splitName = x.split(" ");
            test = splitName[1];
            checker = false;
         }
         catch (Exception e)
         {
            System.out.println("Something went wrong. Enter your full name (first and last with space in between)");
         }
      }
      return splitName;
   }
	
	 //method to print to file (this method strictly outputs raw data, no labels or strings, just the raw data)
   public static void writeToFile(double[][] order) throws IOException
   {
      File myFile = new File("orderform.txt");
   		
      myFile.createNewFile();
   
      FileWriter myWriter = new FileWriter("orderform.txt");
   
      for (int i = 0; i < 5; i++)
      {
         if (order[i][1] == 0) 
            break;
         myWriter.write(" "+(int)order[i][0]);
         for (int j = 1; j < 4; j++)
         {
            if (j == 2 || j == 3)
               myWriter.write(" "+order[i][j]);
            else
               myWriter.write(" "+ (int) order[i][j]);
         
         }
         myWriter.write("\n");
      }
      myWriter.close();
   }
	 
	 //method to check for repeating inputs on the order
   public static boolean repeatChecker(double[][] order, int i)
   {
      for (int j = 0; j < i; j++)
      {
         if (order[i][0] == order[j][0])
         {
            return false;
         }
      }
      return true;
   }
	////////main method
   public static void main(String[] args) throws IOException
   {
   		///////////declaring variables
      Scanner scan = new Scanner (System.in);
   
      double[][] order = new double[5][4];
      double[] prices = {2.50, 5.00, 3.00, 1.50, 4.00};
   	
      String[] menuItems = {"Mango", "Chicken", "Fries", "Apple", "Cereal"};
      String[] splitName = new String [2];
   
   		//////////printing menu
      System.out.println("MENU:");
      System.out.printf("%5s|%10s |%6s \n", "Item#", "Item Name", "Price");
      for (int i = 0; i < 5; i++)
      {
         System.out.printf("%-5d| %-10s| $%-6.2f \n", i + 1,menuItems[i], prices[i]);
      }
   
   		//////////getting name
      System.out.println("Please enter your full name (first and last name. ex. Josh Allen)");
   		
      splitName = getName(splitName);
      
   
   		//////////getting user input
      for (int i = 0; i < 5; i++)
      {
         
         System.out.println("Choose an item to buy (1 through 5, or click 6 to quit");
         order[i][0] = getItem();
      
         if (order[i][0] == 6)
            break;
      
         if (repeatChecker(order,i))
         {
            System.out.println("How many of this item would you like to buy (max 25)");	
            order[i][1] = getQuantity();
         
            order[i][2] = prices[(int)order[i][0] - 1];
            order[i][3] = order[i][1] * prices[(int)order[i][0] - 1];
         }
         else
         {
            System.out.println("You already picked this item, you can't repeat it. Choose another item or quit");
            i = i - 1;
         }
         
      }
   
   		/////////printing receipt
      System.out.println("RECEIPT:");
      System.out.println("First Name: " + splitName[0]);
      System.out.println("Last Name: " + splitName[1]);
      System.out.printf("%-10s%-14s%-12s%-10s \n","Item", "Quantity", "Price", "Subtotal");
      for (int i = 0; i < 5; i++)
      {
         if (order[i][1] == 0) 
            break;
         System.out.printf("%-14s",menuItems[(int)order[i][0] -1]);
         for (int j = 1; j < 4; j++)
         {
            if (j == 2 || j == 3)
               System.out.printf("$%-10.2f  ",order[i][j]);
            else
               System.out.printf("%-10d", (int) order[i][j]);
         
         }
         System.out.println();
      }
   
      System.out.printf("\n%s $%.2f ","Total Before Tax:",preTaxTotalCalculator(order));
      System.out.printf("\n%s $%.2f ","Total After Tax:" ,taxCalculator(preTaxTotalCalculator(order)));
   
   
   		//writing to file
      writeToFile(order);
   	
   }
}