## Razorapay Test App for Java

Sample app for Razorpay Java Integration

# Steps for Integration:

1. Make a checkout form using our Checkout Integration
2. Accept the `razorpay_payment_id` parameter in the form submission
3. Run the capture code to capture the payment

If you are re-using this as your final code, please make sure you do the following:

- Edit the key inside index.ftl
- Create a file called `config.properties` which should contain the following lines:
```
api_key=your_api_key
api_secret=your_api_secret
```
