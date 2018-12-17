package src.opentdbhook;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 * @author nikalsh
 */
public class Main {
//   https://opentdb.com/api_config.php     
//      GLOBAL COUNT
//      https://opentdb.com/api_count_global.php    

    private String token = "";
    private FileHandler fh;
    private HttpURLConnection connect;
    private RequestConfig hookConfig;
    private BufferedReader in;
    private URL apiRequest;
    private String currDir = System.getProperty("user.dir");
    private String databaseDir = "";
    private String tokenDir = "";
    private int cofficient;
    private POJOToken pojoToken;
    private POJOQuestion pojoQuestion;
    private int sum;

    public Main(int cofficient) throws ProtocolException, IOException {
        this.cofficient = cofficient;
        this.setDatabaseDirectory("/output/questions/");
        this.setTokenDirectory("/output/token/");

        fh = new FileHandler(tokenDir, databaseDir);

        if (!fh.wasTokenCreatedWithinExpirationDuration()) {
            apiRequest = new URL(API.NEW_TOKEN);
            pojoToken = generateToken(apiRequest);
            fh.writeTokenToFile(pojoToken.token);

        } else {
            hookConfig = new RequestConfig(fh.getTokenFromFile());
            apiRequest = new URL(hookConfig.getQuestionRequest());
            for (int i = 0; i < this.cofficient; i++) {
                pojoQuestion = generateQuestions(apiRequest);

                System.out.println("Response: " + pojoQuestion.response_code);
                if (pojoQuestion.response_code != 4) {
                    sum += hookConfig.getAmount();
                    System.out.println(sum + " questions generated");
                    fh.writeQuestionsToFiles(pojoQuestion);
                } else {
                    System.out.println("Token exhausted! Cannot generate any more non-duplicate questions");
                    break;
                }
            }
        }
    }

    public POJOToken generateToken(URL request) throws IOException {
        sendGET(request);
        in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        String JSON = in.readLine();
        in.close();
        
        POJOToken token = new POJOToken();
        token = new ObjectMapper().readValue(JSON, POJOToken.class);

        return token;
    }

    public POJOQuestion generateQuestions(URL request) throws IOException {
        sendGET(request);
        in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        String JSON = in.readLine();
        System.out.println("recieved JSON!");
        in.close();

        POJOQuestion question = new POJOQuestion();

        question = new ObjectMapper().readValue(JSON, POJOQuestion.class);
        System.out.println("DAO generated from JSON " + question);

        return question;
    }

    public void sendGET(URL url) throws IOException {
        connect = (HttpURLConnection) url.openConnection();
        connect.setRequestMethod("GET");

    }

    public void setDatabaseDirectory(String path) {
        databaseDir = currDir + path;
    }

    public void setTokenDirectory(String path) {
        tokenDir = currDir + path;
    }

    public static void main(String[] args) throws IOException {
        Main hook = new Main(300);

    }

}
