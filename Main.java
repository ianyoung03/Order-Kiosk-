import java.util.Scanner;
class Main {

	public static double taxCalculator(double[][] array)
	{
		double subtotal = 0;
		for (int i = 0; i < 5; i++)
		{
			subtotal = subtotal + array[i][2]; 
		}
		return subtotal * 1.13;
	}	
	

  public static void main(String[] args) 
	{
		///////////declaring variables
		Scanner scan = new Scanner (System.in);
		
		int itemChoice = 0, quantity = 0;

		String name = "a";

		int[] userChoices = new int[5];

		double[][] array = new double[5][3];
		double[] prices = {2.50, 5.00, 3.00, 1.50, 4.00};
		
		String[] menuItems = {"Mango", "Chicken", "Fries", "Apple", "Cereal"};
		String[] splitName = new String [2];

	

		//////////printing menu
		System.out.println("MENU:");
		System.out.println("1. Mango: $2.50 each");
		System.out.println("2. Chicken: $5.00 each");
		System.out.println("3. Fries: $3.00 each");
		System.out.println("4. Apple: $1.50 each");
		System.out.println("5. Cereal: $4.00 each");

		//////////getting name
		System.out.println("Please enter your full name (first and last name. ex. Josh Allen)");
			
		name = scan.nextLine();
		splitName = name.split(" ");
	
		//////////getting user input
		for (int i = 0; i < 5; i++)
		{
			try
			{
				System.out.println("Choose an item to buy (1 through 5, or click 6 to quit");
				itemChoice = Integer.parseInt(scan.nextLine());
			}
			catch (Exception e)
			{
				System.out.println("Something went wrong. Please choose the corresponding number to the item you want to buy (1-5) or type 6 to quit");
			}
			if (itemChoice == 6)
				break;
			userChoices[i] = itemChoice - 1;

			
			
			try
			{
				System.out.println("How many of this item would you like to buy");	
				quantity = Integer.parseInt(scan.nextLine());
			}
			catch (Exception e)
			{
				System.out.println("Error. Please type an integer. ex. 3");
			}
			array[i][0] = quantity;
			array[i][1] = prices[itemChoice - 1];
			array[i][2] = quantity * prices[itemChoice - 1];
		
			for (int j = 0; j < i; j++)
			{
				if (userChoices[i] == userChoices[j])
				{
					System.out.println("You repeated a choice! Choose a different item");
					i = i - 1;
				}
				/*else
				{
					try
					{
						System.out.println("How many of this item would you like to buy");	
						quantity = Integer.parseInt(scan.nextLine());
					}
					catch (Exception e)
					{
						System.out.println("Error. Please type an integer. ex. 3");
					}
						array[i][0] = quantity;
						array[i][1] = prices[itemChoice - 1];
						array[i][2] = quantity * prices[itemChoice - 1];
						break;
				}*/
			}
		}

		/////////printing receipt
		System.out.println("RECEIPT:");
		System.out.println("First Name: " + splitName[0]);
		System.out.println("Last Name: " + splitName[1]);
		System.out.println("Quantity Price Subtotal");
		for (int i = 0; i < 5; i++)
		{
			if (array[i][0] == 0) 
				break;
			System.out.print(menuItems[userChoices[i]]);
			for (int j = 0; j < 3; j++)
			{
				System.out.print("   " + array[i][j]);
			}
			System.out.println();
		}
			System.out.println("Your total after taxes is: " + taxCalculator(array));
		
  }
}