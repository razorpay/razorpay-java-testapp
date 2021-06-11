package com.razorpay;

import io.dropwizard.views.View;

public class SuccessView extends View {

  private String razorpayPaymentID;
  private String razorpayOrderId;

  public SuccessView(String razorpayPaymentID, String razorpayOrderId) {
    super("/success.ftl");
    this.razorpayPaymentID = razorpayPaymentID;
    this.razorpayOrderId = razorpayOrderId;
  }

  public int getRazorpayPaymentID() {
    return razorpayPaymentID;
  }

  public String getRazorpayOrderId() {
    return razorpayOrderId;
  }
}
