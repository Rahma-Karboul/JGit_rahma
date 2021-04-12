package com.focuscorp.DOFAN.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.focuscorp.DOFAN.model.Credential;
import com.focuscorp.DOFAN.model.Project;
import com.focuscorp.DOFAN.service.CredentialService;
import com.focuscorp.DOFAN.service.PipelineService;
import com.focuscorp.DOFAN.service.ProjectServiceImplementation;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import org.apache.commons.io.FileUtils;
import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.focuscorp.DOFAN.model.Pipeline;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class JobController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private PipelineService pipelineService;

    @Autowired
    private ProjectServiceImplementation projectServiceImplementation;

    @RequestMapping("/jobs")
    public String jobs() { return "/jobs/jobs"; }

    @RequestMapping("/builds")
    public String builds() {
        return "/jobs/builds";
    }


    ////////////////////////////////////// Get AddJob page  ////////////////////////////////////////////
    @RequestMapping(value="/addJob", method = RequestMethod.GET)
    public String addJob(Model model) {
        model.addAttribute("newpipeline", new Pipeline());

        //get list of all project
        List<Project> listProjects = projectServiceImplementation.findAllProjects( );

        //get list of all credentials
        List<Credential> listCredentials = (List<Credential>)credentialService.findAllCredentials( );
        List<Credential> ListNexusCredentials = new ArrayList<>();

        for(Credential credential: listCredentials){
            if(credential.getProvider() != null && (credential.getProvider().equals("nexus") || credential.getProvider().equals("Nexus") )){
                ListNexusCredentials.add(credential);}

        }
        //System.out.println(listProjects);
        //System.out.println(listCredentials);
        //System.out.println(ListNexusCredentials);

        model.addAttribute("listProjects",listProjects);
        model.addAttribute("listCredentials",listCredentials);
        model.addAttribute("ListNexusCredentials",ListNexusCredentials);

        return "jobs/addJob";
    }

    ////////////////////////////////////// Get Nexus Status method  ////////////////////////////////////////////
    public static String getStatus(String url, String username, String psd) throws IOException {

        String result = "";
        try {
            URL urlObj = new URL(url);
            String userPass = username+":"+psd;
            System.out.println(userPass);
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userPass.getBytes()));
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            // Set connection timeout
            con.setConnectTimeout(3000);
            con.setRequestProperty("Authorization", basicAuth);
            con.connect();

            int code = con.getResponseCode();
            System.out.println(code);
            if (code == 200) {
                result = "Success";
            }else {result = "An authorized with the code "+code;}
        } catch (Exception e) {
            result = "Error";
        }
        System.out.println("ArtifactCnxStatus "+result);
        return result;
    }

    ////////////////////////////////////// Test Nexus Connexion  ////////////////////////////////////////////
    @RequestMapping(value="/testNexusConnection", method = RequestMethod.POST)
    public ResponseEntity<String> testNexusConnection(@ModelAttribute("newpipeline")Pipeline pipeline, Model model) throws IOException{
        String url = pipeline.getArtifactUrl();
        Credential c = pipeline.getArtifactCredentials();
        String username = c.getUsername();
        String psd = c.getPassword();

        System.out.println("Inputs "+url+" "+username+" "+psd);
        //pipelineService.addPipeline(pipeline);
        String status1 = getStatus(url,username,psd);

        boolean valid;
        if(status1 == "Success"){
            valid = true;
        }else{ valid = false;
        }

        System.out.println(valid ? ResponseEntity.ok(url) : ResponseEntity.badRequest().body(url));
        return valid ? ResponseEntity.ok(url) : ResponseEntity.badRequest()
                .body(url);
    }

    ////////////////////////////////////// Create/Add Pipeline  ////////////////////////////////////////////
    @RequestMapping(value = "/addPipeline", method = RequestMethod.POST)
    public String addPipeline(@ModelAttribute("newpipeline") Pipeline pipeline, Model model) throws Exception{

        //System.out.println(model.getAttribute("newpipeline"));

        ////////////////////// Adding Pipeline to database ///////////
        pipelineService.addPipeline(pipeline);
        ////////////////////// Preparing Jenkinsfile ///////////////////////////
        FileWriter fw = new FileWriter("Jenkinsfile", true);
        BufferedWriter bw = new BufferedWriter(fw);
        if(pipeline.isBuildTool()){
            System.out.println("Buildtool activated");
            bw.write("node() {\n" +
                    "    stage('prepare') {\n" +
                    "        checkout scm\n" +
                    "        setupCommonPipelineEnvironment script:this\n" +
                    "    }\n" +
                    "}");
            bw.newLine();
            bw.close();
        }
        /*try {
            JenkinsServer jenkins = new JenkinsServer(new URI("http://10.5.14.122/"), "admin", "114a3496334f9a730e749d70cfeac25979");
            String sourceXML = FileUtils.readFileToString(new File(".pipeline/config.xml"), "UTF-8");
            jenkins.createJob("SalutTaJours", sourceXML,true);
        } catch (Exception e) {
        System.out.println("Exception Occured!!!");
        e.printStackTrace();}*/

        return "redirect:/builds";
    }

    ////////////////////////////////////// Display list of Credentials  ////////////////////////////////////////////
    @RequestMapping(value ="/credentials", method = RequestMethod.GET)
    public String credentials(Model model) {
        model.addAttribute("newCredential", new Credential());
        //model.addAttribute("EditableCredential", new Credential());

        List<Credential> list = (List<Credential>)credentialService.findAllCredentials( );
        model.addAttribute("credentials",list);
        return "/jobs/credentials";
    }

    ////////////////////////////////////// Create/Add new credential (first Version) ////////////////////////////////////////////
    @RequestMapping(value = "/addCredential", method = RequestMethod.POST)
    public String addCredential(@ModelAttribute("newCredential") Credential credential, Model model,HttpServletRequest request) {
        String referer = request.getHeader("Referer");

        credentialService.addCredential(credential);
        //model.addAttribute("user", userService.findAllUsers());
        //return "redirect:/credentials";
        return "redirect:"+ referer;
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Credential findOne(String id) {
        return credentialService.findById(id);
    }

    ////////////////////////////////////// Create/Add new credential (second Version) ////////////////////////////////////////////
    @PostMapping("/save")
    public String save(Credential credential,HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        credentialService.addCredential(credential);
        // return "redirect:/credentials";
        return "redirect:"+ referer;
    }

    ////////////////////////////////////// Delete credential  ////////////////////////////////////////////
    @GetMapping("/deleteCredential")
    public String deleteCredential(String id) {
        credentialService.deleteById(id);
        return "redirect:/credentials";
    }



    /////////Test SCM conx
    @RequestMapping(value="/checkSCMConnection", method = RequestMethod.POST)
    public ResponseEntity<String> check(@ModelAttribute("checkpipeline")Pipeline pipeline) throws IOException  {
        GHRepository repo;
        boolean isReachable = true;
        String url1 = "rteeeeye";
               
        //connect with DOFAN GENERAL USER
        GitHub github = new GitHubBuilder().withOAuthToken("036d4a6d5042ceec139ed466eef369324b81567f").build();

        //get from input
        String urlrepo = pipeline.getRepositoryUrl();
        String urlRepoStr = "TesnimK/newfortest"; /*urlrepo.substring(19);*/
     //   String sRepoUrl = "TesnimK/newfortest" ;//martinwojtus/tutorials" //;
       
        try{
             repo = github.getRepository( urlRepoStr );
           //  isReachable = true;
       // GHRepository repo1 = github.getRepository( "TesnimK/anotherTestUser" );
       } catch (IOException e1) {
                System.out.println("Repository content can't be reached!");
                if(github.getMyInvitations().size()>0)
                            { github.getMyInvitations().get(0).accept();
                               repo = github.getRepository( urlRepoStr );
                                //isReachable = true ;
                               // return  ResponseEntity.ok(url1); 
                            }
                else
                {
                    //private with no invitation OR wrong repo name
                  //  return   ResponseEntity.badRequest().body(url1);
                  isReachable = false ;
                }
                
       
      }
       /// isPublic =(!repo.isPrivate());
       
        
        return isReachable ? ResponseEntity.ok(url1 ) : ResponseEntity.badRequest().body(url1);
    }

}
