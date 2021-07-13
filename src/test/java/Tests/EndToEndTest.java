package Tests;

import Pages.MyAccountLogin;
import Setup.TemplateSetup;
import Utils.DataPOJO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class EndToEndTest extends TemplateSetup {
    @DataProvider
    public Object[][] userInfo() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/data.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("users");
        List<DataPOJO> testData = new Gson().fromJson(dataSet, new TypeToken<List<DataPOJO>>() {}.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }

    @Test(dataProvider = "userInfo")
    public void test(DataPOJO userInfo) {
        MyAccountLogin objLogin = new MyAccountLogin(driver);

        objLogin.example_page_factory(userInfo.getPassword());
    }

}
