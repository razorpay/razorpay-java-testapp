## Razorapay Test App for Java

Sample app for Razorpay Java Integration

The sample app is made using [Dropwizard Framework](http://www.dropwizard.io/1.0.2/docs/).

# Steps to run sample app:

- Edit the key inside index.ftl
- Add you api_key and api_secret in server.yml file:
```
apiKey: your_api_key
secretKey: your_api_secret
```
- Build 
```
mvn clean install
```
- Run 
```
java -jar target/razorpay-java-testapp-1.0-SNAPSHOT.jar server server.yml
```

# Steps for Integration:

1. Make a checkout form using our Checkout Integration
2. Accept the `razorpay_payment_id` parameter in the form submission
3. Run the capture code to capture the payment
