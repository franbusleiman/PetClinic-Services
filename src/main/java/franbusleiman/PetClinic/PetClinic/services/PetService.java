package franbusleiman.PetClinic.PetClinic.services;

import franbusleiman.PetClinic.PetClinic.model.Pet;
import franbusleiman.PetClinic.PetClinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface PetService extends CrudRepository<Pet, Integer> {
}
