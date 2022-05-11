package data;

/**
 *
 * @author KhoaHD.7621
 */
public class Vaccine {
    
    private String id;
    private String name;

    public Vaccine() {
    }

    public Vaccine(String id, String name) {
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

    @Override
    public String toString() {
        return id + "," + name;
    }
    
    public void printVaccine() {
        System.out.printf("| %-6s | %-25s |\n", id, name);
    }
}
