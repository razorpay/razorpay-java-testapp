<#-- @ftlvariable name="" type="com.razorpay.PaymentView" -->
<html>
<head lang="en">
    <meta charset="utf-8">
</head>
<body>
<form action="/payment/charge" method="POST">
    <script
            src="https://checkout.razorpay.com/v1/checkout.js"
            data-key="rzp_test_1DP5mmOlF5G5ag"
            data-amount=${amount?c}
            data-name="Daft Punk"
            data-description="Purchase Description"
            data-image="vk.jpg"
            data-netbanking="true"
            data-description="Tron Legacy"
            data-prefill.name="Harshil Mathur"
            data-prefill.email="harshil@razorpay.com"
            data-prefill.contact="9999999999"
            data-notes.shopping_order_id="21">
    </script>
    <input type="hidden" name="shopping_order_id" value="21">
</form>
</body>
</html>