package com.resortmanagement.system.fnb.repository;

import com.resortmanagement.system.fnb.entity.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.resortmanagement.system.common.repository.SoftDeleteRepository;
import java.util.List;
import java.util.UUID;

@Repository
public interface MenuRepository extends SoftDeleteRepository<Menu, UUID> {

    @Query("select m from Menu m where m.deletedAt is null")
    List<Menu> findAllActive();

}
