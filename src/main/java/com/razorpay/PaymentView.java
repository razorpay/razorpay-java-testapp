package com.razorpay;

import io.dropwizard.views.View;

public class PaymentView extends View {

  private int amount;

  public PaymentView(int amount) {
    super("/index.ftl");
    this.amount = amount;
  }

  public int getAmount() {
    return amount;
  }
}
