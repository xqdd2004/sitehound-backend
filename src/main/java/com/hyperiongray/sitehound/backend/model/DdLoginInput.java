package com.hyperiongray.sitehound.backend.model;

import org.junit.Assert;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by tomas on 22/06/17.
 */
public class DdLoginInput {

    private String id;
    private String workspaceId;
    private String jobId;
    private String url;
    private String domain;
    private Map<String, String> keyValues;
    private String screenshot;
    private List<String> keysOrder;
    private String result;

    public String getId() {
        return id;
    }
    public String getWorkspaceId() {
        return workspaceId;
    }
    public String getJobId() {
        return jobId;
    }
    public String getUrl() {
        return url;
    }
    public String getDomain() {
        return domain;
    }
    public String getScreenshot() {
        return screenshot;
    }
    public Map<String, String> getKeyValues() {
        return keyValues;
    }
    public List<String> getKeysOrder() {
        return keysOrder;
    }
    public String getResult() {
        return result;
    }


    public static class Builder {
        private String id;
        private String workspaceId;
        private String jobId;
        private String url;
        private String domain;
        private Map<String, String> keyValues = new HashMap<>();
        private String screenshot;
        private List<String> keysOrder = new LinkedList<>();
        private String result;

        public Builder withId(String id){
            this.id = id;
            return this;
        }
        public Builder withWorkspaceId(String workspaceId){
            this.workspaceId = workspaceId;
            return this;
        }
        public Builder withJobId(String jobId){
            this.jobId = jobId;
            return this;
        }
        public Builder withUrl(String url){
            this.url = url;
            return this;
        }
        public Builder withDomain(String domain){
            this.domain = domain;
            return this;
        }
        public Builder withKeyValues(Map<String,String> keyValues){
            this.keyValues = keyValues;
            return this;
        }
        public Builder withScreenshot(String screenshot){
            this.screenshot = screenshot;
            return this;
        }
        public Builder withKeysOrder(List<String> keysOrder){
            this.keysOrder = keysOrder;
            return this;
        }
        public Builder withResult(String result){
            this.result = result;
            return this;
        }


        public DdLoginInput build(){
            org.springframework.util.Assert.hasText(this.workspaceId);
            org.springframework.util.Assert.hasText(this.url);
            org.springframework.util.Assert.notEmpty(this.keysOrder);
            for(String key : keysOrder){
                org.springframework.util.Assert.notNull(this.keyValues.get(key));
            }
            org.springframework.util.Assert.isTrue(this.keysOrder.size() == this.keyValues.size());
            return new DdLoginInput(this);
        }
    }

    private DdLoginInput(Builder builder) {
        this.id = builder.id;
        this.workspaceId = builder.workspaceId;
        this.jobId = builder.jobId;
        this.url = builder.url;
        this.domain = builder.domain;
        this.keyValues = builder.keyValues;
        this.screenshot = builder.screenshot;
        this.keysOrder = builder.keysOrder;
        this.result = builder.result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DdLoginInput that = (DdLoginInput) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (workspaceId != null ? !workspaceId.equals(that.workspaceId) : that.workspaceId != null) return false;
        if (jobId != null ? !jobId.equals(that.jobId) : that.jobId != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (domain != null ? !domain.equals(that.domain) : that.domain != null) return false;
        if (keyValues != null ? !keyValues.equals(that.keyValues) : that.keyValues != null) return false;
        if (screenshot != null ? !screenshot.equals(that.screenshot) : that.screenshot != null) return false;
        if (keysOrder != null ? !keysOrder.equals(that.keysOrder) : that.keysOrder != null) return false;
        return result != null ? result.equals(that.result) : that.result == null;
    }

    @Override
    public int hashCode() {
        int result1 = id != null ? id.hashCode() : 0;
        result1 = 31 * result1 + (workspaceId != null ? workspaceId.hashCode() : 0);
        result1 = 31 * result1 + (jobId != null ? jobId.hashCode() : 0);
        result1 = 31 * result1 + (url != null ? url.hashCode() : 0);
        result1 = 31 * result1 + (domain != null ? domain.hashCode() : 0);
        result1 = 31 * result1 + (keyValues != null ? keyValues.hashCode() : 0);
        result1 = 31 * result1 + (screenshot != null ? screenshot.hashCode() : 0);
        result1 = 31 * result1 + (keysOrder != null ? keysOrder.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        String screenshot = null;
        if (this.screenshot !=null){
            screenshot = "" + this.screenshot.length();
        }
        return "DdLoginInput{" +
                "id='" + id + '\'' +
                ", workspaceId='" + workspaceId + '\'' +
                ", jobId='" + jobId + '\'' +
                ", url='" + url + '\'' +
                ", domain='" + domain + '\'' +
                ", keyValues=" + keyValues +
                ", screenshot='" + screenshot + '\'' +
                ", keysOrder=" + keysOrder +
                ", result=" + result +
                '}';
    }
}
