package org.energy.repository;

import org.energy.entity.Robot;
import org.energy.repository.custom.RobotCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface RobotRepository extends JpaRepository<Robot, BigInteger>, RobotCustomRepository {

    List<Robot> findByEnterpriseCostCenterId(@Param("enterpriseCostCenterId") BigInteger enterpriseCostCenterId);
}
