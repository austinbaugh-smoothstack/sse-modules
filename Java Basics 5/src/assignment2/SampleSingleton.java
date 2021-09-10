package assignment2;

import java.sql.Connection;

public class SampleSingleton {

    private static Connection conn = null;
    private volatile static SampleSingleton instance = null;

    private SampleSingleton() {
    }

    public static SampleSingleton getInstance() {
        if (instance == null) {
            synchronized (SampleSingleton.class) {
                if (instance == null) {
                    instance = new SampleSingleton();
                }
            }
        }
        return instance;
    }
}