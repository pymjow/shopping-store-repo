package com.accountservice.document.model.entity;

import com.accountservice.document.model.aggregates.Account;
import com.accountservice.document.model.valueobjects.BillSummery;
import com.accountservice.document.model.valueobjects.Purchase;

import javax.persistence.*;

@Entity
public class Bill {

    private Long id;
    private Account account;
    private Purchase purchase;
    private BillSummery billSummery;
    private PaymentType paymentType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Embedded
    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @Embedded
    public BillSummery getBillSummery() {
        return billSummery;
    }

    public void setBillSummery(BillSummery billSummery) {
        this.billSummery = billSummery;
    }

    @ManyToOne
    @JoinColumn(name = "payment_type_id")
    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}
