package entities;

public class ListRequests {

    private String name;
    private String description;
    private String language;
    private String list_id;
    private String getStatus_code;
    private String success;

    public ListRequests(){ }

    public ListRequests(String name, String description, String language,
                           String list_id, String success) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.list_id = list_id;
        this.getStatus_code = getStatus_code;
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {this.description = description;}

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    public String getStatus_code() {return getStatus_code;}

    public void setStatus_code(String getStatus_code) {this.getStatus_code = getStatus_code;}

    public String getSuccess() {return success;}

    public void setSuccess(String success) {this.success = success;}



}
