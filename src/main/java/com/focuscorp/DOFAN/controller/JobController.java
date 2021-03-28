package com.focuscorp.DOFAN.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.focuscorp.DOFAN.model.Credential;
import com.focuscorp.DOFAN.model.Project;
import com.focuscorp.DOFAN.service.CredentialService;
import com.focuscorp.DOFAN.service.PipelineService;
import com.focuscorp.DOFAN.service.ProjectServiceImplementation;
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
import java.util.Base64;
import java.util.Optional;

@Controller
public class JobController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private PipelineService pipelineService;

    @Autowired
    private ProjectServiceImplementation projectServiceImplementation;

    @RequestMapping("/jobs")
    public String jobs() {
        return "/jobs/jobs";
    }


    @RequestMapping(value="/addJob", method = RequestMethod.GET)
    public String addJob(Model model) {
        model.addAttribute("newpipeline", new Pipeline());
        System.out.println(model.getAttribute("newpipeline"));

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

    @RequestMapping(value="/testNexusConnection", method = RequestMethod.POST)
    public ResponseEntity<String> testNexusConnection(@ModelAttribute("newpipeline")Pipeline pipeline, Model model) throws IOException{
        String url = pipeline.getArtifactUrl();
        Credential c = pipeline.getArtifactCredentials();
        String username = c.getUsername();
        String psd = c.getPassword();

        System.out.println("Inputs "+url+" "+username+" "+psd);
        System.out.println(model.getAttribute("newpipeline"));
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

    @RequestMapping(value = "/addPipeline", method = RequestMethod.POST)
    public String addPipeline(@ModelAttribute("newpipeline") Pipeline pipeline, Model model) {

        System.out.println(model.getAttribute("newpipeline"));
        //model.addAttribute("project", new Project());
        //Optional<Project> selectedProject = projectServiceImplementation.findProjectById(pipeline.getProject().getProjectName());
        //System.out.print(selectedProject);
        //pipeline.setProject(selectedProject);
        //findprojectbyId(nameProject)
        System.out.println(pipeline.getProject()+pipeline.getName());
        pipelineService.addPipeline(pipeline);
        return "redirect:/builds";
    }

    @RequestMapping("/builds")
    public String builds() {
        return "/jobs/builds";
    }

    @RequestMapping(value ="/credentials", method = RequestMethod.GET)
    public String credentials(Model model) {
        model.addAttribute("newCredential", new Credential());
        //model.addAttribute("EditableCredential", new Credential());

        List<Credential> list = (List<Credential>)credentialService.findAllCredentials( );
        model.addAttribute("credentials",list);
        return "/jobs/credentials";
    }

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

    @PostMapping("/save")
    public String save(Credential credential,HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        credentialService.addCredential(credential);
        // return "redirect:/credentials";
        return "redirect:"+ referer;
    }

    @GetMapping("/deleteCredential")
    public String deleteCredential(String id) {
        credentialService.deleteById(id);
        return "redirect:/credentials";
    }
}
