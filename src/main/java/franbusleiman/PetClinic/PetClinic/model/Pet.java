package franbusleiman.PetClinic.PetClinic.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String petType;

    private String healthProblem;
    private String problemEvolution;

    private Date date;


    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JoinColumn(name = "owner_id")
    private Owner owner;
   @ManyToOne
   @JoinColumn(name ="vet_id")
    private Vet vet;

    public Pet() {
    }

    public Pet(String name, String petType, String healthProblem){
        this.name = name;
        this.petType = petType;
        this.healthProblem = healthProblem;
        this.date = date;


    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public String getHealthProblem() {
        return healthProblem;
    }

    public void setHealthProblem(String healthProblem) {
        this.healthProblem = healthProblem;
    }

    public String getProblemEvolution() {
        return problemEvolution;
    }

    public void setProblemEvolution(String problemEvolution) {
        this.problemEvolution = problemEvolution;
    }

    public String getDate() {

        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/YYYY");

        return formateador.format(date);
    }

    public void setDate(Date date) {

        this.date = date;
    }
}
