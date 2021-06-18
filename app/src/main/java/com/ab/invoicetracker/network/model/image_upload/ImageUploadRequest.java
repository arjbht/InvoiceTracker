package com.ab.invoicetracker.network.model.image_upload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arjun Bhatt on 5/1/2021.
 */
public class ImageUploadRequest {
    @SerializedName("ActivityId")
    @Expose
    private String activityId;
    @SerializedName("FileName")
    @Expose
    private String fileName;
    @SerializedName("FileContent")
    @Expose
    private String fileContent;
    @SerializedName("FileUrl")
    @Expose
    private String fileUrl;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

}
