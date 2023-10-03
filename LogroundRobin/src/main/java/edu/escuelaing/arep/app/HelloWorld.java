package edu.escuelaing.arep.app;

import static spark.Spark.*;

import java.io.IOException;

public class HelloWorld {

    public static void main(String... args) {
        port(getPort());
        staticFiles.location("/public");
        secure("keystore/ecikeystore.p12", "123456", null, null); 
        get("/hello", (req, res) -> "Hello World"); 
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }
}