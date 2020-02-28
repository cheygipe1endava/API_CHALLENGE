package entities;



public class SessionRequests {

    private String success;
    private String expires_at;
    private String request_token;
    private String session_id;
    private String guest_session_id;
    private String delete_session_id;

    public SessionRequests() {
    }

    public SessionRequests(String success, String expires_at, String request_token,
                           String session_id, String guest_session_id, String delete_session_id) {
        this.success = success;
        this.expires_at = expires_at;
        this.request_token = request_token;
        this.session_id = session_id;
        this.guest_session_id = guest_session_id;
        this.delete_session_id = delete_session_id;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {this.success = success;}

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {this.expires_at = expires_at;}

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getGuest_session_id() {return guest_session_id;}

    public void setGuest_session_id(String guest_session_id) {this.guest_session_id = guest_session_id;}

    public String getDelete_session_id() {return delete_session_id;}

    public void setDelete_session_id(String delete_session_id) {this.delete_session_id = delete_session_id;}



}