package com.razorpay;

import io.dropwizard.views.View;

public class SuccessView extends View {

  private String razorpayPaymentID;
  private String razorpayOrderId;
  private String message;

  public SuccessView(String razorpayPaymentID, String razorpayOrderId, String message) {
    super("/success.ftl");
    this.razorpayPaymentID = razorpayPaymentID;
    this.razorpayOrderId = razorpayOrderId;
    this.message = message;
  }

  public String getRazorpayPaymentID() {
    return razorpayPaymentID;
  }

  public String getMessage() {
    return message;
  }

  public String getRazorpayOrderId() {
    return razorpayOrderId;
  }
}
