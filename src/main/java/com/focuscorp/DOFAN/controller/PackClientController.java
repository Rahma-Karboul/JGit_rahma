package com.focuscorp.DOFAN.controller;

import com.focuscorp.DOFAN.model.Machine;
import com.focuscorp.DOFAN.model.Pack;
import com.focuscorp.DOFAN.model.PackClient;
import com.focuscorp.DOFAN.service.EmailSenderService;
import com.focuscorp.DOFAN.service.MachineService;
import com.focuscorp.DOFAN.service.PackClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PackClientController {

    @Autowired
    private PackClientService packClientService;

    @Autowired
    private EmailSenderService emailSenderService;

/*    @Autowired
    private UserService userService;*/

    @Autowired
    private MachineService machineService;

    @Autowired
    private PaypalController paypalController;

    public PackClientController() {
    }

    @RequestMapping(method = RequestMethod.POST, value = "payBasicPack")
    public String addBasicClientPack(@ModelAttribute("basicClientPack") PackClient basicClientPack, Model model) {

        //use a static user for test => get authenticated user later
/*        User currentClient = (User)userService.findUserByEmail("mohta@gmail.com");
        basicClientPack.setClient(currentClient);*/

        // get list of machines
        List<Machine> listMachines = machineService.findAllMachines();
        basicClientPack.setMachines(listMachines);

        //add basic pack client
        packClientService.addPackClient(basicClientPack);
        System.out.println(basicClientPack);

        //payment process

        return paypalController.payment(basicClientPack.getPrice());
    }

    @RequestMapping(method = RequestMethod.POST, value = "payProPack")
    public String addProClientPack(@ModelAttribute("proClientPack") PackClient proClientPack, Model model) {

        //add pro pack client
        packClientService.addPackClient(proClientPack);
        System.out.println(proClientPack);

        //payment process

        return paypalController.payment(proClientPack.getPrice());
    }

    @RequestMapping(method = RequestMethod.POST, value = "packEntreprise")
    public String requestPackEntreprise(@ModelAttribute("entrepClientPack") PackClient entrepClientPack, @ModelAttribute("entrepPack") Pack entrepPack,
                                       /* @ModelAttribute("clientEntrep") User clientEntrep,*/
                                        Model model) {


        System.out.println(entrepClientPack);
        System.out.println(entrepPack);
        // System.out.println(clientEntrep);

        String emailText =  "Client Infos:" +  System.lineSeparator() +
/*                "   Name: "+ clientEntrep.getUsername() + System.lineSeparator() +
                "   Email: "+ clientEntrep.getEmail() + System.lineSeparator() +
                "   Phone: "+ clientEntrep.getPhone() + System.lineSeparator() +
                "   Company: " + clientEntrep.getCompany() + System.lineSeparator() + System.lineSeparator() +*/
                "Pack Infos: " + System.lineSeparator() +
                "   Deployment: " + entrepPack.getDeployment() + System.lineSeparator() +
                "   Concurrent Pipelines: "+ entrepClientPack.getPipelines() + System.lineSeparator() +
                "   Number of users: " + entrepPack.getUsers();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("dofangen@gmail.com");
        mailMessage.setSubject("Custom Entrprise Pack");
        mailMessage.setFrom("dofangen@gmail.com");
        mailMessage.setText(emailText);

        emailSenderService.sendEmail(mailMessage);
        return "index";

    }

}
