package pl.sakfa.budget_tracker.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import org.sql2o.data.Row;
import org.sql2o.data.Table;
import pl.sakfa.budget_tracker.currencies.ecb.CurrencyProviderECB;

import javax.sql.DataSource;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@ComponentScan({ "pl.sakfa.budget_tracker" })
public class BudgetTrackerApp {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws FileNotFoundException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BudgetTrackerApp.class);
        ctx.registerShutdownHook();

        Sql2o sql2o = ctx.getBean(Sql2o.class);
        try (Connection con = sql2o.open()) {
            Query q = con.createQuery("SELECT * FROM currencies");
            Table t = q.executeAndFetchTable();
            System.out.println(t.rows().stream().map(Row::asMap).map(map->(String) map.get("iso_code")).collect(Collectors.joining(", ")));
        }
//        CurrencyProviderECB currencyProviderECB = ctx.getBean(CurrencyProviderECB.class);
//        currencyProviderECB.getLatestFeed();

    }
}
