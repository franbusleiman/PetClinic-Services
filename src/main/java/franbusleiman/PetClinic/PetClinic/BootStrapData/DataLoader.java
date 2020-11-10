package franbusleiman.PetClinic.PetClinic.BootStrapData;

import franbusleiman.PetClinic.PetClinic.model.Vet;
import franbusleiman.PetClinic.PetClinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final VetService vetService;

    public DataLoader(VetService vetService){
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Vet juanRodriguez = new Vet("Juan", "Rodriguez");
        Vet marceloMorales = new Vet("Marcelo", "Morales");
        Vet monicaParedes = new Vet("Monica", "Paredes");

        vetService.save(juanRodriguez);
        vetService.save(marceloMorales);
        vetService.save(monicaParedes);


    }
}
