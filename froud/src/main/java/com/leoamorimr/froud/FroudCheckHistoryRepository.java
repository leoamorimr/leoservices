package com.leoamorimr.froud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FroudCheckHistoryRepository extends JpaRepository<FroudCheckHistory, Integer> {
}
