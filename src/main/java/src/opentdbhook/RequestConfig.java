package src.opentdbhook;

/**
 *
 * @author nikalsh
 */
class RequestConfig {

    private String amount = "10";
    private String category = API.CATEGORY_ANY;
    private String difficulty = API.DIFFICULTY_ANY;
    private String type = API.TYPE_MULTI;
    private String encoding = API.ENCODING_RFC_3986;
    private String token = "";

    private String QuestionRequest;

    public RequestConfig(String token) {
        setToken(token);
    }

    public void updateToken(String token) {
        setToken(token);
    }

    private void setToken(String token) {
        this.token = "&token=" + token;
        updateRequest();
    }

    private void updateRequest() {
        QuestionRequest = String.format("https://opentdb.com/api.php?amount=%s%s%s%s%s%s",
                amount, category, difficulty, type, encoding, token);

    }

    public String getQuestionRequest() {
        return QuestionRequest;
    }

    public int getAmount() {
        return Integer.parseInt(amount);
    }

}
