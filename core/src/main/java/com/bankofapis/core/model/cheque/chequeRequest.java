package com.bankofapis.core.model.cheque;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class chequeRequest {

	private String payeeName;
	private String payeeAcNum;
	private String payeeSortCode;
	private String beneName;
	private String brand;
	private String beneAcNum;
	private boolean acPayOnly;
	private String beneSortCode;
	private Float chequeAmount;
	private String chequeAmountText;
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", shape = JsonFormat.Shape.STRING)
//	LocalDateTime  chequeDate;
	UUID qrChequeNum=UUID.randomUUID();
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getPayeeAcNum() {
		return payeeAcNum;
	}
	public void setPayeeAcNum(String payeeAcNum) {
		this.payeeAcNum = payeeAcNum;
	}
	public String getPayeeSortCode() {
		return payeeSortCode;
	}
	public void setPayeeSortCode(String payeeSortCode) {
		this.payeeSortCode = payeeSortCode;
	}
	public String getBeneName() {
		return beneName;
	}
	public void setBeneName(String beneName) {
		this.beneName = beneName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBeneAcNum() {
		return beneAcNum;
	}
	public void setBeneAcNum(String beneAcNum) {
		this.beneAcNum = beneAcNum;
	}
	public boolean isAcPayOnly() {
		return acPayOnly;
	}
	public void setAcPayOnly(boolean acPayOnly) {
		this.acPayOnly = acPayOnly;
	}
	public String getBeneSortCode() {
		return beneSortCode;
	}
	public void setBeneSortCode(String beneSortCode) {
		this.beneSortCode = beneSortCode;
	}
	public Float getChequeAmount() {
		return chequeAmount;
	}
	public void setChequeAmount(Float chequeAmount) {
		this.chequeAmount = chequeAmount;
	}
	
	

    public String getChequeAmountText() {
		return chequeAmountText;
	}
	public void setChequeAmountText(String chequeAmountText) {
		this.chequeAmountText = chequeAmountText;
	}
	//	public LocalDateTime  getChequeDate() {
//		return chequeDate;
//	}
//	public void setChequeDate(LocalDateTime  chequeDate) {
//		this.chequeDate = chequeDate;
//	}
	public UUID getQrChequeNum() {
		return qrChequeNum;
	}
	public void setQrChequeNum(UUID qrChequeNum) {
		this.qrChequeNum = qrChequeNum;
	}
	@Override
	public String toString() {
		return "chequeRequest [payeeName=" + payeeName + ", payeeAcNum=" + payeeAcNum + ", payeeSortCode="
				+ payeeSortCode + ", beneName=" + beneName + ", brand=" + brand + ", beneAcNum=" + beneAcNum
				+ ", acPayOnly=" + acPayOnly + ", beneSortCode=" + beneSortCode + ", chequeAmount=" + chequeAmount
				+ ", chequeAmountText=" + chequeAmountText + ", qrChequeNum=" + qrChequeNum + "]";
	}
	

	
	
	
	
	
}
