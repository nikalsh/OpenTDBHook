package server.OpenTriviaDataBaseAPIHook;

/**
 *
 * @author nikalsh
 */
class DAOToken {

    int response_code;
    String response_message;
    String token;

    public DAOToken() {
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public void setResponse_message(String response_message) {
        this.response_message = response_message;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    
    

}
