package edu.escuelaing.arep.app;

import static spark.Spark.*;

import java.io.IOException;

public class HelloWorld1 {

    public static void main(String... args) {
        port(getPort());
        staticFiles.location("/public");
        secure(getKeStore(), getKeStorePassword(), null, null);
        get("/hello", (req, res) -> "Hello World this is 5000"); 
        get("/remote", (req, res) -> {
            return URLReader.searchuri(getUri(), "keystore/ecikeystore.p12");
        }); 
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }

    private static String getKeStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "keystore/ecikeystore2.p12";
    }

    private static String getKeStorePassword() {
        if (System.getenv("KEYSTORE_PASSWORD") != null) {
            return System.getenv("KEYSTORE_PASSWORD");
        }
        return "123456";
    }

    private static String getUri() {
        if (System.getenv("URI") != null) {
            return System.getenv("URI");
        }
        return "https://localhost:5051/hello";
    }
}