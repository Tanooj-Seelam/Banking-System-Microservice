package com.banking.account.model;

import java.time.LocalDateTime;

public class Transaction {

    private long id;
    private long sourceAccountId;
    private long targetAccountId;
    private String targetOwnerName;
    private double amount;
    private LocalDateTime initiationDate;
    private LocalDateTime completionDate;
    private String reference;
    private Double latitude;
    private Double longitude;

    public Transaction() {}

    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public long getSourceAccountId() {
        return sourceAccountId;
    }
    
    public void setSourceAccountId(long sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }
    
    public long getTargetAccountId() {
        return targetAccountId;
    }
    
    public void setTargetAccountId(long targetAccountId) {
        this.targetAccountId = targetAccountId;
    }
    
    public String getTargetOwnerName() {
        return targetOwnerName;
    }
    
    public void setTargetOwnerName(String targetOwnerName) {
        this.targetOwnerName = targetOwnerName;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public LocalDateTime getInitiationDate() {
        return initiationDate;
    }
    
    public void setInitiationDate(LocalDateTime initiationDate) {
        this.initiationDate = initiationDate;
    }
    
    public LocalDateTime getCompletionDate() {
        return completionDate;
    }
    
    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }
    
    public String getReference() {
        return reference;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    public Double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public Double getLongitude() {
        return longitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sourceAccountId=" + sourceAccountId +
                ", targetAccountId=" + targetAccountId +
                ", targetOwnerName='" + targetOwnerName + '\'' +
                ", amount=" + amount +
                ", initiationDate=" + initiationDate +
                ", completionDate=" + completionDate +
                ", reference='" + reference + '\'' +
                '}';
    }
} 