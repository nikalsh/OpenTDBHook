package server.OpenTriviaDataBaseAPIHook;

/**
 *
 * @author nikalsh
 */
public class DAOQuestions {

    int response_code;
    DAOResults[] results;

    public DAOQuestions() {
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public void setResults(DAOResults[] results) {
        this.results = results;
    }

    public int getResponse_code() {
        return response_code;
    }

    public DAOResults[] getResults() {
        return results;
    }
    
    
    
    

}
