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
  public View getPaymentForm() throws RazorpayException {
    JSONObject options = new JSONObject();
    options.put("amount", amount); // Note: The amount should be in paise.
    options.put("currency", "INR");
    options.put("receipt", "txn_123456");
    options.put("payment_capture", 1);
    Order order = client.Orders.create(options);
    return new PaymentView(amount, (String) order.get("id"));
  }

  @POST
  @Path("/charge")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.APPLICATION_JSON)
  public Response charge(MultivaluedMap<String, String> formParams) {
    String paymentId = formParams.getFirst("razorpay_payment_id");
    String razorpaySignature = formParams.getFirst("razorpay_signature");
    String orderId = formParams.getFirst("razorpay_order_id");
    JSONObject options = new JSONObject();

    if (StringUtils.isNotBlank(paymentId) && StringUtils.isNotBlank(razorpaySignature)
        && StringUtils.isNotBlank(orderId)) {
      try {
        options.put("razorpay_payment_id", paymentId);
        options.put("razorpay_order_id", orderId);
        options.put("razorpay_signature", razorpaySignature);
        boolean isEqual = client.Utility.verifyPaymentSignature(options);

        if (isEqual) {
          return Response.ok().build();
        }
      } catch (RazorpayException e) {
        System.out.println("Exception caused because of " + e.getMessage());
        return Response.status(Status.BAD_REQUEST).build();
      }
    }
    return Response.status(Status.BAD_REQUEST).build();
  }
}
