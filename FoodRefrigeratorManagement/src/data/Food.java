package data;

import java.sql.Date;

/**
 *
 * @author KhoaHD.7621
 */
public class Food implements Comparable<Food>{
    private String id;
    private String name;
    private double weight;
    private String type;
    private String place;
    private Date date;

    public Food() {
    }

    public Food(String id, String name, double weight, String type, String place, Date date) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.place = place;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void printFood() {
        System.out.printf("| %-6s | %-25s | %4.1f Kg | %-10s | %-14s |  %-10s  |\n", id, name, weight, type, place, date);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + weight + "," + type + "," + place + "," + date;
    }

    @Override
    public int compareTo(Food o) {
        return -this.date.compareTo(o.date);
    }
}
