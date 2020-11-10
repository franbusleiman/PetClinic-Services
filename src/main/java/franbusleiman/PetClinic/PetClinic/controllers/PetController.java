package franbusleiman.PetClinic.PetClinic.controllers;

import franbusleiman.PetClinic.PetClinic.model.Owner;
import franbusleiman.PetClinic.PetClinic.model.Pet;
import franbusleiman.PetClinic.PetClinic.model.Vet;
import franbusleiman.PetClinic.PetClinic.services.OwnerService;
import franbusleiman.PetClinic.PetClinic.services.PetService;
import franbusleiman.PetClinic.PetClinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PetController {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;

    public PetController(OwnerService ownerService, VetService vetService, PetService petService){
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;

    }

    @RequestMapping("/petSaver")
    public String savePet(HttpServletRequest request, Model model, Pet pet){

        petService.save(pet);

        int  vet_id = Integer.parseInt(request.getParameter("vet_id"));
        int owner_id = Integer.parseInt(request.getParameter("owner_id"));

        if(vetService.findById(vet_id).isPresent()) {
            pet.setVet(vetService.findById(vet_id).get());
        }
        if(vetService.findById(vet_id).isPresent()) {
            vetService.findById(vet_id).get().getPets().add(pet);
        }
        if(ownerService.findById(owner_id).isPresent()) {
            pet.setOwner(ownerService.findById(owner_id).get());
        }
        if(ownerService.findById(owner_id).isPresent()) {
            ownerService.findById(owner_id).get().setPet(pet);
        }


        return "index";
    }

}
