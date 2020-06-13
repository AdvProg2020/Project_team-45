package controller;

import com.google.gson.Gson;
import model.Market;
import model.MarketCopier;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class DatabaseController {
    private static DatabaseController instance = new DatabaseController();

    private DatabaseController() {

    }

    public static DatabaseController getInstance() {
        return instance;
    }

    public void readFromDatabase() {
        try (Reader reader = new FileReader("src/main/java/database.txt")) {
            MarketCopier marketCopier = (new Gson()).fromJson(reader, MarketCopier.class);
            if (marketCopier != null) {
                MarketCopier.setInstance(marketCopier);
            }
        } catch (Exception e) {

        }
    }

    public void writeToDatabase() {
        try (Writer writer = new FileWriter("src/main/java/database.txt")) {
            MarketCopier.getInstance().copyMarket();
            (new Gson()).toJson(MarketCopier.getInstance(), writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
