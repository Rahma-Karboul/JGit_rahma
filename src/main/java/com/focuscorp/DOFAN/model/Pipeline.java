package com.focuscorp.DOFAN.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;


@Document(collection = "Pipelines")
public class Pipeline {

    @Id
    private String id;

    private String name;

    @DBRef
    private Project project;

    private String repositoryUrl;

    @DBRef
    private Credential repositoryCredentials;

    private boolean isSAPenvironment;

    private boolean isExternalArtifact;

    private boolean artifactCnxStatus;

    private String artifactUrl;

    private String artifactUsername;

    private String artifactPassword;

    @DBRef
    private Credential artifactCredentials;

    public Pipeline() {
    }


    public Pipeline(String name) {
        this.name = name;
    }
    public Pipeline(String name, String artifactUrl) {
        this.name = name;
        this.artifactUrl = artifactUrl;
    }
    /*public Pipeline(@NotBlank @Size(max = 20) String name, @NotBlank @Size(max = 120) String project) {
        this.name = name;
        this.project = project;
    }*/

    public Pipeline(String name, Project project, String repoUrl,Credential repoCredential) {
        this.name = name;
        this.project = project;
        this.repositoryUrl = repoUrl;
        this.repositoryCredentials = repoCredential;
    }

    public Pipeline( String artifactUrl, String artifactUsername,String artifactPassword) {
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
                ", SAP environment='" + isSAPenvironment + '\'' +
                ", external Artifact='" + isExternalArtifact + '\'' +
                ", artifact Url='" + artifactUrl + '\'' +
                ", artifact Username='" + artifactUsername + '\'' +
                ", artifact Password='" + artifactPassword + '\'' +
                ", artifact Credentials='" + artifactCredentials + '\'' +
                '}';
    }

    //////////////////getters & setters ////////////////////////////////////////////
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


    public Project getProject() {
        return project;
    }


    public void setProject(Project project) {
        this.project = project;
    }


    public String getRepositoryUrl() {
        return repositoryUrl;
    }


    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }


    public Credential getRepositoryCredentials() {
        return repositoryCredentials;
    }


    public void setRepositoryCredentials(Credential repositoryCredentials) {
        this.repositoryCredentials = repositoryCredentials;
    }


    public boolean isSAPenvironment() {
        return isSAPenvironment;
    }


    public void setSAPenvironment(boolean sAPenvironment) {
        isSAPenvironment = sAPenvironment;
    }


    public boolean isExternalArtifact() {
        return isExternalArtifact;
    }


    public void setExternalArtifact(boolean externalArtifact) {
        this.isExternalArtifact = externalArtifact;
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

    public Credential getArtifactCredentials() {
        return artifactCredentials;
    }

    public void setArtifactCredentials(Credential artifactCredentials) {
        this.artifactCredentials = artifactCredentials;
    }
}