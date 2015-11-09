package helpers;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;

public class DataProviders {
    private static final String REGISTRATION_DATA_FILE = "./src/main/resources/RegistrationData.csv";

    @DataProvider(name = "registrationData")
    public static Object[][] getRegistrationData() throws IOException {
        return getData(REGISTRATION_DATA_FILE, ",");
    }

    private static Object[][] getData(String path, String divider) throws IOException {
        List<String> data = Helper.readAllLines(path);
        Object[][] dataRows = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            dataRows[i] = data.get(i).split(divider);
        }
        return dataRows;
    }
}
