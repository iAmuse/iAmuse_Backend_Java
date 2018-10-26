package com.iamuse.admin.VO;

import java.util.List;

public class SubscribedCustomer {
	private Customer customer;
	private Card card;
	private Plan plan;
	private List<PaymentGateways> payment_gateways;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public List<PaymentGateways> getPayment_gateways() {
		return payment_gateways;
	}
	public void setPayment_gateways(List<PaymentGateways> payment_gateways) {
		this.payment_gateways = payment_gateways;
	}
	
	
}
