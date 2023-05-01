// package com.example.pattyulms.controller;

// import com.stripe.Stripe;
// import com.stripe.exception.StripeException;
// import com.stripe.model.checkout.Session;
// import com.stripe.param.checkout.SessionCreateParams;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// @RequestMapping("/create-checkout-session")
// public class CheckoutController {
// static {
// Stripe.apiKey =
// "sk_test_51My8L9CTTGR7rgLfJE3L5i9pNnhLk0WlLDiLHb9W5SkC2dpubuOMIV54oXQhLWXBI6DszHXTyRV6EsehS2uSCUUd00q4dRDnU0";
// }

// @RequestMapping("")
// public String createCheckoutSession() {
// SessionCreateParams params = new SessionCreateParams.Builder()
// .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
// .setMode(SessionCreateParams.Mode.PAYMENT)
// .setSuccessUrl("http://localhost:3000/success")
// .setCancelUrl("http://localhost:3000/cancel")
// .addLineItem(new SessionCreateParams.LineItem.Builder()
// .setPriceData(new SessionCreateParams.LineItem.PriceData.Builder()
// .setCurrency("usd")
// .setUnitAmount(1000L)
// .setProductData(new
// SessionCreateParams.LineItem.PriceData.ProductData.Builder()
// .setName("Example product")
// .build())
// .build())
// .setQuantity(1L)
// .build())
// .build();

// try {
// Session session = Session.create(params);
// return session.getId();
// } catch (StripeException e) {
// e.printStackTrace();
// return null;
// }
// }
// }
