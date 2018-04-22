import java.util.ArrayList;
import java.util.List;

/**
 * Created by dani1 on 20/10/2017.
 */
public class Method {
    private String id;
    private String visibility;
    private List<Parameter> parameters = new ArrayList<>();
    private List<String> exceptions = new ArrayList<>();
    private String returnType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(Parameter parameter) {
        this.parameters.add(parameter);
    }

    public List<String> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<String> exceptions) {
        this.exceptions = exceptions;
    }

    public void addException(String exception) {
        this.exceptions.add(exception);
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return "uk.ac.le.cs.module.Method{" +
                "id='" + id + '\'' +
                ", visibility='" + visibility + '\'' +
                ", parameters=" + parameters.toString() +
                ", exceptions=" + exceptions +
                ", returnType='" + returnType + '\'' +
                '}';
    }
}
