package com.lld.payment.external;

public class RazorpayClient {

    // Razorpay's own method - completely differnt from our interface
    public String initiateTransaction(double amount, String currency, String receiptId) {
        System.out.println("[RAZORPAY SDK] Initiating transaction: ₹" + amount + " | Receipt: " + receiptId);
        return "rzp_txn_" + System.currentTimeMillis();
    }

    public String getTransactionStatus(String transactionId) {
        System.out.println("[RAZORPAY SDK] Fetching status for: " + transactionId);
        return "SUCCESS";
    }

    public String initiateRefund(String transactionId, double amount) {
        System.out.println("[RAZORPAY SDK] Initiating refund of ₹"
                + amount + " for txn: " + transactionId);
        return "rzp_refund_" + System.currentTimeMillis();
    }
}
