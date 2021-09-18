package container;

import data.Student;
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
public class StudentList {
    
    private List <Student> list;

    public StudentList() {
        list = new ArrayList<>();
    }
    
    public void addStudent(Student student) {
        list.add(student);
    }
    
    public int getSize() {
        return list.size();
    }
    
    public String[] searchStudentByNameReturnStudentId (String name) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().contains(name))
                count++;
        }
        String[] arrId = new String[count];
        count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().contains(name)) {
                arrId[count] = list.get(i).getId();
                count++;
            }
        }
        return arrId;
    }
    
    public Student searchStudentId(String studentId) {
        if (list.isEmpty())
            return null;
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).getId().equalsIgnoreCase(studentId))
                return list.get(i);
        return null;
    }
    
    public void addFromFile() {
        try {
            File f = new File("student.dat");
            if (!f.exists())
                return;
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String details;
            while ((details = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",");
                String id = stk.nextToken();
                String name = stk.nextToken();
                Student student = new Student(id, name);
                list.add(student);
            }
            bf.close();
            fr.close();
        } catch (Exception e) {
        }
    }
    
    public void saveToFile() {
        if (list.isEmpty()) {
            System.out.println("Student list is empty! Nothing to save!\n");
            return;
        }
        try {
            File f = new File("student.dat");
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Student student : list)
                pw.println(student.toString());
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Export student list to file \"student.dat\" successfully!\n");
    }
}
