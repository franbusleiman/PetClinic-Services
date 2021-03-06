package franbusleiman.PetClinic.PetClinic.controllers;

import franbusleiman.PetClinic.PetClinic.model.Owner;
import franbusleiman.PetClinic.PetClinic.model.Vet;
import franbusleiman.PetClinic.PetClinic.services.OwnerService;
import franbusleiman.PetClinic.PetClinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Controller
public class OwnerController {
    private final OwnerService ownerService;
private final VetService vetService;
    public OwnerController(OwnerService ownerService, VetService vetService){
        this.ownerService = ownerService;
        this.vetService = vetService;
    }


    @RequestMapping("/chooseVet")
    public String chooseVet(){

        return "chooseVet";
    }

    @RequestMapping("/ownerPage")
        public String getOwner(HttpServletRequest request, Model model){
      int vet_id =Integer.parseInt(request.getParameter("vet_id"));
        model.addAttribute("vet_id", vet_id);

           return "ownerPage";
        }

        @RequestMapping({"/ownerSaver", "ownerSaver"})
    public String saveOwner(HttpServletRequest request, Model model, Owner owner){

            ownerService.save(owner);

       int  vet_id = Integer.parseInt(request.getParameter("vet_id"));

       if(vetService.findById(vet_id).isPresent()) {

           owner.setVet(vetService.findById(vet_id).get());
           ownerService.save(owner);
       }
            if(vetService.findById(vet_id).isPresent()) {
                vetService.findById(vet_id).get().getOwners().add(owner);
            }
        model.addAttribute("vet_id", vet_id);
        model.addAttribute("owner_id", owner.getId());


        return "pet";
        }

    }

