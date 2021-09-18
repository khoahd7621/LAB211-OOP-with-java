package data;

import java.sql.Date;

/**
 *
 * @author KhoaHD.7621
 */
public class Injection {
    
    private String id;
    private String place1;
    private Date date1;
    private String place2;
    private Date date2;
    private String studentId;
    private String vaccineId;
    
    public Injection() {
    }

    public Injection(String id, String place1, Date date1, String place2, Date date2, String studentId, String vaccineId) {
        this.id = id;
        this.place1 = place1;
        this.place2 = place2;
        this.date1 = date1;
        this.date2 = date2;
        this.studentId = studentId;
        this.vaccineId = vaccineId;
    }

    public String getId() {
        return id;
    }

    public String getPlace1() {
        return place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }

    public String getPlace2() {
        return place2;
    }

    public void setPlace2(String place2) {
        this.place2 = place2;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    @Override
    public String toString() {
        return id + "," + place1 + "," + date1 + "," + place2 + "," + date2 + "," + studentId + "," + vaccineId;
    }
    
    public void printInjection() {
        System.out.printf("| %-6s | %-8s  |   %-6s  | %-26s |     %-10s     | %-26s |     %-10s     |\n", id, studentId, vaccineId, place1, date1, (place2 == null) ? "No Infor" : place2, (date2 == null) ? "No Infor" : date2);
    }
}
