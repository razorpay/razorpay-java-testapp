package com.razorpay;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class App extends Application<Configuration>{
    String apiKey;
    String apiSecret;

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        super.initialize(bootstrap);
        bootstrap.addBundle(new ViewBundle());
    }

    public void run(Configuration configuration, Environment environment) throws Exception {
        setProperties();
        System.out.println(apiKey + apiSecret);
        RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);
        environment.jersey().register(new PaymentResource(razorpayClient));
    }

    private void setProperties() {
        try {
            Properties props = new Properties();
            FileInputStream input = new FileInputStream("config.properties");
            props.load(input);
            apiKey = props.getProperty("api_key");
            apiSecret = props.getProperty("api_secret");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }


}
