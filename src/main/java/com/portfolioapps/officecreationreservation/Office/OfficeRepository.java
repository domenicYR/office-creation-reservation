package com.portfolioapps.officecreationreservation.Office;

import com.portfolioapps.officecreationreservation.Office.Office;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Integer> {
    @Query(value = "select * from offices o order by o.office_name asc", nativeQuery = true)
    List<Office> findAllOfficesOrderASC();
}
