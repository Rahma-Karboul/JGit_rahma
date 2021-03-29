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
    private String SAPplateform;
    ////////////////////////// Build attributes ///////////
    private boolean isBuildTool;
    ////////////////////////// LocalTests attributes ///////
    private boolean isBackendIntegrationTests;
    private boolean isBackendUnitTests;
    private boolean isFrontendIntegrationTests;
    private boolean isFrontendUnitTests;
    private boolean isLint;
    private boolean isNPMdependencyAudit;
    private boolean isStaticCodeCheck;
    ////////////////////////// RemoteTests attributes ///////
    private boolean isEndToEndTests;
    private boolean isPerformanceTests;
    ////////////////////////// Quality checks attributes ///////
    private boolean isCodeCoverage;
    ////////////////////////// Third Party Checks attributes ///////
    private boolean isAdditionalTools;
    private boolean isCheckmarxScan;
    private boolean isFortifyScan;
    private boolean isSourceClearScan;
    private boolean isWhiteSourceScan;

    private boolean isExternalArtifact;
    private String artifactUrl;
    @DBRef
    private Credential artifactCredentials;
    private String deploymentServer;

    //////////////////////////////// Constructors //////////////////////////////////////////////////////////////////
    public Pipeline() { }
    public Pipeline(String name) {
        this.name = name;
    }
    public Pipeline(String name, String artifactUrl) {
        this.name = name;
        this.artifactUrl = artifactUrl;
    }
    public Pipeline(String name, Project project, String repoUrl,Credential repoCredential) {
        this.name = name;
        this.project = project;
        this.repositoryUrl = repoUrl;
        this.repositoryCredentials = repoCredential;
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


    public Credential getArtifactCredentials() {
        return artifactCredentials;
    }
    public void setArtifactCredentials(Credential artifactCredentials) {
        this.artifactCredentials = artifactCredentials;
    }


    public String getSAPplateform() { return SAPplateform; }
    public void setSAPplateform(String SAPplateform) { this.SAPplateform = SAPplateform; }


    public boolean isBuildTool() { return isBuildTool; }
    public void setBuildTool(boolean buildTool) { isBuildTool = buildTool; }


    public boolean isBackendIntegrationTests() { return isBackendIntegrationTests; }
    public void setBackendIntegrationTests(boolean backendIntegrationTests) { isBackendIntegrationTests = backendIntegrationTests; }

    public boolean isBackendUnitTests() { return isBackendUnitTests; }
    public void setBackendUnitTests(boolean backendUnitTests) { isBackendUnitTests = backendUnitTests; }

    public boolean isFrontendIntegrationTests() { return isFrontendIntegrationTests; }
    public void setFrontendIntegrationTests(boolean frontendIntegrationTests) { isFrontendIntegrationTests = frontendIntegrationTests; }

    public boolean isFrontendUnitTests() { return isFrontendUnitTests; }
    public void setFrontendUnitTests(boolean frontendUnitTests) { isFrontendUnitTests = frontendUnitTests; }

    public boolean isLint() { return isLint; }
    public void setLint(boolean lint) { isLint = lint; }

    public boolean isNPMdependencyAudit() { return isNPMdependencyAudit; }
    public void setNPMdependencyAudit(boolean NPMdependencyAudit) { isNPMdependencyAudit = NPMdependencyAudit; }

    public boolean isStaticCodeCheck() { return isStaticCodeCheck; }
    public void setStaticCodeCheck(boolean staticCodeCheck) { isStaticCodeCheck = staticCodeCheck; }

    public boolean isEndToEndTests() { return isEndToEndTests; }
    public void setEndToEndTests(boolean endToEndTests) { isEndToEndTests = endToEndTests; }

    public boolean isPerformanceTests() { return isPerformanceTests; }
    public void setPerformanceTests(boolean performanceTests) { isPerformanceTests = performanceTests; }

    public boolean isCodeCoverage() {
        return isCodeCoverage;
    }
    public void setCodeCoverage(boolean codeCoverage) {
        isCodeCoverage = codeCoverage;
    }



    public boolean isAdditionalTools() {
        return isAdditionalTools;
    }
    public void setAdditionalTools(boolean additionalTools) {
        isAdditionalTools = additionalTools;
    }



    public boolean isCheckmarxScan() {
        return isCheckmarxScan;
    }
    public void setCheckmarxScan(boolean checkmarxScan) {
        isCheckmarxScan = checkmarxScan;
    }



    public boolean isFortifyScan() {
        return isFortifyScan;
    }
    public void setFortifyScan(boolean fortifyScan) {
        isFortifyScan = fortifyScan;
    }



    public boolean isSourceClearScan() {
        return isSourceClearScan;
    }
    public void setSourceClearScan(boolean sourceClearScan) {
        isSourceClearScan = sourceClearScan;
    }



    public boolean isWhiteSourceScan() {
        return isWhiteSourceScan;
    }
    public void setWhiteSourceScan(boolean whiteSourceScan) {
        isWhiteSourceScan = whiteSourceScan;
    }

    public String getDeploymentServer() { return deploymentServer; }
    public void setDeploymentServer(String deploymentServer) { this.deploymentServer = deploymentServer; }
}