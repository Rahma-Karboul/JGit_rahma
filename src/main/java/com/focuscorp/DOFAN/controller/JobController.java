package com.focuscorp.DOFAN.controller;

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

@Controller
public class JobController {

    @RequestMapping("/jobs")
    public String jobs() {
        return "/jobs/jobs";
    }


    @RequestMapping(value="/addJob", method = RequestMethod.GET)
    public String addJob(Model model) {
        model.addAttribute("newpipeline", new Pipeline());
        System.out.println(model.getAttribute("newpipeline"));
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
    public ResponseEntity<String> validateEmail(@ModelAttribute("newpipeline")Pipeline pipeline, Model model) throws IOException{
        String url = pipeline.getArtifactUrl();
        String username = pipeline.getArtifactUsername();
        String psd = pipeline.getArtifactPassword();
        System.out.println("Inputs "+url+" "+username+" "+psd);
        System.out.println(model.getAttribute("newpipeline"));
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


    @RequestMapping("/builds")
    public String builds() {
        return "/jobs/builds";
    }

    @RequestMapping("/credentials")
    public String credentials() {
        return "/jobs/credentials";
    }
}
