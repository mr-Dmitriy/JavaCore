package lesson07;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ApplicationGlobalState {

    private static ApplicationGlobalState INSTANCE;
    private String selectedCity = null;
    private final String apiKeyForCity = "HdFtWAHAqnCKgYczZGqfKIEjiAl65MhC";

    private ApplicationGlobalState() {
    }

    // Непотокобезопасный код для упрощения
    public static ApplicationGlobalState getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ApplicationGlobalState();
        }
        return INSTANCE;
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }

    public String getApiKeyForCity() {
        return apiKeyForCity;
    }



}
