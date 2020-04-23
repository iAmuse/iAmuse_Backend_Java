package com.iamuse.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TransactionMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "transaction_master", catalog = "iamuse_internal")
public class TransactionMaster implements java.io.Serializable {

	// Fields

	private Integer transactionMasterId;
	private String txnId;
	private String protectionEligibility;
	private String addressStatus;
	private String payerId;
	private String tax;
	private String addressStreet;
	private String paymentDate;
	private String paymentStatus;
	private String addressZip;
	private String firstName;
	private String addressCountryCode;
	private String addressName;
	private String payerStatus;
	private String business;
	private String addressCountry;
	private String addressCity;
	private String quantity;
	private String payerEmail;
	private String paymentType;
	private String addressState;
	private String receiverEmail;
	private String paymentFee;
	private String receiverId;
	private String txnType;
	private String itemName;
	private String mcCurrency;
	private String itemNumber;
	private String residenceCountry;
	private String handlingAmount;
	private String paymentGross;
	private String auth;
	private String statusResponse;
	private String mcFee;
	private String paymentAmount;
	private Integer userId;
	private Boolean status;
	private Boolean isDeleted;
	
	 private String productId;
	 private String originalpurchasedatems;
	 private String purchasedatepst;
	 private String originalpurchasedate;
	 private String bvrs;
	 private String originalpurchasedatepst;
	 private String uniqueidentifier;
	 private String originaltransactionid;
	 private String itemid;
	 private String purchasedatems;
	 private String purchasedate;
	 private String bid;
	 private String uniquevendoridentifier;
	 

	// Constructors

	/** default constructor */
	public TransactionMaster() {
	}
	

	/** full constructor */
	public TransactionMaster(String txnId, String protectionEligibility,
			String addressStatus, String payerId, String tax,
			String addressStreet, String paymentDate, String paymentStatus,
			String addressZip, String firstName, String addressCountryCode,
			String addressName, String payerStatus, String business,
			String addressCountry, String addressCity, String quantity,
			String payerEmail, String paymentType, String addressState,
			String receiverEmail, String paymentFee, String receiverId,
			String txnType, String itemName, String mcCurrency,
			String itemNumber, String residenceCountry, String handlingAmount,
			String paymentGross, String auth, String statusResponse,
			String mcFee, String paymentAmount, Integer userId,Boolean status,Boolean isDeleted) {
		this.txnId = txnId;
		this.protectionEligibility = protectionEligibility;
		this.addressStatus = addressStatus;
		this.payerId = payerId;
		this.tax = tax;
		this.addressStreet = addressStreet;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
		this.addressZip = addressZip;
		this.firstName = firstName;
		this.addressCountryCode = addressCountryCode;
		this.addressName = addressName;
		this.payerStatus = payerStatus;
		this.business = business;
		this.addressCountry = addressCountry;
		this.addressCity = addressCity;
		this.quantity = quantity;
		this.payerEmail = payerEmail;
		this.paymentType = paymentType;
		this.addressState = addressState;
		this.receiverEmail = receiverEmail;
		this.paymentFee = paymentFee;
		this.receiverId = receiverId;
		this.txnType = txnType;
		this.itemName = itemName;
		this.mcCurrency = mcCurrency;
		this.itemNumber = itemNumber;
		this.residenceCountry = residenceCountry;
		this.handlingAmount = handlingAmount;
		this.paymentGross = paymentGross;
		this.auth = auth;
		this.statusResponse = statusResponse;
		this.mcFee = mcFee;
		this.paymentAmount = paymentAmount;
		this.userId = userId;
		this.status = status;
		this.isDeleted = isDeleted;

	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "transaction_master_id", unique = true, nullable = false)
	public Integer getTransactionMasterId() {
		return this.transactionMasterId;
	}

	public void setTransactionMasterId(Integer transactionMasterId) {
		this.transactionMasterId = transactionMasterId;
	}

