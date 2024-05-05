package com.portfolioapps.officecreationreservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends CrudRepository<OfficeCreationForm, Integer> {

}
