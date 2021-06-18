package com.ab.invoicetracker.network.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Arjun Bhatt on 4/21/2021.
 */
public class LoginData extends RealmObject {
    @SerializedName("IsGoogleAuthenticated")
    @Expose
    private Boolean isGoogleAuthenticated;
    @SerializedName("EnableTwofactorAuth")
    @Expose
    private Boolean enableTwofactorAuth;
    @PrimaryKey
    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("DisplayName")
    @Expose
    private String displayName;
    @SerializedName("Alias")
    @Expose
    private String alias;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("SCName")
    @Expose
    private String sCName;
    @SerializedName("AgentNumber")
    @Expose
    private String agentNumber;
    @SerializedName("CallerNumber")
    @Expose
    private String callerNumber;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Role")
    @Expose
    private String role;
    @SerializedName("SCCode")
    @Expose
    private String sCCode;
    @SerializedName("Assigned_Batch")
    @Expose
    private String assignedBatch;
    @SerializedName("IsSelected")
    @Expose
    private Boolean isSelected;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("EnableFreshData")
    @Expose
    private Boolean enableFreshData;

    public Boolean getIsGoogleAuthenticated() {
        return isGoogleAuthenticated;
    }

    public void setIsGoogleAuthenticated(Boolean isGoogleAuthenticated) {
        this.isGoogleAuthenticated = isGoogleAuthenticated;
    }

    public Boolean getEnableTwofactorAuth() {
        return enableTwofactorAuth;
    }

    public void setEnableTwofactorAuth(Boolean enableTwofactorAuth) {
        this.enableTwofactorAuth = enableTwofactorAuth;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Object getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSCName() {
        return sCName;
    }

    public void setSCName(String sCName) {
        this.sCName = sCName;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public String getCallerNumber() {
        return callerNumber;
    }

    public void setCallerNumber(String callerNumber) {
        this.callerNumber = callerNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSCCode() {
        return sCCode;
    }

    public void setSCCode(String sCCode) {
        this.sCCode = sCCode;
    }

    public String getAssignedBatch() {
        return assignedBatch;
    }

    public void setAssignedBatch(String assignedBatch) {
        this.assignedBatch = assignedBatch;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


    public Boolean getEnableFreshData() {
        return enableFreshData;
    }

    public void setEnableFreshData(Boolean enableFreshData) {
        this.enableFreshData = enableFreshData;
    }


}
