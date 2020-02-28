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



    public String getDescription() {
        return description;
    }



    public String getLanguage() {
        return language;
    }



    public String getList_id() {
        return list_id;
    }



    public String getStatus_code() {return getStatus_code;}



    public String getSuccess() {return success;}





}
