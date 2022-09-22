package com.rest.third.controller;

import com.rest.third.DAO.IPersonnageDAO;
import com.rest.third.DAO.PersonnageDAOImpl;
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

    private IPersonnageDAO personnageDAO;

    public ApiPersonnage(IPersonnageDAO personnageDAO) {
        this.personnageDAO = personnageDAO;
    }

    @ApiOperation(value = "Récupère les personnages")
    @GetMapping(value = "/api/personnage")
    public ResponseEntity<List<PersonnageDTO>> getPeronnages() {
        ResponseEntity<List<PersonnageDTO>> responseEntity = new ResponseEntity<>(personnageDAO.findAll(), HttpStatus.OK);
        return responseEntity;
    }

    @ApiOperation(value = "Récupère un personnage")
    @GetMapping(value = "/api/personnage/{id}")
    public ResponseEntity<PersonnageDTO> getPeronnage(@PathVariable int id) {
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        PersonnageDTO personnage = personnageDAO.findById(id);
        if (personnage != null) {
            return new ResponseEntity<PersonnageDTO>(personnage, HttpStatus.OK);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Ajoute un personnage")
    @PostMapping(value = "/api/personnage")
    public ResponseEntity<PersonnageDTO> addPersonnage(@RequestBody PersonnageDTO personnage) {
        ResponseEntity<PersonnageDTO> responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        if (!personnage.getName().isEmpty() && !personnage.getType().isEmpty() && personnage.getLife() != 0) {
            personnage = personnageDAO.save(personnage);
            responseEntity = new ResponseEntity<>(personnage, HttpStatus.OK);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Modifie un personnage")
    @PutMapping(value = "/api/personnage")
    public ResponseEntity<PersonnageDTO> modifyPersonnage(@RequestBody PersonnageDTO personnage) {
        ResponseEntity<PersonnageDTO> responseEntity = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        PersonnageDTO personnageSaved = personnageDAO.save(personnage);
        if (personnageSaved != null) {
            return new ResponseEntity<>(personnageSaved, HttpStatus.OK);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Supprime un personnage")
    @DeleteMapping(value = "/api/personnage/{id}")
    public ResponseEntity<Boolean> modifyPersonnage(@PathVariable int id) {
        ResponseEntity<Boolean> responseEntity = new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        if (personnageDAO.deleteById(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return responseEntity;
    }
}
