package nikalsh.opentdbhook;

/**
 *
 * @author nikalsh
 */
class HookConfig {

    private String amount = "10";
    private String category = API_CONST.CATEGORY_ANY;
    private String difficulty = API_CONST.DIFFICULTY_ANY;
    private String type = API_CONST.TYPE_MULTI;
    private String encoding = API_CONST.ENCODING_RFC_3986;
    private String token = "";

    private String QuestionRequest;

    public HookConfig(String token) {
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
