package com.focuscorp.DOFAN.controller;

import com.focuscorp.DOFAN.model.*;
import com.focuscorp.DOFAN.service.MachineService;
import com.focuscorp.DOFAN.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PackController {

/*    @RequestMapping("/pack")
    public String projects() {
        return "projects/pack";
    } */


    @Autowired
    private PackService packService;

    @Autowired
    private MachineService machineService;

    public PackController() {
    }

    @RequestMapping(value = "/packs", method = RequestMethod.GET)
    public String packList(Model model) {

        // get packs by Offer
        Pack packBasic = (Pack)packService.findPackByOffer(EOffer.BASIC);
        Pack packPro = (Pack)packService.findPackByOffer(EOffer.PRO);
        Pack packEntreprise = (Pack)packService.findPackByOffer(EOffer.Entreprise);

        // add packs to model
        model.addAttribute("packBasic",packBasic);
        model.addAttribute("packPro",packPro);
        model.addAttribute("packEntreprise",packEntreprise);

        System.out.println(new PackClient(packBasic));

        // intialise pack client by pack
        model.addAttribute("basicClientPack", new PackClient(packBasic));
        model.addAttribute("proClientPack", new PackClient(packPro));

        //get machines by type
        Machine machineSmall = (Machine)machineService.findMachineByType(EType.Small);
        Machine machineMedium = (Machine)machineService.findMachineByType(EType.Medium);
        Machine machineLarge = (Machine)machineService.findMachineByType(EType.Large);

        //add attributes for machine pack
        model.addAttribute("machineSmall", machineSmall);
        model.addAttribute("machineMedium", machineMedium);
        model.addAttribute("machineLarge", machineLarge);

        //add attributes for pack entreprise
        model.addAttribute("entrepClientPack", new PackClient());
        model.addAttribute("entrepPack", new Pack());
        model.addAttribute("clientEntrep", new User());

        return "packs/packs";
    }
}
