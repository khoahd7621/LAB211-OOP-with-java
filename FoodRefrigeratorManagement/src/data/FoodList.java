package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Date;
import java.util.List;
import java.util.StringTokenizer;
import util.Tools;

/**
 *
 * @author KhoaHD.7621
 */
public class FoodList {
    
    private List <Food> list;
    
    public FoodList() {
        list = new ArrayList();
        readFile();
    }
    
    public Food searchFoodIdReturnPointer(String id) {
        if (list.isEmpty())
            return null;
        for (int i = 0; i < list.size(); i++)
            if(list.get(i).getId().equalsIgnoreCase(id))
                return list.get(i);
        return null;
    }
    
    public void addFood() {
        int count = 0;
        do {
            String id, name, type, place;
            Date date;
            double weight;
            while (true) {
                id = Tools.getAPattern("Enter food id (Up to 6 digits): ", "Wrong format! Accepts only digits and up to 6 digits!", "^\\d{1,6}$").trim();
                if (searchFoodIdReturnPointer(id) == null)
                    break;
                else
                    System.out.println("Id is duplicated! Try again!");
            }
            name = Tools.getAString("Enter food name: ").toUpperCase().trim();
            weight = Tools.getADouble("Enter food weight (0.1 - 10.0 (kg)): ", "Wrong format or out of range! Weight range is 0.1 to 10.0!", 0, 10);
            type = Tools.getAString("Enter food type: ").toUpperCase().trim();
            place = Tools.getAString("Enter food storage place: ").toUpperCase().trim();
            date = Tools.getADate("Enter food expired date (yyyy/m/d): ", "Date is invalid! Try again!");
            list.add(new Food(id, name, weight, type, place, date));
            count++;
        }
        while (Tools.getYesNo("\nDo you want to add another food [Yes-Y/No-N]: "));
        System.out.println("\nAdd " + count + " food(s) succesfully!\n");
    }
    
    public void searchFoodByName() {
        if (list.isEmpty())
            System.out.println("Refrigerator is empty! Nothing to search!\n");
        else {
            do {
                int count = 0;
                String name = Tools.getAString("Enter food name you wanna search: ").toUpperCase().trim();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getName().contains(name)) {
                        list.get(i).printFood();
                        count++;
                    }
                }
                if (count == 0)
                    System.out.println("\nDo not exist food with name \"" + name + "\" !");
            }
            while (Tools.getYesNo("\nDo you want to search another food [Yes-Y/No-N]: "));
            System.out.println();
        }
    }
    
    public void removeFood() {
        if (list.isEmpty())
            System.out.println("Refrigerator is empty! Nothing to remove!\n");
        else {
            String id;
            Food food;
            while (true) {
                id = Tools.getAString("Enter food id you want to remove: ").trim();
                food = searchFoodIdReturnPointer(id);
                if (food != null)
                    break;
                else
                    System.out.println("\nThere is no food in the fridge with the id \"" + id + "\"! Type again.\n");
            }
            boolean response = Tools.getYesNo("\nAre you confirm to remove this food [Yes-Y/No-N]: ");
            if (response) {
                    list.remove(food);
                    System.out.println("\nAccepted! Remove food sucessfully!\n");
            }
            else
                System.out.println("\nNot accepted! Food is not removed!\n");
        }
    }
    
    public void sortListFoodByExpiredDateDecsending() {
        Collections.sort(list);
    }
    
    public void printListFood() {
        if (list.isEmpty())
            System.out.println("Refrigerator is empty! Nothing to show!");
        else {
            sortListFoodByExpiredDateDecsending();
            System.out.println("|   ID   |            NAME           |  WEIGHT |    TYPE    |      PLACE     | EXPIRED DATE |");
            for (int i = 0; i < list.size(); i++)
                list.get(i).printFood();
        }
    }
    
    public void readFile() {
        try {
            FileReader fr = new FileReader("food.txt");
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String id = stk.nextToken();
                String name = stk.nextToken();
                double weight = Double.parseDouble(stk.nextToken());
                String type = stk.nextToken();
                String place = stk.nextToken();
                String inputDate = stk.nextToken();
                Date date = Tools.getADate(inputDate);
                list.add(new Food(id, name, weight, type, place, date));
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
        }
    }
    
    public void saveToFile() {
        if (list.isEmpty()) {
            System.out.println("Refrigerator is empty! Nothing to export!\n");
            return;
        }
        try {
            File f = new File("food.txt");
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Food food : list)
                pw.println(food.toString());
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Export food list to file \"food.txt\" successfully!\n");
    }
}