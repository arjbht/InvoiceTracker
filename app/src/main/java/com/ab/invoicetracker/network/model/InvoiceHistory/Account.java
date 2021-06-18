package com.ab.invoicetracker.network.model.InvoiceHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 6/1/2021.
 */
public class Account {
    @SerializedName("Billing_Tagging__c")
    @Expose
    private String billingTaggingC;
    @SerializedName("HR_ShippingRegion__r")
    @Expose
    private Object hRShippingRegionR;
    @SerializedName("Invoice_Billing_Region__c")
    @Expose
    private String invoiceBillingRegionC;
    @SerializedName("Tag_Account_SC_Codes__c")
    @Expose
    private String tagAccountSCCodesC;
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Customer_id__c")
    @Expose
    private String customerIdC;
    @SerializedName("Customer_Referral_Code__c")
    @Expose
    private Object customerReferralCodeC;
    @SerializedName("BillTo_Customer_ID__c")
    @Expose
    private Object billToCustomerIDC;
    @SerializedName("Bill_To_Customer_Number__c")
    @Expose
    private Object billToCustomerNumberC;
    @SerializedName("Mobile__c")
    @Expose
    private Object mobileC;
    @SerializedName("Alternate_Mobile__c")
    @Expose
    private Object alternateMobileC;
    @SerializedName("Alternate_Phone__c")
    @Expose
    private Object alternatePhoneC;
    @SerializedName("Phone")
    @Expose
    private Object phone;
    @SerializedName("Account_Type__c")
    @Expose
    private Object accountTypeC;
    @SerializedName("Account_Types__c")
    @Expose
    private Object accountTypesC;
    @SerializedName("Email__c")
    @Expose
    private Object emailC;
    @SerializedName("Flat_Number__c")
    @Expose
    private Object flatNumberC;
    @SerializedName("Building_Name__c")
    @Expose
    private Object buildingNameC;
    @SerializedName("Landmark__c")
    @Expose
    private Object landmarkC;
    @SerializedName("Locality_Suburb__c")
    @Expose
    private Object localitySuburbC;
    @SerializedName("BillingStreet")
    @Expose
    private Object billingStreet;
    @SerializedName("BillingPostalCode")
    @Expose
    private Object billingPostalCode;
    @SerializedName("BillingCity")
    @Expose
    private Object billingCity;
    @SerializedName("BillingState")
    @Expose
    private Object billingState;
    @SerializedName("Location__Latitude__s")
    @Expose
    private Object locationLatitudeS;
    @SerializedName("Location__Longitude__s")
    @Expose
    private Object locationLongitudeS;
    @SerializedName("Account_Lat__c")
    @Expose
    private Integer accountLatC;
    @SerializedName("Account_Long__c")
    @Expose
    private Integer accountLongC;
    @SerializedName("IsSelected")
    @Expose
    private Boolean isSelected;
    @SerializedName("Locality_Pincode__r")
    @Expose
    private Object localityPincodeR;
    @SerializedName("Salutation__c")
    @Expose
    private Object salutationC;
    @SerializedName("First_Name__c")
    @Expose
    private Object firstNameC;
    @SerializedName("Last_Name__c")
    @Expose
    private Object lastNameC;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("SR_Outstanding_Amount__c")
    @Expose
    private Object sROutstandingAmountC;
    @SerializedName("Opt_in_for_Call_alerts__c")
    @Expose
    private Boolean optInForCallAlertsC;
    @SerializedName("Opt_in_for_Email_alerts__c")
    @Expose
    private Boolean optInForEmailAlertsC;
    @SerializedName("Opt_in_for_SMS_alerts__c")
    @Expose
    private Boolean optInForSMSAlertsC;
    @SerializedName("Opt_in_for_WhatsApp_alerts__c")
    @Expose
    private Boolean optInForWhatsAppAlertsC;
    @SerializedName("Referral_Amount__c")
    @Expose
    private Object referralAmountC;
    @SerializedName("ShippingRegion__c")
    @Expose
    private Object shippingRegionC;
    @SerializedName("Shipping_State_GSTN__c")
    @Expose
    private Object shippingStateGSTNC;
    @SerializedName("AccountAddress")
    @Expose
    private String accountAddress;

    public String getBillingTaggingC() {
        return billingTaggingC;
    }

    public void setBillingTaggingC(String billingTaggingC) {
        this.billingTaggingC = billingTaggingC;
    }

    public Object getHRShippingRegionR() {
        return hRShippingRegionR;
    }

    public void setHRShippingRegionR(Object hRShippingRegionR) {
        this.hRShippingRegionR = hRShippingRegionR;
    }

    public String getInvoiceBillingRegionC() {
        return invoiceBillingRegionC;
    }

    public void setInvoiceBillingRegionC(String invoiceBillingRegionC) {
        this.invoiceBillingRegionC = invoiceBillingRegionC;
    }

    public String getTagAccountSCCodesC() {
        return tagAccountSCCodesC;
    }

