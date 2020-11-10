package franbusleiman.PetClinic.PetClinic.services;

import franbusleiman.PetClinic.PetClinic.model.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface OwnerService extends CrudRepository<Owner, Integer> {
}
