package com.razorpay;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import io.dropwizard.views.View;
import org.json.JSONObject;



@Path("/payment")
public class PaymentResource {


    private RazorpayClient client;
    int amount = 500;

    public PaymentResource(RazorpayClient client){
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
    @Produces("text/plain")
    public String charge(MultivaluedMap<String, String> formParams){
        String paymentId = formParams.getFirst("razorpay_payment_id");
        JSONObject options = new JSONObject();
        options.put("amount", amount);
        if(paymentId != null) {
            try {
                Payment payment = client.Payments.capture(paymentId, options);
                return payment.toString();
            } catch (RazorpayException e) {
                return e.toString();
            }
        }
        return "";
    }

}
