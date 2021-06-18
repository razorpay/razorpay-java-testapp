<#-- @ftlvariable name="" type="com.razorpay.PaymentView" -->
<button id="rzp-button1">Pay with Razorpay</button>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<form action="/payment/charge" method="POST" name="razorpayForm">
    <input id="razorpay_payment_id" type="hidden" name="razorpay_payment_id" />
    <input id="razorpay_order_id" type="hidden" name="razorpay_order_id" />
    <input id="razorpay_signature" type="hidden" name="razorpay_signature" />
</form>
<script>
// Checkout details as a json
var options = {
    "name": "DJ Tiesto",
    "description": "Tron Legacy",
    "image": "https://s29.postimg.org/r6dj1g85z/daft_punk.jpg",
    "prefill": {
      "name": "Daft Punk",
      "email": "customer@merchant.com",
      "contact": "+919999999999",
    },
    "notes": {
      "address": "Hello World",
      "merchant_order_id": "12312321",
    },
    "theme": {
      "color": "#F37254"
    },
    "order_id": `${razorpayOrderId}`,
}

/**
 * The entire list of Checkout fields is available at
 * https://docs.razorpay.com/docs/checkout-form#checkout-fields
 */
// Boolean whether to show image inside a white frame. (default: true)
options.theme.image_padding = false;

options.handler = function(res) {
    document.getElementById('razorpay_payment_id').value = res.razorpay_payment_id;
    document.getElementById('razorpay_order_id').value = `${razorpayOrderId}`;
    document.getElementById('razorpay_signature').value = res.razorpay_signature;
    document.razorpayForm.submit();
}

options.modal = {
    ondismiss: function() {
        console.log("This code runs when the popup is closed");
    },
    // Boolean indicating whether pressing escape key 
    // should close the checkout form. (default: true)
    escape: true,
    // Boolean indicating whether clicking translucent blank
    // space outside checkout form should close the form. (default: false)
    backdropclose: false
};

var rzp = new Razorpay(options);

document.getElementById('rzp-button1').onclick = function(e){
    rzp.open();
    e.preventDefault();
}
</script>
