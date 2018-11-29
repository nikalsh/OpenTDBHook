package server.OpenTriviaDataBaseAPIHook;

/**
 *
 * @author nikalsh
 */
class DAOResults {

    String category;
    String type;
    String difficulty;
    String question;
    String correct_answer;
    String[] incorrect_answers = new String[3];

    public DAOResults() {
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setIncorrect_answers(String[] incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String[] getIncorrect_answers() {
        return incorrect_answers;
    }

    public String getQuestion() {
        return question;
    }

    public String getType() {
        return type;
    }

}