	@Column(name = "txn_id", length = 500)
	public String getTxnId() {
		return this.txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	@Column(name = "protection_eligibility", length = 500)
	public String getProtectionEligibility() {
		return this.protectionEligibility;
	}

	public void setProtectionEligibility(String protectionEligibility) {
		this.protectionEligibility = protectionEligibility;
	}

	@Column(name = "address_status", length = 500)
	public String getAddressStatus() {
		return this.addressStatus;
	}

	public void setAddressStatus(String addressStatus) {
		this.addressStatus = addressStatus;
	}

	@Column(name = "payer_id", length = 500)
	public String getPayerId() {
		return this.payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	@Column(name = "tax", length = 500)
	public String getTax() {
		return this.tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	@Column(name = "address_street", length = 500)
	public String getAddressStreet() {
		return this.addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	@Column(name = "payment_date", length = 500)
	public String getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Column(name = "payment_status", length = 500)
	public String getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Column(name = "address_zip", length = 500)
	public String getAddressZip() {
		return this.addressZip;
	}

	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}

	@Column(name = "first_name", length = 500)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "address_country_code", length = 500)
	public String getAddressCountryCode() {
		return this.addressCountryCode;
	}

	public void setAddressCountryCode(String addressCountryCode) {
		this.addressCountryCode = addressCountryCode;
	}

	@Column(name = "address_name", length = 500)
	public String getAddressName() {
		return this.addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	@Column(name = "payer_status", length = 500)
	public String getPayerStatus() {
		return this.payerStatus;
	}

	public void setPayerStatus(String payerStatus) {
		this.payerStatus = payerStatus;
	}

	@Column(name = "business", length = 500)
	public String getBusiness() {
		return this.business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	@Column(name = "address_country", length = 500)
	public String getAddressCountry() {
		return this.addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	@Column(name = "address_city", length = 500)
	public String getAddressCity() {
		return this.addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	@Column(name = "quantity", length = 500)
	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Column(name = "payer_email", length = 500)
	public String getPayerEmail() {
		return this.payerEmail;
	}

	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}

	@Column(name = "payment_type", length = 500)
	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Column(name = "address_state", length = 500)
	public String getAddressState() {
		return this.addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	@Column(name = "receiver_email", length = 500)
	public String getReceiverEmail() {
		return this.receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	@Column(name = "payment_fee", length = 500)
	public String getPaymentFee() {
		return this.paymentFee;
	}

	public void setPaymentFee(String paymentFee) {
		this.paymentFee = paymentFee;
	}

	@Column(name = "receiver_id", length = 500)
	public String getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	@Column(name = "txn_type", length = 500)
	public String getTxnType() {
		return this.txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	@Column(name = "item_name", length = 500)
	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "mc_currency", length = 500)
	public String getMcCurrency() {
		return this.mcCurrency;
	}

	public void setMcCurrency(String mcCurrency) {
		this.mcCurrency = mcCurrency;
	}

	@Column(name = "item_number", length = 500)
	public String getItemNumber() {
		return this.itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	@Column(name = "residence_country", length = 500)
	public String getResidenceCountry() {
		return this.residenceCountry;
	}

	public void setResidenceCountry(String residenceCountry) {
		this.residenceCountry = residenceCountry;
	}

	@Column(name = "handling_amount", length = 500)
	public String getHandlingAmount() {
		return this.handlingAmount;
	}

	public void setHandlingAmount(String handlingAmount) {
		this.handlingAmount = handlingAmount;
	}

	@Column(name = "payment_gross", length = 500)
	public String getPaymentGross() {
		return this.paymentGross;
	}

	public void setPaymentGross(String paymentGross) {
		this.paymentGross = paymentGross;
	}

	@Column(name = "auth", length = 500)
	public String getAuth() {
		return this.auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	@Column(name = "status_response", length = 500)
	public String getStatusResponse() {
		return this.statusResponse;
	}

	public void setStatusResponse(String statusResponse) {
		this.statusResponse = statusResponse;
	}

	@Column(name = "mc_fee", length = 45)
	public String getMcFee() {
		return this.mcFee;
	}

	public void setMcFee(String mcFee) {
		this.mcFee = mcFee;
	}

	@Column(name = "payment_amount", length = 45)
	public String getPaymentAmount() {
		return this.paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	@Column(name = "user_id")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}


	@Column(name = "isDeleted")
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="productId")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column(name="originalpurchasedatems")
	public String getOriginalpurchasedatems() {
		return originalpurchasedatems;
	}

	public void setOriginalpurchasedatems(String originalpurchasedatems) {
		this.originalpurchasedatems = originalpurchasedatems;
	}

	@Column(name="purchasedatepst")
	public String getPurchasedatepst() {
		return purchasedatepst;
	}

	public void setPurchasedatepst(String purchasedatepst) {
		this.purchasedatepst = purchasedatepst;
	}

	@Column(name="originalpurchasedate")
	public String getOriginalpurchasedate() {
		return originalpurchasedate;
	}

	public void setOriginalpurchasedate(String originalpurchasedate) {
		this.originalpurchasedate = originalpurchasedate;
	}

	@Column(name="bvrs")
	public String getBvrs() {
		return bvrs;
	}

	public void setBvrs(String bvrs) {
		this.bvrs = bvrs;
	}

	@Column(name="originalpurchasedatepst")
	public String getOriginalpurchasedatepst() {
		return originalpurchasedatepst;
	}

	public void setOriginalpurchasedatepst(String originalpurchasedatepst) {
		this.originalpurchasedatepst = originalpurchasedatepst;
	}

	@Column(name="uniqueidentifier")
	public String getUniqueidentifier() {
		return uniqueidentifier;
	}

	public void setUniqueidentifier(String uniqueidentifier) {
		this.uniqueidentifier = uniqueidentifier;
	}

	@Column(name="originaltransactionid")
	public String getOriginaltransactionid() {
		return originaltransactionid;
	}

	public void setOriginaltransactionid(String originaltransactionid) {
		this.originaltransactionid = originaltransactionid;
	}

	@Column(name="itemid")
	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	@Column(name="purchasedatems")
	public String getPurchasedatems() {
		return purchasedatems;
	}

	public void setPurchasedatems(String purchasedatems) {
		this.purchasedatems = purchasedatems;
	}

	 @Column(name="purchasedate")
	public String getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}
	
	@Column(name="bid")
	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}
	
	 @Column(name="uniquevendoridentifier")
	public String getUniquevendoridentifier() {
		return uniquevendoridentifier;
	}

	public void setUniquevendoridentifier(String uniquevendoridentifier) {
		this.uniquevendoridentifier = uniquevendoridentifier;
	}
}