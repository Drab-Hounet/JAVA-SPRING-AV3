package com.rest.third.DAO;

import com.rest.third.Model.PersonnageDTO;

import java.util.List;

public interface IPersonnageDAO {
    PersonnageDTO findById(int id);

    List<PersonnageDTO> findAll();

    PersonnageDTO save(PersonnageDTO personnageDTO);

    Boolean deleteById(int id);
}
