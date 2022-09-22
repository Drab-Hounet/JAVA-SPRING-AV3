package com.rest.third.DAO;

import com.rest.third.Model.PersonnageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PersonnageDAOImpl implements IPersonnageDAO {

    private static List<PersonnageDTO> listPersonnages;

    static {
        listPersonnages = new ArrayList() {{
            add(new PersonnageDTO(1, "tintin1", "Guerrier", 10));
            add(new PersonnageDTO(2, "toto1", "Blagueur", 5));
            add(new PersonnageDTO(3, "bob1", "Eponge", 5));
        }};
    }

    @Override
    public PersonnageDTO findById(int id) {
        Optional<PersonnageDTO> personnage = listPersonnages.stream().filter(el -> el.getId() == id).findFirst();
        if (personnage.isPresent()) {
            return personnage.get();
        }
        return null;
    }

    @Override
    public List<PersonnageDTO> findAll() {
        return listPersonnages;
    }

    @Override
    public PersonnageDTO save(PersonnageDTO personnage) {
        Optional<PersonnageDTO> personnageOpt = listPersonnages.stream().filter(el -> el.getId() == personnage.getId()).findFirst();
        if (personnageOpt.isPresent()) {
            personnageOpt.get().setName(personnage.getName());
            personnageOpt.get().setType(personnage.getType());
            personnageOpt.get().setId(personnage.getLife());
        } else {
            int id = listPersonnages.stream().mapToInt(p -> p.getId()).max().getAsInt();
            personnage.setId(id + 1);
            listPersonnages.add(personnage);
        }
        return personnage;
    }

    @Override
    public Boolean deleteById(int id) {
        Optional<PersonnageDTO> personnageOptional = listPersonnages.stream().filter(p -> p.getId() == id).findFirst();
        if (personnageOptional.isPresent()) {
            listPersonnages = listPersonnages.stream().filter(p -> p.getId() != id).collect(Collectors.toList());
            return true;
        }
        return false;
    }
}
