package jhk.nytimes_mobile;

import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import jhk.nytimes_mobile.Objects.API_Result;
import jhk.nytimes_mobile.Objects.MultiMediaData;
import jhk.nytimes_mobile.Objects.StoryData;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void JSonParsing_Test() throws Exception {
        File file = new File("/Users/RG/Documents/AndroidStudioProjects/ASCAN/NYTimes_Mobile/sampleData.txt");
        if(file.exists() == false)
            Assert.fail();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String  line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String  ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
        } finally {
            reader.close();
        }

        String sampleData = stringBuilder.toString().replace("abstact","abstactString");

        Gson gson = new Gson();
        API_Result result = gson.fromJson(sampleData, API_Result.class);
        for(StoryData sData : result.results){
            //System.out.println(sData.toString());
            for(MultiMediaData mData : sData.multimedia){
                //System.out.println(mData.toString());
                System.out.println(String.format("%s : %d/%d",mData.format,mData.width,mData.height));
                if(mData.height > mData.width)
                    System.out.println("+++++++++++++++++++++++++++++++++");
            }
        }
    }
}