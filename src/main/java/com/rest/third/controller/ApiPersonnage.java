package com.rest.third.controller;

import com.rest.third.Model.PersonnageDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ApiPersonnage {

    static List<PersonnageDTO> listPersonnages = new ArrayList() {{
        add(new PersonnageDTO(1, "tintin", "Guerrier", 10));
        add(new PersonnageDTO(2, "toto", "Blagueur", 5));
        add(new PersonnageDTO(3, "bob", "Eponge", 5));
    }};

    @ApiOperation(value = "Récupère les personnages")
    @GetMapping(value = "/api/personnage")
    public ResponseEntity<List<PersonnageDTO>> getPeronnages() {
        ResponseEntity<List<PersonnageDTO>> responseEntity = new ResponseEntity<>(listPersonnages, HttpStatus.OK);
        return responseEntity;
    }

    @ApiOperation(value = "Récupère un personnage")
    @GetMapping(value = "/api/personnage/{id}")
    public ResponseEntity<PersonnageDTO> getPeronnage(@PathVariable int id) {
        ResponseEntity<PersonnageDTO> responseEntity = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        Optional<PersonnageDTO> personnageOptional = listPersonnages.stream().filter(p -> p.getId() == id).findFirst();
        if (personnageOptional.isPresent()) {
            return new ResponseEntity<>(personnageOptional.get(), HttpStatus.OK);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Ajoute un personnage")
    @PostMapping(value = "/api/personnage")
    public ResponseEntity<PersonnageDTO> addPersonnage(@RequestBody PersonnageDTO personnage) {
        ResponseEntity<PersonnageDTO> responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        // Récupération du dernier id
        int id = listPersonnages.stream().mapToInt(p -> p.getId()).max().getAsInt();
        if (!personnage.getName().isEmpty() && !personnage.getType().isEmpty() && personnage.getLife() != 0)
            listPersonnages.add(new PersonnageDTO(id + 1, personnage.getName(), personnage.getType(), personnage.getLife()));
        return new ResponseEntity<>(personnage, HttpStatus.OK);
    }

    @ApiOperation(value = "Modifie un personnage")
    @PutMapping(value = "/api/personnage")
    public ResponseEntity<PersonnageDTO> modifyPersonnage(@RequestBody PersonnageDTO personnage) {
        ResponseEntity<PersonnageDTO> responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        // Récupération du personnage
        Optional<PersonnageDTO> personnageOptional = listPersonnages.stream().filter(p -> p.getId() == personnage.getId()).findFirst();
        if (personnageOptional.isPresent()) {
            if (!personnage.getName().isEmpty() && !personnage.getType().isEmpty() && personnage.getLife() != 0) {
                PersonnageDTO personnageTobeModified = personnageOptional.get();
                personnageTobeModified.setLife(personnage.getLife());
                personnageTobeModified.setName(personnage.getName());
                personnageTobeModified.setType(personnage.getType());
                return new ResponseEntity<>(personnage, HttpStatus.OK);
            }
        }
        return responseEntity;
    }

    @ApiOperation(value = "Supprime un personnage")
    @DeleteMapping(value = "/api/personnage/{id}")
    public ResponseEntity<Boolean> modifyPersonnage(@PathVariable int id) {
        ResponseEntity<Boolean> responseEntity = new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        // Récupération du personnage
        Optional<PersonnageDTO> personnageOptional = listPersonnages.stream().filter(p -> p.getId() == id).findFirst();
        if (personnageOptional.isPresent()) {
            listPersonnages = listPersonnages.stream().filter(p -> p.getId()!=id).collect(Collectors.toList());
            return  new ResponseEntity<>(true, HttpStatus.OK);
        }
        return responseEntity;
    }
}
