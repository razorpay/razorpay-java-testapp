package com.razorpay;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import io.dropwizard.views.View;

@Path("/payment")
public class PaymentResource {

  private RazorpayClient client;
  private int amount = 500;

  public PaymentResource(RazorpayClient client) {
    this.client = client;
  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  public View getPaymentForm() {
    return new PaymentView(amount);
  }

  @POST
  @Path("/charge")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response charge(MultivaluedMap<String, String> formParams) {
    String paymentId = formParams.getFirst("razorpay_payment_id");
    JSONObject options = new JSONObject();
    options.put("amount", amount);

    if (StringUtils.isNotBlank(paymentId)) {
      try {
        Payment payment = client.Payments.capture(paymentId, options);
        return Response.ok(payment.toString()).build();
      } catch (RazorpayException e) {
        System.out.println("Exception caused because of " + e.getMessage());
        return Response.status(Status.BAD_REQUEST).build();
      }
    }
    return Response.status(Status.BAD_REQUEST).build();
  }
}
