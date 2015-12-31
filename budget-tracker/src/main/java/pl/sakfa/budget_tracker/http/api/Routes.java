package pl.sakfa.budget_tracker.http.api;

import static spark.Spark.*;

public class Routes {
    static {
        get("/account/list", (req, res) -> {
            return "Hello World";
        });
    }
}
