<#-- @ftlvariable name="" type="com.razorpay.SuccesstView" -->
<#if message != "">
  <h3>
    <div>Your payment failed</div>
    <div>${message}</div>
  </h3>
<#else>
  <h3>
    <div>Your payment for order_id: ${razorpayOrderId} was successful</div>
    <div> paymentId: ${razorpayPaymentID}</div>
  </h3>
</#if>