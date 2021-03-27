package com.focuscorp.DOFAN.controller;

import com.focuscorp.DOFAN.model.Project;
import com.focuscorp.DOFAN.service.PipelineService;
import com.focuscorp.DOFAN.service.ProjectServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ProjectController {

    @Autowired
    private ProjectServiceImplementation projectServiceImplementation;

    ////////////////////////////////Display/////////////////////////////////////////
    @RequestMapping(value = "/displayProjects", method = RequestMethod.GET)
    public String displayProjects(Model model) {
        //used for save new project
        model.addAttribute("project", new Project());
        model.addAttribute("projectToBeEdited", new Project());

        //get list of all project
        List<Project> listProjects = projectServiceImplementation.findAllProjects( );

        //workaround to display pipelines per project
        for(Project project: listProjects){
            if(project.getPipelines()!=null && project.getPipelines().size() !=0 ){
                int pipelinePerProjectSize = project.getPipelines().size();
                project.setPipelinePerProjectCount(pipelinePerProjectSize);
            }
        }
        //pass through listProjects ! project.getPipelines()
        model.addAttribute("listProjects",listProjects);
        return "projects/displayProjects";
    }

    ////////////////////////////////Save new Record/////////////////////////////////
    @RequestMapping(value = "/saveProject", method = RequestMethod.POST)
    public String saveProject(@ModelAttribute("project") Project project) {
        //model.addAttribute("project", new Project());


        // project last update
        DateFormat dateFormat = new SimpleDateFormat("E yyyy/MM/dd HH:mm a");
        Date date = new Date();
        String dformat = dateFormat.format(date);
        project.setProjectLastUpdateDate(dformat);

        projectServiceImplementation.addProject(project);

        return "redirect:/displayProjects";
    }

    ////////////////////////////////Delete/////////////////////////////////////////
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteAccount(@PathVariable String id) {
        projectServiceImplementation.deleteProjectById(id);
        return "redirect:/displayProjects";
    }

    ////////////////////////////////EDIT Post & Get/////////////////////////////////
    @GetMapping("/edit")
    public Project editProject(String projectID, Model model ) {
        Optional<Project> project =  projectServiceImplementation.findProjectById(projectID);
        System.out.println(project);
        //System.out.println(project);
        model.addAttribute("project", project);
        // return "projects/editProject";
        return project.get();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEditedAccount(@ModelAttribute("project") Project project ) {

        DateFormat dateFormat = new SimpleDateFormat("E yyyy/MM/dd HH:mm a");
        Date date = new Date();
        String dformat = dateFormat.format(date);
        project.setProjectLastUpdateDate(dformat);

        projectServiceImplementation.addProject(project);
        return "redirect:/displayProjects";
    }

    //private Project project;

    /*@RequestMapping("/displayProjects")
    public String projects() {
        return "projects/displayProjects";
    }*/

    /*@RequestMapping(value = "/newProject", method = RequestMethod.GET)
    public String newProject(Model model) {
        model.addAttribute("project", new Project());
        return "redirect:/displayProjects";
    }*/


}
