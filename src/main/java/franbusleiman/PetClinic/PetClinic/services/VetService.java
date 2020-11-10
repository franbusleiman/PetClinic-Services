package franbusleiman.PetClinic.PetClinic.services;

import franbusleiman.PetClinic.PetClinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetService extends CrudRepository<Vet, Integer> {
}
