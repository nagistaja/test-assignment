package org.helmes.task.repository;

import org.helmes.task.model.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {

  @Query("SELECT s FROM Sector s WHERE s.parent IS NULL")
  List<Sector> findAllTopLevelSectors();

  List<Sector> findByParentId(Long parentId);
}