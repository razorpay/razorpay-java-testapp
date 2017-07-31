package com.razorpay;

import org.apache.commons.lang3.StringUtils;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class App extends Application<AppConfiguration> {

  @Override
  public void initialize(Bootstrap<AppConfiguration> bootstrap) {
    super.initialize(bootstrap);
    bootstrap.addBundle(new ViewBundle());
  }

  public void run(AppConfiguration configuration, Environment environment) throws Exception {
    String apiKey = configuration.getApiKey();
    String secretKey = configuration.getSecretKey();

    if (StringUtils.isBlank(apiKey) || StringUtils.isBlank(secretKey)) {
      throw new Exception("Please specify API and Secret Key in configuration file");
    }

    System.out.println("API Key " + apiKey + " Secret Key " + secretKey);

    environment.jersey().register(new PaymentResource(apiKey, secretKey));
  }

  public static void main(String[] args) throws Exception {
    new App().run(args);
  }
}
