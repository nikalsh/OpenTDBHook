package src.opentdbhook;

/**
 *
 * @author nikalsh
 */
public class POJOQuestion {

    int response_code;
    POJOResults[] results;

    public POJOQuestion() {
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public void setResults(POJOResults[] results) {
        this.results = results;
    }

    public int getResponse_code() {
        return response_code;
    }

    public POJOResults[] getResults() {
        return results;
    }
    
    
    
    

}
