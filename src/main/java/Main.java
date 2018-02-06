import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
Name: Matthew Bae
Phone: 661 - 593 - 8221
Email: sungmatthewbae@gmail.com
Language: Java 8
*/

public class Main {

    public static void main(String[] args) throws Exception {

        /*
        takes account_id as a input, and outputs the most recent PII
        Input -> account_id (long), and file directory
        output -> the most recent PII (JSONObject)
         */

        String directory = "/Users/matthew/Desktop/JAVA/mainproject/test1.json";
        long uniqueId = 1121345;
        read(uniqueId, directory);
    }

    private static final Gson gson = new Gson();

    public static JSONObject read(long uniqueId, String directory) throws Exception {

        BufferedReader br = null;
        String currentLine;
        JSONParser parser = new JSONParser();
        JSONObject userinfo = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {

            br = new BufferedReader(new FileReader(directory));
            Date DateSave = null;

            while ((currentLine = br.readLine()) != null) {

                // check if JSON is valid
                if(isJSONValid(currentLine) == false)
                    continue;

                JSONObject jsonObject = (JSONObject) parser.parse(currentLine);

                //parse data into data simple data format yyyy-MM-dd
                Date DateTemp = sdf.parse((String) jsonObject.get("event_date"));
                //get unique account id
                long id = (long) jsonObject.get("account_id");

                //check account id within JSON file and outputs the most recent PII for unique account id
                if (uniqueId == id && DateSave != null) {
                    if (DateSave.compareTo(DateTemp) < 0) {
                        DateSave = DateTemp;
                        userinfo = jsonObject;
                    } }

                if (uniqueId == id && DateSave == null) {
                    DateSave = DateTemp;
                    userinfo = jsonObject;
                } }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return userinfo;
    }

    //check whether Json String is in valid syntax, outputs false if it is not.
    public static boolean isJSONValid(String jsonInString) {
        try {
            gson.fromJson(jsonInString, Object.class);
            return true;
        } catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }

}
