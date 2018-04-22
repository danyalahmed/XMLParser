/**
 * Created by dani1 on 20/10/2017.
 */
public class Parameter {
    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
