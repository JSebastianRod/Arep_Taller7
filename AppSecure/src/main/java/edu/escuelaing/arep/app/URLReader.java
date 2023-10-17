package edu.escuelaing.arep.app;

import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class URLReader {

    public static String searchuri(String url, String myTrustStore) {
        try {
            // Create a file and a password representation
            File trustStoreFile = new File(myTrustStore);
            char[] trustStorePassword = "123456".toCharArray();

            // Load the trust store, the default type is "pkcs12", the alternative is "jks"
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);

            // Get the singleton instance of the TrustManagerFactory
            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());

            // Itit the TrustManagerFactory using the truststore object
            tmf.init(trustStore);

            //Set the default global SSLContext so all the connections will use it
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);

            // We can now read this URL
            readURL("https://localhost:5000/hello");

            // This one can't be read because the Java default truststore has been
            // changed.
            readURL("https://www.google.com");

        } catch (KeyStoreException | NoSuchAlgorithmException | IOException | CertificateException |
                 KeyManagementException ex) {
            Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return readURL(url);
    }

    public static String readURL(String sitetoread) {
        StringBuilder res = new StringBuilder();
        try {
            // Crea el objeto que representa una URL2
            URL siteURL = new URL(sitetoread);
            // Crea el objeto que URLConnection
            URLConnection urlConnection = siteURL.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                res.append(inputLine);
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return res.toString();
    }
}