    public void setTagAccountSCCodesC(String tagAccountSCCodesC) {
        this.tagAccountSCCodesC = tagAccountSCCodesC;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerIdC() {
        return customerIdC;
    }

    public void setCustomerIdC(String customerIdC) {
        this.customerIdC = customerIdC;
    }

    public Object getCustomerReferralCodeC() {
        return customerReferralCodeC;
    }

    public void setCustomerReferralCodeC(Object customerReferralCodeC) {
        this.customerReferralCodeC = customerReferralCodeC;
    }

    public Object getBillToCustomerIDC() {
        return billToCustomerIDC;
    }

    public void setBillToCustomerIDC(Object billToCustomerIDC) {
        this.billToCustomerIDC = billToCustomerIDC;
    }

    public Object getBillToCustomerNumberC() {
        return billToCustomerNumberC;
    }

    public void setBillToCustomerNumberC(Object billToCustomerNumberC) {
        this.billToCustomerNumberC = billToCustomerNumberC;
    }

    public Object getMobileC() {
        return mobileC;
    }

    public void setMobileC(Object mobileC) {
        this.mobileC = mobileC;
    }

    public Object getAlternateMobileC() {
        return alternateMobileC;
    }

    public void setAlternateMobileC(Object alternateMobileC) {
        this.alternateMobileC = alternateMobileC;
    }

    public Object getAlternatePhoneC() {
        return alternatePhoneC;
    }

    public void setAlternatePhoneC(Object alternatePhoneC) {
        this.alternatePhoneC = alternatePhoneC;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getAccountTypeC() {
        return accountTypeC;
    }

    public void setAccountTypeC(Object accountTypeC) {
        this.accountTypeC = accountTypeC;
    }

    public Object getAccountTypesC() {
        return accountTypesC;
    }

    public void setAccountTypesC(Object accountTypesC) {
        this.accountTypesC = accountTypesC;
    }

    public Object getEmailC() {
        return emailC;
    }

    public void setEmailC(Object emailC) {
        this.emailC = emailC;
    }

    public Object getFlatNumberC() {
        return flatNumberC;
    }

    public void setFlatNumberC(Object flatNumberC) {
        this.flatNumberC = flatNumberC;
    }

    public Object getBuildingNameC() {
        return buildingNameC;
    }

    public void setBuildingNameC(Object buildingNameC) {
        this.buildingNameC = buildingNameC;
    }

    public Object getLandmarkC() {
        return landmarkC;
    }

    public void setLandmarkC(Object landmarkC) {
        this.landmarkC = landmarkC;
    }

    public Object getLocalitySuburbC() {
        return localitySuburbC;
    }

    public void setLocalitySuburbC(Object localitySuburbC) {
        this.localitySuburbC = localitySuburbC;
    }

    public Object getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(Object billingStreet) {
        this.billingStreet = billingStreet;
    }

    public Object getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(Object billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public Object getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(Object billingCity) {
        this.billingCity = billingCity;
    }

    public Object getBillingState() {
        return billingState;
    }

    public void setBillingState(Object billingState) {
        this.billingState = billingState;
    }

    public Object getLocationLatitudeS() {
        return locationLatitudeS;
    }

    public void setLocationLatitudeS(Object locationLatitudeS) {
        this.locationLatitudeS = locationLatitudeS;
    }

    public Object getLocationLongitudeS() {
        return locationLongitudeS;
    }

    public void setLocationLongitudeS(Object locationLongitudeS) {
        this.locationLongitudeS = locationLongitudeS;
    }

    public Integer getAccountLatC() {
        return accountLatC;
    }

    public void setAccountLatC(Integer accountLatC) {
        this.accountLatC = accountLatC;
    }

    public Integer getAccountLongC() {
        return accountLongC;
    }

    public void setAccountLongC(Integer accountLongC) {
        this.accountLongC = accountLongC;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Object getLocalityPincodeR() {
        return localityPincodeR;
    }

    public void setLocalityPincodeR(Object localityPincodeR) {
        this.localityPincodeR = localityPincodeR;
    }

    public Object getSalutationC() {
        return salutationC;
    }

    public void setSalutationC(Object salutationC) {
        this.salutationC = salutationC;
    }

    public Object getFirstNameC() {
        return firstNameC;
    }

    public void setFirstNameC(Object firstNameC) {
        this.firstNameC = firstNameC;
    }

    public Object getLastNameC() {
        return lastNameC;
    }

    public void setLastNameC(Object lastNameC) {
        this.lastNameC = lastNameC;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Object getSROutstandingAmountC() {
        return sROutstandingAmountC;
    }

    public void setSROutstandingAmountC(Object sROutstandingAmountC) {
        this.sROutstandingAmountC = sROutstandingAmountC;
    }

    public Boolean getOptInForCallAlertsC() {
        return optInForCallAlertsC;
    }

    public void setOptInForCallAlertsC(Boolean optInForCallAlertsC) {
        this.optInForCallAlertsC = optInForCallAlertsC;
    }

    public Boolean getOptInForEmailAlertsC() {
        return optInForEmailAlertsC;
    }

    public void setOptInForEmailAlertsC(Boolean optInForEmailAlertsC) {
        this.optInForEmailAlertsC = optInForEmailAlertsC;
    }

    public Boolean getOptInForSMSAlertsC() {
        return optInForSMSAlertsC;
    }

    public void setOptInForSMSAlertsC(Boolean optInForSMSAlertsC) {
        this.optInForSMSAlertsC = optInForSMSAlertsC;
    }

    public Boolean getOptInForWhatsAppAlertsC() {
        return optInForWhatsAppAlertsC;
    }

    public void setOptInForWhatsAppAlertsC(Boolean optInForWhatsAppAlertsC) {
        this.optInForWhatsAppAlertsC = optInForWhatsAppAlertsC;
    }

    public Object getReferralAmountC() {
        return referralAmountC;
    }

    public void setReferralAmountC(Object referralAmountC) {
        this.referralAmountC = referralAmountC;
    }

    public Object getShippingRegionC() {
        return shippingRegionC;
    }

    public void setShippingRegionC(Object shippingRegionC) {
        this.shippingRegionC = shippingRegionC;
    }

    public Object getShippingStateGSTNC() {
        return shippingStateGSTNC;
    }

    public void setShippingStateGSTNC(Object shippingStateGSTNC) {
        this.shippingStateGSTNC = shippingStateGSTNC;
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }
}
