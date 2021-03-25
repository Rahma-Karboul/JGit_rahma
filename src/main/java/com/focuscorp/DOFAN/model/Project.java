package com.focuscorp.DOFAN.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document
public class Project{
    // Attribute
    @Id
    private String projectID;
    private String projectName;
    private String projectDescription;
    private String projectLastUpdateDate;
    private int pipelinePerProjectCount;

    @DBRef
    private List<Pipeline> pipelines;

    //constructor
    public Project() {
    }

    public Project(String projectName, String projectDescription) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
    }

    public Project(String projectID, String projectName, String projectDescription) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
    }

    public Project(String projectID, String projectName, String projectDescription, List<Pipeline> pipelines) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.pipelines = pipelines;
    }

    // have to be implemented : Pipelines' numbers / Status of last pipeline

    // Gette & Satter
    public int getPipelinePerProjectCount() {
        return pipelinePerProjectCount;
    }

    public void setPipelinePerProjectCount(int pipelinePerProjectCount) {
        this.pipelinePerProjectCount = pipelinePerProjectCount;
    }

    public String getProjectLastUpdateDate() {
        return projectLastUpdateDate;
    }

    public void setProjectLastUpdateDate(String projectLastUpdateDate) {
        this.projectLastUpdateDate = projectLastUpdateDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public List<Pipeline> getPipelines() {
        return pipelines;
    }

    public void setPipelines(List<Pipeline> pipelines) {
        this.pipelines = pipelines;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectID='" + projectID + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectLastUpdateDate='" + projectLastUpdateDate + '\'' +
                ", pipelinePerProjectCount=" + pipelinePerProjectCount +
                ", pipelines=" + pipelines +
                '}';
    }
}
