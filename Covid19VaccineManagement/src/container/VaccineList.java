package container;

import data.Vaccine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author KhoaHD.7621
 */
public class VaccineList {
    
    private List <Vaccine> list;
    
    public VaccineList() {
        list = new ArrayList<>();
    }
    
    public void addVaccine(Vaccine vaccine) {
        list.add(vaccine);
    }
    
    public Vaccine searchVaccineId(String vaccineId) {
        if (list.isEmpty())
            return null;
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).getId().equalsIgnoreCase(vaccineId))
                return list.get(i);
        return null;
    }
    
    public void printVaccineList() {
        System.out.println("List of vaccine available:");
        System.out.println("|   Id   |            Name           |");
        for (int i = 0; i < list.size(); i++)
            list.get(i).printVaccine();
    }
    
    public void addFromFile() {
        try {
            File f = new File("vaccine.dat");
            if (!f.exists())
                return;
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String id = stk.nextToken();
                String name = stk.nextToken();
                Vaccine vaccine = new Vaccine(id, name);
                list.add(vaccine);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
        }
    }
    
    public void saveToFile() {
        if (list.isEmpty()) {
            System.out.println("Vaccine list is empty! Nothing to save!\n");
            return;
        }
        try {
            File f = new File("vaccine.dat");
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Vaccine vaccine : list)
                pw.println(vaccine.toString());
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Export vaccine list to file \"vaccine.dat\" successfully!\n");
    }
}
