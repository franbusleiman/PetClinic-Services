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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class PetController {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;

    public PetController(OwnerService ownerService, VetService vetService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;

    }

    @RequestMapping("/petSaver")
    public String savePet(HttpServletRequest request, Model model, Pet pet) {


        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/YYYY");
        Date date = new Date();

        pet.setDate(date);
        petService.save(pet);

        int vet_id = Integer.parseInt(request.getParameter("vet_id"));
        int owner_id = Integer.parseInt(request.getParameter("owner_id"));

        if (vetService.findById(vet_id).isPresent()) {
            pet.setVet(vetService.findById(vet_id).get());

        }
        if (vetService.findById(vet_id).isPresent()) {
            vetService.findById(vet_id).get().getPets().add(pet);
        }
        if (ownerService.findById(owner_id).isPresent()) {
            pet.setOwner(ownerService.findById(owner_id).get());
            petService.save(pet);
        }
        if (ownerService.findById(owner_id).isPresent()) {
            ownerService.findById(owner_id).get().setPet(pet);
        }


        return "index";
    }

    @RequestMapping("editor")
    public String editar(Model model, HttpServletRequest request) {
        int owner_id = Integer.parseInt(request.getParameter("owner_id"));
        int pet_id = Integer.parseInt(request.getParameter("pet_id"));

        if (ownerService.findById(owner_id).isPresent()) {
            model.addAttribute("owner", ownerService.findById(owner_id).get());
        }
        if (petService.findById(pet_id).isPresent()) {
            model.addAttribute("pet", petService.findById(pet_id).get());

        }
        return "EditPage";
    }

    @RequestMapping("/modificarDatos")
    public String modificarDatos(HttpServletRequest request, Model model) {
        String firstName1 = request.getParameter("firstName1");
        String lastName1 = request.getParameter("lastName1");
        String telephoneNumber1 = request.getParameter("telephoneNumber1");
        String name1 = request.getParameter("name1");
        String petType1 = request.getParameter("petType1");
        String healthProblem1 = request.getParameter("healthProblem1");
        String problemEvolution1 = request.getParameter("problemEvolution1");
        int owner_id = Integer.parseInt(request.getParameter("owner_id"));
        int pet_id = Integer.parseInt(request.getParameter("pet_id"));

        if (ownerService.findById(owner_id).isPresent()) {
            ownerService.findById(owner_id).get().setFirstName(firstName1);
            ownerService.findById(owner_id).get().setLastName(lastName1);
            ownerService.findById(owner_id).get().setTelephoneNumber(telephoneNumber1);
            ownerService.save(ownerService.findById(owner_id).get());
        }

        if (petService.findById(pet_id).isPresent()) {
            petService.findById(pet_id).get().setName(name1);
            petService.findById(pet_id).get().setPetType(petType1);
            petService.findById(pet_id).get().setHealthProblem(healthProblem1);
            petService.findById(pet_id).get().setProblemEvolution(problemEvolution1);
            petService.save(petService.findById(pet_id).get());
        }


        return "index";
    }

    @RequestMapping("/eliminar")
    public String eliminarCaso(HttpServletRequest request, Model model) {
        int owner_id = Integer.parseInt(request.getParameter("owner_id"));
        int pet_id = Integer.parseInt(request.getParameter("pet_id"));


      if (petService.findById(pet_id).isPresent()) {
          petService.delete(petService.findById(pet_id).get());
        }
        return "index";
    }

}