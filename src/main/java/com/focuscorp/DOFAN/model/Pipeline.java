package com.focuscorp.DOFAN.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Document(collection = "Pipelines")
public class Pipeline {

    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String project;

    @NotBlank
    @Size(max = 120)
    private String repositoryUrl;

    @NotBlank
    @Size(max = 120)
    private String repositoryCredentials;

    private boolean SAPenvironment;

    private boolean externalArtifact;

    private boolean artifactCnxStatus;

    @Size(max = 120)
    private String artifactUrl;

    @Size(max = 120)
    private String artifactUsername;

    @Size(max = 120)
    private String artifactPassword;

    public Pipeline() {
    }


    public Pipeline(@NotBlank @Size(max = 20) String name) {
        this.name = name;
    }

    /*public Pipeline(@NotBlank @Size(max = 20) String name, @NotBlank @Size(max = 120) String project) {
        this.name = name;
        this.project = project;
    }*/

    public Pipeline(@NotBlank @Size(max = 20) String name, @NotBlank @Size(max = 120) String project, @NotBlank @Size(max = 120) String repoUrl, @NotBlank @Size(max = 120) String repoCredential) {
        this.name = name;
        this.project = project;
        this.repositoryUrl = repoUrl;
        this.repositoryCredentials = repoCredential;
    }

    public Pipeline(@NotBlank @Size(max = 120) String artifactUrl, @NotBlank @Size(max = 120) String artifactUsername, @NotBlank @Size(max = 120) String artifactPassword) {
        this.artifactUrl = artifactUrl;
        this.artifactUsername = artifactUsername;
        this.artifactPassword = artifactPassword;
    }

    @Override
    public String toString() {
        return "Pipeline{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", project='" + project + '\'' +
                ", repository url='" + repositoryUrl + '\'' +
                ", repository Credentials='" + repositoryCredentials + '\'' +
                ", SAP environment='" + SAPenvironment + '\'' +
                ", external Artifact='" + externalArtifact + '\'' +
                ", artifact Url='" + artifactUrl + '\'' +
                ", artifact Username='" + artifactUsername + '\'' +
                ", artifact Password='" + artifactPassword + '\'' +
                '}';
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


    public String getProject() {
        return project;
    }


    public void setProject(String project) {
        this.project = project;
    }


    public String getRepositoryUrl() {
        return repositoryUrl;
    }


    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }


    public String getRepositoryCredentials() {
        return repositoryCredentials;
    }


    public void setRepositoryCredentials(String repositoryCredentials) {
        this.repositoryCredentials = repositoryCredentials;
    }


    public boolean isSAPenvironment() {
        return SAPenvironment;
    }


    public void setSAPenvironment(boolean sAPenvironment) {
        SAPenvironment = sAPenvironment;
    }


    public boolean isExternalArtifact() {
        return externalArtifact;
    }


    public void setExternalArtifact(boolean externalArtifact) {
        this.externalArtifact = externalArtifact;
    }


    public String getArtifactUrl() {
        return artifactUrl;
    }


    public void setArtifactUrl(String artifactUrl) {
        this.artifactUrl = artifactUrl;
    }


    public String getArtifactUsername() {
        return artifactUsername;
    }


    public void setArtifactUsername(String artifactUsername) {
        this.artifactUsername = artifactUsername;
    }


    public String getArtifactPassword() {
        return artifactPassword;
    }


    public void setArtifactPassword(String artifactPassword) {
        this.artifactPassword = artifactPassword;
    }


    public boolean isArtifactCnxStatus() {
        return artifactCnxStatus;
    }

    public void setArtifactCnxStatus(boolean artifactCnxStatus) {
        this.artifactCnxStatus = artifactCnxStatus;
    }
    
}