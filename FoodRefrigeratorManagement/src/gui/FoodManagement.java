package gui;

import data.FoodList;
import util.Menu;
import util.Tools;

/**
 *
 * @author KhoaHD.7621
 */
public class FoodManagement {
    public static void main(String[] args) {
        FoodList foodList = new FoodList();
        System.out.println("Welcome to Food Management - @2021 by <SE150848 - Hoàng Đăng Khoa>");
        int choice;
        boolean check = true;
        while (check) {
            Menu.mainMenu();
            while (true) {
                choice = Tools.getAnInteger("Enter your choice: ");
                if (choice > 0 && choice < 7)
                    break;
                else
                    System.out.println("\nOut of range! Only accept choice from 1 to 6! Try again!\n");
            }
            switch (choice) {
                case 1:
                    System.out.println("\nYou choose add new food(s) into Refrigerator!\n");
                    foodList.addFood();
                    break;
                case 2:
                    System.out.println("\nYou choose search a food by name!\n");
                    foodList.searchFoodByName();
                    break;
                case 3:
                    System.out.println("\nYou choose remove a food by id!\n");
                    foodList.removeFood();
                    break;
                case 4:
                    System.out.println("\nYou choose print the food list in the descending order of expired date!\n");
                    foodList.printListFood();
                    System.out.println("");
                    break;
                case 5:
                    System.out.println("\nYou choose export list food into file!\n");
                    foodList.saveToFile();
                    break;
                case 6:
                    System.out.println("\nYou choose quit Refrigerator Food Management!\n");
                    check = false;
                    break;
            }
        }
        System.out.println("Good bye! See you again!");
    }
}
