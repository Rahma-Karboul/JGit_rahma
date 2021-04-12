package com.focuscorp.DOFAN;

import com.focuscorp.DOFAN.model.Pipeline;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.StreamUtils;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.focuscorp.DOFAN.model.Credential;
import com.cdancy.jenkins.rest.domain.job.JobList;
import com.cdancy.jenkins.rest.domain.job.PipelineNode;
import com.cdancy.jenkins.rest.domain.job.StageFlowNode;
import com.cdancy.jenkins.rest.domain.job.Workflow;
import com.cdancy.jenkins.rest.features.JobsApi;
import org.jenkinsci.plugins.workflow.job.WorkflowJob;
import com.cdancy.jenkins.rest.JenkinsClient;
import com.offbytwo.jenkins.JenkinsServer;

import java.io.*;
import java.net.URI;
import java.util.*;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import java.nio.file.Files;
import org.apache.commons.io.FileUtils;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class JobControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //ALL_CREDENTIALS
    @Test
    public void AllCredentials_ShouldReturnCredentials() throws Exception   {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/credentials"))
                .andDo(print())
                .andExpect(view().name("/jobs/credentials"))
                .andExpect(status().isOk());
    }

    //DELETE_CREDENTIAL

    @Test
    public void deleteCredential_ShouldDeleteCredential() throws Exception   {

     /*String id ="id1234";
      this.mockMvc.perform(MockMvcRequestBuilders.get("/delete/"+id)
                   .param("id", id))
                   .andDo(print())
                   .andExpect(MockMvcResultMatchers.redirectedUrl("/users"))
                  . andExpect(status().isFound());
                // .andExpect(view().name("/users"))
               //  .andExpect(status().isOk());

             verify(userService, times(1)).deleteById(id);*/
    }
    @Test
    public void AddCredential_ShouldAddCredential() throws Exception   {

        Credential oCredential = new Credential();
        mockMvc.perform(MockMvcRequestBuilders.post("/addCredential")
                .flashAttr("newCredential", oCredential)).andDo(print())
                //  .param("username", sUsername)
                //  .param("email", sEmail)
                //  .param("password", sPassword))

                .andExpect(MockMvcResultMatchers.redirectedUrl("/credentials"))
                . andExpect(status().isFound());
    }

    @Test
    public void EditCredential_ShouldEditCredential() throws Exception   {

    }

    @Test
    public void AddPipeline_ShouldAddPipeline() throws Exception   {

        Pipeline oPipeline = new Pipeline();
        mockMvc.perform(MockMvcRequestBuilders.post("/addPipeline")
                .flashAttr("newPipeline", oPipeline)).andDo(print())
                //  .param("username", sUsername)
                //  .param("email", sEmail)
                //  .param("password", sPassword))

                //.andExpect(MockMvcResultMatchers.redirectedUrl("/jobs"))
                . andExpect(status().isFound());
    }

    @Test
    public void jenkinsStatusUsingOffByTwo() throws Exception{
        //official docs : https://github.com/jenkinsci/java-client-api
        // in the documentation http://localhost:8080/jenkins
        try {
            JenkinsServer jenkins = new JenkinsServer(new URI("http://10.5.14.122/"), "admin", "RDrd123++");

            Map<String, Job> jobs = jenkins.getJobs();
            System.out.println("Job Size : " + jobs.size());

         //jenkins.createFolder("TesnimFolder",false);
            String sourceXML = FileUtils.readFileToString(new File(".pipeline/config.xml"), "UTF-8");
            jenkins.createJob("HelloRamaaaaaaaa", sourceXML,true);
            //System.out.println(" JenkinsXMLfile: "+jenkins.getJobXml("rahmaDOFANSecurity"));
        } catch (Exception e) {
        System.out.println("Exception Occured!!!");
        e.printStackTrace();
    }
        //Pipeline MultiBranch
        //JobWithDetails job = jobs.get("rahmaDOFANSecurity").details();
       // System.out.println("Name : "+ job.getName());
       // System.out.println("URL : "+ job.getUrl());

        //Normal Job
       // JobWithDetails jobHello = jobs.get("checkstyle").details();
       // System.out.println("URL : "+ jobHello.getUrl());
        //HelloDOFAN
       // System.out.println( jobHello.getDisplayName());
        //For Testing...
       // System.out.println(jobHello.getDescription());
        //5
       // System.out.println(jobHello.getLastBuild().details().getNumber());
        //5
       //System.out.println(jobHello.getLastFailedBuild().details().getNumber());
        //System.out.println("console" + jobHello.details().getLastBuild().details().getConsoleOutputText());
        //System.out.println("----------------------------------------------");
        //JobWithDetails jobHelloDofan_ = jobs.get("HelloDofan_").details();
       // System.out.println(jobHelloDofan_);
    }

    @Test
    public void whenAppendToFileUsingFileWriter_thenCorrect()
            throws IOException {

        FileWriter fw = new FileWriter("Jenkinsfile", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Spain");
        bw.newLine();
        bw.close();

    }

    @Test
    public void whenCommitJenkinsFile() throws IOException {
        //connect with DOFAN GENERAL USER
        GitHub github = new GitHubBuilder().withOAuthToken("036d4a6d5042ceec139ed466eef369324b81567f").build();

        GHRepository repository = github.getRepository("focuscorp/DOFAN");

        /*GHCommitBuilder x = repository.createCommit();
        x.message("Test Commit");
        GHCommit x2 =x.create();
        String Jenkinsfile = FileUtils.readFileToString(new File("Jenkinsfile"), "UTF-8");
        Object x2 = new GHEventPayload.Push.PushCommit();
        System.out.println(repository.getCollaboratorNames());
        System.out.println(x2.getAuthor().getName());*/
        //repository.createPullRequest("Test Pull Request","hello","master",Jenkinsfile);
    }

    @Test
    public void testBlJenkinsClientAPI() throws Exception{

        JenkinsClient client = JenkinsClient.builder()
                .endPoint("http://10.5.14.122/") // Optional. Defaults to http://127.0.0.1:8080
                .credentials("admin:RDrd123++") // Optional.
                .build();

        //System.out.println(client.api().systemApi().systemInfo());

        //For Testing...
        //System.out.println("description "+client.api().jobsApi().description( null, "rahmaDOFANSecurity"));

        //BuildInfo[]
        //System.out.println("buildInfo "+client.api().jobsApi().buildInfo(null,"rahmaDOFANSecurity",1));

        //jobInfo[]
        //System.out.println("jobInfo "+client.api().jobsApi().jobInfo(null,"rahmaDOFANSecurity"));

        //lastBuildNumber 6
        //System.out.println("lastBuildNumber "+client.api().jobsApi().lastBuildNumber(null,"HelloDOFAN"));

        //System.out.println("-----------------------------------------------");

        //Error parsing input: Null color || as it's not a job
        //System.out.println("jobInfo "+client.api().jobsApi().jobInfo(null,"DOFAN"));


        //http://localhost:8080/job/DOFAN/job/mohtadiBranch/21/wfapi/describe
        //@Path("{optionalFolderPath}job/{name}/{number}/wfapi/describe")
        //System.out.println("workflow "+ client.api().jobsApi().workflow("DOFAN/","mohtadiBranch",21));²


        //http://localhost:8080/job/DOFAN/job/mohtadiBranch/21/execution/node/6/wfapi/describe
        //@Path("{optionalFolderPath}job/{name}/{number}/execution/node/{nodeId}/wfapi/describe")
        //System.out.println("pipeline node "+ client.api().jobsApi().pipelineNode("DOFAN/","mohtadiBranch",21, 6));

        /*PipelineNode pipelineNode = client.api().jobsApi().pipelineNode("DOFAN/","mohtadiBranch",21, 47);
        long durantion = pipelineNode.durationTimeMillis();
        String name = pipelineNode.name();
        List<StageFlowNode>  flownodes = pipelineNode.stageFlowNodes();
        String status = pipelineNode.status();



        System.out.println(
                "durantion "+ durantion+
                        "\n name "+ name+
                        "\n flownodes "+flownodes+
                        "\n status "+ status
        );*/

        System.out.println("-----------------------------------------------");
        client.api().jobsApi().create(null,"testJobCreationFromCLientAPI","<general>\n" +
                "  <buildTool>maven</buildTool>\n" +
                "</general>");
    }

}
