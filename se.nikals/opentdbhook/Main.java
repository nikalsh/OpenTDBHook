package se.nikals.opentdbhook;

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
    private DatabaseIO databaseIO;
    private HttpURLConnection connect;
    private HookConfig hookConfig;
    private BufferedReader in;
    private URL apiRequest;
    private String currDir = System.getProperty("user.dir");
    private String databaseDir = "";
    private String tokenDir = "";
    private int amountOfQuestionsPerAPICall;
    private DAOToken daoToken;
    private DAOQuestions daoQuestions;
    private int sum;

    public Main(int amountOfQuestionsPerAPICall) throws ProtocolException, IOException {
        int roundAmountOfQuestionsPerAPICallToNearestTen = (int) ((Math.round(amountOfQuestionsPerAPICall / 10.0) * 10));
        this.amountOfQuestionsPerAPICall = (roundAmountOfQuestionsPerAPICallToNearestTen > 50 ? 50 : roundAmountOfQuestionsPerAPICallToNearestTen);

        this.setDatabaseDirectory("/Questions/");
        this.setTokenDirectory("/opentriviadatabaseapihook/");

        databaseIO = new DatabaseIO(tokenDir, databaseDir);

        if (!databaseIO.wasTokenCreatedWithinExpirationDuration()) {
            apiRequest = new URL(ApiConstants.NEW_TOKEN);
            daoToken = generateToken(apiRequest);
            databaseIO.writeTokenToFile(daoToken.token);

        } else {
            hookConfig = new HookConfig(databaseIO.getTokenFromFile());
            apiRequest = new URL(hookConfig.getQuestionRequest());
            for (int i = 0; i < this.amountOfQuestionsPerAPICall; i++) {
                daoQuestions = generateQuestions(apiRequest);

                System.out.println("Response: " + daoQuestions.response_code);
                if (daoQuestions.response_code != 4) {
                    sum += hookConfig.getAmount();
                    System.out.println(sum + " questions generated");
                    databaseIO.writeQuestionsToFiles(daoQuestions);
                } else {
                    System.out.println("Token exhausted! Cannot generate any more non-duplicate questions");
                    break;
                }
            }
        }
    }

    public DAOToken generateToken(URL request) throws IOException {
        sendGET(request);
        in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        String JSON = in.readLine();
        in.close();

        DAOToken daoToken = new DAOToken();
        daoToken = new ObjectMapper().readValue(JSON, DAOToken.class);

        return daoToken;
    }

    public DAOQuestions generateQuestions(URL request) throws IOException {
        sendGET(request);
        in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
        String JSON = in.readLine();
        System.out.println("recieved JSON!");
        in.close();

        DAOQuestions daoQuestions = new DAOQuestions();

        daoQuestions = new ObjectMapper().readValue(JSON, DAOQuestions.class);
        System.out.println("DAO generated from JSON " + daoQuestions);

        return daoQuestions;
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
//        Main hook = new Main(10);
//        hook.setDatabaseDirectory(path);
//        hook.setTokenDirectory(path);

        for (String arg : args) {
            System.out.println(arg);
        }
//
//for (int i = 0; i <= 60; i++) {
//            
//            
//            int stuff = (int) ((Math.round(i / 10.0) * 10));
//            int amountOfQuestionsPerAPICall = (stuff > 50 ? 50 : (stuff < 10 ? 10 : stuff));
//            System.out.println(i + " = " + amountOfQuestionsPerAPICall);
//        }
//
//    }
    }
}
