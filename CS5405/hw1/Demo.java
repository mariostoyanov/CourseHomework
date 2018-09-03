/* Mario Stoyanov *
 * CS5405 HW1     */

package code; 

import java.util.Scanner;
import java.lang.System;

public class Demo {
	public static void main(String args[])	{
		System.out.println("\n\n  ~~Name Reading Program~~\n\n");
		System.out.println("Please enter the following information:\n");
		Scanner input = new Scanner(System.in);

		System.out.printf("Enter Real Name: ");
		String name = input.nextLine(); 

		System.out.printf("Enter Phone Number: ");
		String number = input.nextLine();

		System.out.printf("Enter Address: ");
		String address = input.nextLine();

		System.out.printf("\nYour name is: %s \n", name);
		System.out.printf("Your phone number is: %s \n", number);
		System.out.printf("Your address is: %s \n", address);

		System.out.println("\n  Thank you for using Name Reading Program  \n");
	}
}
