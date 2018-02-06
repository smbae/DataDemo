import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

public class MainTest {

    @Test
    public void TestRead() throws Exception{

        String directory = "/Users/matthew/Desktop/JAVA/mainproject/test1.json";
        JSONObject PII = Main.read(1121345, directory);

        String expected = "2017-08-21";
        String result = (String) PII.get("event_date");

        Assert.assertEquals(expected, result);
    }

    @Test
    public void TestMissing() throws Exception{

        String directory = "/Users/matthew/Desktop/JAVA/mainproject/test1.json";
        JSONObject PII = Main.read(1454581, directory);

        long expected = 1454581;
        long result = (long) PII.get("account_id");

        Assert.assertEquals(expected, result);

    }

    @Test
    public void ValidateJSON() throws Exception{

        String directory = "/Users/matthew/Desktop/JAVA/mainproject/test3.json";
        JSONObject PII = Main.read(1454581, directory);

        BufferedReader br = null;
        String currentLine;
        String result = null;
        int Counter = 0;

        br = new BufferedReader(new FileReader(directory));

        while((currentLine = br.readLine()) != null){
            Counter++;
            if(Main.isJSONValid(currentLine) == false){
                result = "not Json! in line "+ Counter;}
        }
        String expected = "not Json! in line 6";

        Assert.assertEquals(expected, result);
    }


}
