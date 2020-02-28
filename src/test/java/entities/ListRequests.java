package entities;

public class ListRequests {

    private String name;
    private String description;
    private String language;
    private String list_id;
    private String status_code;
    private String item_present;

    public ListRequests(){ }

    public ListRequests(String name, String description, String language,
                           String list_id, String status_code, String item_present) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.list_id = list_id;
        this.status_code = status_code;
        this.item_present = item_present;
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

    public String getStatus_code() {return status_code;}

    public void setStatus_code(String status_code) {this.status_code = status_code;}

    public String getItem_present() {return item_present;}

    public void setItem_present(String item_present) {this.item_present = item_present;}



}
