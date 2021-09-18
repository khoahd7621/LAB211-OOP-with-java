package data;

/**
 *
 * @author KhoaHD.7621
 */
public class Student {
    
    private String id;
    private String name;

    public Student() {
    }

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
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
    
    public void printStudent() {
        System.out.printf("| %-6s | %-25s |", id, name);
    }

    @Override
    public String toString() {
        return id + "," + name;
    }
}
