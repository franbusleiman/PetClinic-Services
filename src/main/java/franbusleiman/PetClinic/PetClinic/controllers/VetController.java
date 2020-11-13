package franbusleiman.PetClinic.PetClinic.controllers;

import franbusleiman.PetClinic.PetClinic.model.Owner;
import franbusleiman.PetClinic.PetClinic.model.Pet;
import franbusleiman.PetClinic.PetClinic.services.VetService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService){
        this.vetService  = vetService;
    }

    @RequestMapping("/VetPage")
    public String getVets(Model model){


        return "VetPage";
    }

    @RequestMapping("/wichVet")
    public String getOwners(Model model, HttpServletRequest request){

        int vet_id = Integer.parseInt(request.getParameter("vet_id"));

        if(vetService.findById(vet_id).isPresent()) {
            Set<Owner> owners =  vetService.findById(vet_id).get().getOwners();
            Set<Pet> pets = vetService.findById(vet_id).get().getPets();
            model.addAttribute("owners", owners);
            model.addAttribute("pets", pets);


        }

     return "Vet";
    }

}
