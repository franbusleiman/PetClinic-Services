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
    public String savePet(HttpServletRequest request, Model model, Pet pet, Owner owner, Vet vet){

        model.addAttribute("pet", pet);

        petService.save(pet);

        int  vet_id = Integer.parseInt(request.getParameter("vet_id"));

        Vet vet1 = vetService.findById(vet_id).get();

        int  owner_id = Integer.parseInt(request.getParameter("owner_id"));

        Owner owner1 = ownerService.findById(owner_id).get();



        owner1.setPet(pet);
        pet.setOwner(owner1);
       pet.setVet(vet1);
       vet.getPets().add(pet);

        return "index.html";
    }

}
