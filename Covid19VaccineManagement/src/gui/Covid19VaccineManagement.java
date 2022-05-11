package gui;

import container.InjectionList;
import util.Menu;
import util.Tools;

/**
 *
 * @author KhoaHD.7621
 */
public class Covid19VaccineManagement {

    public static void main(String[] args) {
        InjectionList injectionList = new InjectionList();
        injectionList.addFromFile();
        System.out.println("Welcome to Covid 19 Vaccine Management - @2021 by <SE150848 - Hoàng Đăng Khoa>");
        int choice;
        boolean check = true;
        while (check) {
            Menu.mainMenu();
            choice = Tools.getAnInteger("Enter your choice: ", "\nOut of range! Only accept choice from 1 to 9! Try again!\n", 1, 9);
            switch (choice) {
                case 1:
                    System.out.println("\nYou choose show information all students have been injected!\n");
                    injectionList.showInformationOfAllStudentInjection();
                    break;
                case 2:
                    System.out.println("\nYou choose add student's vaccine injection information!\n");
                    injectionList.addNewInjection();
                    break;
                case 3:
                    System.out.println("\nYou choose updating information of students' vaccine injection!\n");
                    injectionList.updateInjecton();
                    break;
                case 4:
                    System.out.println("\nYou choose delete student vaccine injection information\n");
                    injectionList.removeInjection();
                    break;
                case 5:
                    System.out.println("\nYou choose search for injection information by studentId!\n");
                    do {
                        System.out.println("What do you want to do?");
                        System.out.println("1. Search injection by student id");
                        System.out.println("2. Search injection by student name");
                        int choice1 = Tools.getAnInteger("Enter your choice: ", "\nWrong format or Out of range choice! Try again!\n", 1, 2);
                        switch (choice1) {
                            case 1:
                                injectionList.searchInjectionByStudentId();
                                break;
                            case 2:
                                injectionList.searchInjectionByStudentName();
                                break;
                        }
                    }
                    while (Tools.getYesNo("Do you want to search another information? [Yes-Y/No-N]: "));
                    break;
                case 6:
                    System.out.println("\nYou choose print injection information!\n");
                    do {
                        System.out.println("What do you want to do?");
                        System.out.println("1. List of students who have had injection 1.");
                        System.out.println("2. List of students who have had both injections.");
                        System.out.println("3. List of students who have not had any injections.");
                        int choice2 = Tools.getAnInteger("Enter your choice: ", "\nWrong format or Out of range choice! Try again!\n", 1, 3);
                        System.out.println();
                        switch (choice2) {
                            case 1:
                                injectionList.printListOfStudentWhoHaveHadInjection1();
                                break;
                            case 2:
                                injectionList.printListOfStudentsWhoHaveHadBothInjections();
                                break;
                            case 3:
                                injectionList.printListOfStudentWhoHaveNotHadAnyInjection();
                                break;
                        }
                    }
                    while (Tools.getYesNo("Do you want to continue performing other printing operations? [Yes-Y/No-N]: "));
                    break;
                case 7:
                    System.out.println("\nYou choose store data to file!\n");
                    injectionList.saveToFile();
                    System.out.println("Export injection list to file \"injection.dat\" successfully!\n");
                    break;
                case 8:
                    System.out.println("\nYou choose store data to file with encrypt MDF5!\n");
                    injectionList.saveToFileWithEncryptMDF5();
                    break;
                case 9:
                    System.out.println("\nYou choose quit Vaccine Covid 19 Management!\n");
                    check = false;
                    break;
            }
        }
        System.out.println("Good bye! See you again!");
    }
}
