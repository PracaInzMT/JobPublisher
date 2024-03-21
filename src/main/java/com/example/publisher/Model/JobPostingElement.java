package com.example.publisher.Model;
import java.util.List;
public class JobPostingElement {

    private List<Element> elements;

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public static class Element {
        private String listingType;
        private String integrationContext;
        private String companyApplyUrl;
        private String description;
        private String employmentStatus;
        private String externalJobPostingId;
        private long listedAt;
        private String jobPostingOperationType;
        private String title;
        private String location;
        private List<String> workplaceTypes;

        public String getListingType() {
            return listingType;
        }

        public void setListingType(String listingType) {
            this.listingType = listingType;
        }

        public String getIntegrationContext() {
            return integrationContext;
        }

        public void setIntegrationContext(String integrationContext) {
            this.integrationContext = integrationContext;
        }

        public String getCompanyApplyUrl() {
            return companyApplyUrl;
        }

        public void setCompanyApplyUrl(String companyApplyUrl) {
            this.companyApplyUrl = companyApplyUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEmploymentStatus() {
            return employmentStatus;
        }

        public void setEmploymentStatus(String employmentStatus) {
            this.employmentStatus = employmentStatus;
        }

        public String getExternalJobPostingId() {
            return externalJobPostingId;
        }

        public void setExternalJobPostingId(String externalJobPostingId) {
            this.externalJobPostingId = externalJobPostingId;
        }

        public long getListedAt() {
            return listedAt;
        }

        public void setListedAt(long listedAt) {
            this.listedAt = listedAt;
        }

        public String getJobPostingOperationType() {
            return jobPostingOperationType;
        }

        public void setJobPostingOperationType(String jobPostingOperationType) {
            this.jobPostingOperationType = jobPostingOperationType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public List<String> getWorkplaceTypes() {
            return workplaceTypes;
        }

        public void setWorkplaceTypes(List<String> workplaceTypes) {
            this.workplaceTypes = workplaceTypes;
        }
    }
}
