package container;

import data.Injection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import security.MD5Encryptor;
import util.Tools;

/**
 *
 * @author KhoaHD.7621
 */
public class InjectionList {
    
    private List<Injection> list;
    private StudentList listStudent;
    private VaccineList listVaccine;
    
    public InjectionList() {
        list = new ArrayList<>();
        listStudent = new StudentList();
        listStudent.addFromFile();
        listVaccine = new VaccineList();
        listVaccine.addFromFile();
    }
    
    public int getSize() {
        return list.size();
    }
    
    public int searchInjectionByIdReturnIndex(String id) {
        if (list.isEmpty())
            return -1;
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).getId().equalsIgnoreCase(id))
                return i;
        return -1;
    }
    
    public Injection searchInjectionByIdReturnPointer(String id) {
        if (list.isEmpty())
            return null;
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).getId().equalsIgnoreCase(id))
                return list.get(i);
        return null;
    }
    
    public void showInformationOfAllStudentInjection() {
        if (list.isEmpty())
            System.out.println("Injection list is empty! Nothing to show!\n");
        else {
            System.out.println("|   ID   | StudentId | VaccineId |     1st Injection Place    | 1st Injection Date |     2nd Injection Place    | 2nd Injection Date |");
            for (Injection injection : list)
                injection.printInjection();
            System.out.println();
        }
    }
    
    private void addAllInjection(String studentId) {
        String id;
        while (true) {
            id = Tools.getAPattern("*Enter injection Id (IJxxxx): ",
                                    "Error! You have left blank or typed the wrong format!\nThe injection Id format is IJxxxx, x stands for digit.",
                                    "^[i,I]{1}[j,J]{1}[0-9]{4}$").toUpperCase().trim();
            if (list.isEmpty())
                break;
            else
                if (searchInjectionByIdReturnPointer(id) == null)
                    break;
                else
                    System.out.println("Id is duplicated. Try again!");
        }
        listVaccine.printVaccineList();
        String vaccineId;
        while (true) {
            vaccineId = Tools.getAPattern("*Enter vaccine Id (VAxxxx): ",
                                          "Error! You have left blank or typed the wrong format!\nThe vaccine Id format is VAxxxx, x stands for digit.",
                                          "^[v,V]{1}[a,A]{1}[0-9]{4}$").toUpperCase().trim();
            if (listVaccine.searchVaccineId(vaccineId) != null)
                break;
            else
                System.out.println("There is no vaccine with the id \"" + vaccineId +"\"! Try again!");
            }
        String place1 = Tools.getAString("*Enter 1st injection place: ", "Error! This field must be filled in, cannot be left blank!");
        Date date1 = Tools.getADate("Enter 1st Injection Date (yyyy/m/d): ", "Date is invalid! Try again!");
        String place2 = Tools.getAString("*Enter 2nd injection place: ", "Error! This field must be filled in, cannot be left blank!");
        Date date2;
        while (true) {
            date2 = Tools.getADate("Enter 2nd Injection Date (yyyy/m/d): ", "Date is invalid! Try again!");
            if (Tools.daysBetween2Dates(date1, date2) >= 28)
                break;
            else
                System.out.println("Invalid! The date of the 2nd injection must be after 28 days count from the 1st day. Try again!");
        }
        list.add(new Injection(id, place1, date1, place2, date2, studentId, vaccineId));
        System.out.println("\nAdd injection information success! This student completed 2 injections!\n");
    }
    
    private void addFirstInjection(String studentId) {
        String id;
        while (true) {
            id = Tools.getAPattern("*Enter injection Id (IJxxxx): ",
                                    "Error! You have left blank or typed the wrong format!\nThe injection Id format is IJxxxx, x stands for digit.",
                                    "^[i,I]{1}[j,J]{1}[0-9]{4}$").toUpperCase().trim();
            if (list.isEmpty())
                break;
            else
                if (searchInjectionByIdReturnPointer(id) == null)
                    break;
                else
                    System.out.println("Id is duplicated. Try again!");
        }
        listVaccine.printVaccineList();
        String vaccineId;
        while (true) {
            vaccineId = Tools.getAPattern("*Enter vaccine Id (VAxxxx): ",
                                          "Error! You have left blank or typed the wrong format!\nThe vaccine Id format is VAxxxx, x stands for digit.",
                                          "^[v,V]{1}[a,A]{1}[0-9]{4}$").toUpperCase().trim();
            if (listVaccine.searchVaccineId(vaccineId) != null)
                break;
            else
                System.out.println("There is no vaccine with the id \"" + vaccineId +"\"! Try again!");
        }
        String place1 = Tools.getAString("*Enter 1st injection place: ", "Error! This field must be filled in, cannot be left blank!");
        Date date1 = Tools.getADate("Enter 1st Injection Date (yyyy/m/d): ", "Date is invalid! Try again!");
        Injection injection = new Injection(id, place1, date1, null, null, studentId, vaccineId);
        list.add(injection);
        Date d = new Date(System.currentTimeMillis());
        if (Tools.daysBetween2Dates(date1, d) >= 28)
            if (Tools.getYesNo("This student injected more than 28 days ago, would you like to add the 2nd injection for this student? [Yes-Y/No-N]: "))
                addSecondInjection(studentId, date1);
            else
                System.out.println("\nAdd injection information success!\n");
        else
            System.out.println("\nAdd injection information success!\n");
    }
    
    private void addSecondInjection(String studentId, Date date1) {
        String place2 = Tools.getAString("*Enter 2nd injection place: ", "Error! This field must be filled in, cannot be left blank!");
        Date date2;
        while (true) {
            date2 = Tools.getADate("Enter 2nd Injection Date (yyyy/m/d): ", "Date is invalid! Try again!");
            if (Tools.daysBetween2Dates(date1, date2) >= 28)
                break;
            else
                System.out.println("Invalid! The date of the 2nd injection must be after 28 days count from the 1st day. Try again!");
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStudentId().equalsIgnoreCase(studentId)) {
                list.get(i).setPlace2(place2);
                list.get(i).setDate2(date2);
            }
        }
        System.out.println("\nAdd injection information success! This student completed 2 injections!\n");
    }
    
    public void addNewInjection() {
        do {
            String studentId;
            while (true) {
                studentId = Tools.getAPattern("*Enter student Id (SExxxxxx): ",
                                       "Error! You have left blank or typed the wrong format!\nThe student Id format is SExxxxxx, x stands for digit.",
                                       "^[s,S]{1}[e,E]{1}[0-9]{6}$").toUpperCase().trim();
                if (listStudent.searchStudentId(studentId) != null)
                    break;
                else
                    System.out.println("Student does not exist! Try again!");
            }
            Injection tmp = null;
            for (int i = 0; i < list.size(); i++)
                if (list.get(i).getStudentId().equalsIgnoreCase(studentId))
                    tmp = list.get(i);
            if (tmp != null) {
                if (tmp.getPlace1() != null && tmp.getPlace2() != null)
                    System.out.println("This student has completed 2 injections. No more input required.!");
                else {
                    if (Tools.getYesNo("This student has had the 1st injection. Would you like to enter the 2nd injection information by now? [Yes-Y/No-N]: ")) {
                        addSecondInjection(studentId, tmp.getDate1());
                        saveToFile();
                    }
                    else
                        System.out.println("You choose not! That student's injection information remains the same!");
                }
            }
            else {
                System.out.println("\nThis student has no injection information yet! What do you want to do?");
                System.out.println("1. Add 1st injection information.");
                System.out.println("2. Add both 1st and 2nd injection information.");
                System.out.println("3. Cancel - Do not add injection information for this student.");
                int choice = Tools.getAnInteger("Enter your choice: ", "\nWrong format or Out of range choice! Try again!\n", 1, 3);
                switch (choice) {
                    case 1:
                        addFirstInjection(studentId);
                        saveToFile();
                        break;
                    case 2:
                        addAllInjection(studentId);
                        saveToFile();
                        break;
                    case 3:
                        System.out.println("\nCancel! Do not add this student's injection information!\n");
                        break;
                }
            }
        }
        while (Tools.getYesNo("Do you want to add another new injection? [Yes-Y/No-N]: "));
        System.out.println();
    }
    
    public void updateInjecton() {
        String injectionId = Tools.getAPattern("*Enter injection Id you wanna update 2nd information (IJxxxx): ",
                                    "Error! You have left blank or typed the wrong format!\nThe injection Id format is IJxxxx, x stands for digit.",
                                    "^[i,I]{1}[j,J]{1}[0-9]{4}$").toUpperCase().trim();
        Injection tmp = searchInjectionByIdReturnPointer(injectionId);
        if (tmp == null)
            System.out.println("\nInjection does not exist! Nothing to update!\n");
        else {
            System.out.println("\nHere is the information of this Injection:");
            System.out.printf("| Injection ID: %s\n", tmp.getId());
            System.out.printf("| Student ID: %s, Student Name: %s\n", tmp.getStudentId(), listStudent.searchStudentId(tmp.getStudentId()).getName());
            System.out.printf("| Vaccine ID: %s, Vaccine Name: %s\n", tmp.getVaccineId(), listVaccine.searchVaccineId(tmp.getVaccineId()).getName());
            System.out.printf("| 1st Injection date: %10s, 1st Injection place: %s\n", tmp.getDate1(), tmp.getPlace1());
            System.out.printf("| 2nd Injection date: %10s, 2nd Injection place: %s\n", tmp.getDate2() == null ? "No infor" : tmp.getDate2(), tmp.getPlace2() == null ? "No infor" : tmp.getPlace2());
            Date date2;
            String place2;
            if (Tools.getYesNo("\nDo you want to update 2nd information of this injection? [Yes-Y/No-N]: ")) {
                place2 = Tools.getAString("\nEnter 2nd injection place: ", "Error! This field must be filled in, cannot be left blank!");
                while (true) {
                date2 = Tools.getADate("Enter 2nd Injection Date (yyyy/m/d): ", "Date is invalid! Try again!");
                if (Tools.daysBetween2Dates(tmp.getDate1(), date2) >= 28)
                    break;
                else
                    System.out.println("Invalid! The date of the 2nd injection must be after 28 days count from the 1st day. Try again!");
                }
                tmp.setDate2(date2);
                tmp.setPlace2(place2);
                System.out.println("\nUpdate successfully! Here is the information of this Injection after update:");
                System.out.printf("| Injection ID: %s\n", tmp.getId());
                System.out.printf("| Student ID: %s, Student Name: %s\n", tmp.getStudentId(), listStudent.searchStudentId(tmp.getStudentId()).getName());
                System.out.printf("| Vaccine ID: %s, Vaccine Name: %s\n", tmp.getVaccineId(), listVaccine.searchVaccineId(tmp.getVaccineId()).getName());
                System.out.printf("| 1st Injection date: %10s, 1st Injection place: %s\n", tmp.getDate1(), tmp.getPlace1());
                System.out.printf("| 2nd Injection date: %10s, 2nd Injection place: %s\n\n", tmp.getDate2() == null ? "No infor" : tmp.getDate2(), tmp.getPlace2() == null ? "No infor" : tmp.getPlace2());
                saveToFile();
            }
            else
                System.out.println("\nNot accepted! Not updated! Information about injection remains the same!\n");
        }
    }
    
    public void addFromFile() {
        try {
            File f = new File("injection.dat");
            if (!f.exists())
                return;
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String id = stk.nextToken();
                String place1 = stk.nextToken();
                String inputDate1 = stk.nextToken();
                Date date1 = Tools.getADate(inputDate1);
                String place2 = stk.nextToken();
                String inputDate2 = stk.nextToken();
                String studentId = stk.nextToken();
                String vaccineId = stk.nextToken();
                Injection injection = new Injection(id, place1, date1, place2.equals("null") ? null : place2, inputDate2.equals("null") ? null : Tools.getADate(inputDate2), studentId, vaccineId);
                list.add(injection);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
        }
    }
    
    public void removeInjection() {
        if (list.isEmpty())
            System.out.println("The list is empty! Nothing to remove!\n");
        else {
            String id = Tools.getAPattern("Enter injection Id you wanna remove (IJxxxx): ",
                                      "Error! You have left blank or typed the wrong format!\nThe injection Id format is IJxxxx, x stands for digit.",
                                      "^[i,I]{1}[j,J]{1}[0-9]{4}$").toUpperCase().trim();
            Injection injection = searchInjectionByIdReturnPointer(id);
            if (injection == null)
                System.out.println("\nInjection Id does not exist! Don't remove anything!\n");
            else {
                boolean response = Tools.getYesNo("\nAre you confirm to remove this injection [Yes-Y/No-N]: ");
                if (response) {
                    list.remove(injection);
                    System.out.println("\nAccepted! Remove injection sucessfully!\n");
                }
                else
                    System.out.println("\nNot accepted! Injection is not removed!\n");
            }
        }
    }
    
    public void searchInjectionByStudentId() {
        if (list.isEmpty())
            System.out.println("The list is empty! Nothing to search!\n");
        else {
            String studentId = Tools.getAPattern("*Enter student Id you wanna search injection (SExxxxxx): ",
                                       "Error! You have left blank or typed the wrong format!\nThe student Id format is SExxxxxx, x stands for digit.",
                                       "^[s,S]{1}[e,E]{1}[0-9]{6}$").toUpperCase().trim();
            int count = 0;
            for (int i = 0; i < list.size(); i++)
                if (list.get(i).getStudentId().equalsIgnoreCase(studentId)) {
                    System.out.println("\n|   ID   | StudentId | VaccineId |     1st Injection Place    | 1st Injection Date |     2nd Injection Place    | 2nd Injection Date |");
                    list.get(i).printInjection();
                    System.out.println();
                    count++;
                    break;
                }
            if (count == 0)
                System.out.println("\nNot found! This student has not been vaccinated!\n");
        }
    }
    
    public void searchInjectionByStudentName() {
        if (list.isEmpty())
            System.out.println("\nThe list is empty! Nothing to search!\n");
        else {
            String name = Tools.getAString("Enter student name you wanna search injection information: ").toUpperCase().trim();
            System.out.println();
            String arr[] = listStudent.searchStudentByNameReturnStudentId(name);
            int count = 0;
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < arr.length; j++) {
                    if (list.get(i).getStudentId().equalsIgnoreCase(arr[j])) {
                        System.out.println("Name: " + listStudent.searchStudentId(arr[j]).getName());
                        System.out.println("\n|   ID   | StudentId | VaccineId |     1st Injection Place    | 1st Injection Date |     2nd Injection Place    | 2nd Injection Date |");
                        list.get(i).printInjection();
                        count++;
                    }
                }
            }
            if (count > 0)
                System.out.println();
            else 
                System.out.println("No injection information available for this student \"" + name + "\"!\n");
        }
    }
    
    public void saveToFile() {
        if (list.isEmpty()) {
            System.out.println("Injection list is empty! Nothing to save!\n");
            return;
        }
        try {
            File f = new File("injection.dat");
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Injection injection : list)
                pw.println(injection.toString());
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
        public void saveToFileWithEncryptMDF5() {
        if (list.isEmpty()) {
            System.out.println("Injection list is empty! Nothing to save!\n");
            return;
        }
        try {
            File f = new File("injection.txt");
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Injection injection : list)
                pw.println(MD5Encryptor.encrypt(injection.toString()));
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Export injection list to file \"injection.txt\" successfully!\n");
    }
}
