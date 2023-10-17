package edu.escuelaing.arep.app;

import static spark.Spark.*;

import java.io.IOException;

public class HelloWorld {

    public static void main(String... args) {
        port(getPort());
        staticFiles.location("/public");
        secure(getKeStore(), getKeStorePassword(), null, null); 
        get("/hello", (req, res) -> "Hello World this is 5001");
        get("/remote", (req, res) -> {
            return URLReader.searchuri("https://localhost:5000/hello", "keystore/ecikeystore2.p12");
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5001;
    }

    private static String getKeStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "keystore/ecikeystore.p12";
    }

    private static String getKeStorePassword() {
        if (System.getenv("KEYSTORE_PASSWORD") != null) {
            return System.getenv("KEYSTORE_PASSWORD");
        }
        return "123456";
    }

}