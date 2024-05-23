package com.portfolioapps.officecreationreservation.Office;

import com.portfolioapps.officecreationreservation.Office.Office;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Integer> {

}
