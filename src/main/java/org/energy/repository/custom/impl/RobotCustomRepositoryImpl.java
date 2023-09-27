package org.energy.repository.custom.impl;

import lombok.RequiredArgsConstructor;
import org.energy.repository.custom.RobotCustomRepository;
import org.energy.util.GeneralFunction;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
public class RobotCustomRepositoryImpl implements RobotCustomRepository {

    private final EntityManager entityManager;

    @Transactional
    @Override
    public void updateEnterpriseCostCenterId(BigInteger enterpriseCostCenterId, BigInteger robotId) {
        entityManager.createQuery("UPDATE Robot SET enterpriseCostCenterId = :enterpriseCostCenterId WHERE id = :robotId")
                .setParameter("enterpriseCostCenterId", enterpriseCostCenterId)
                .setParameter("robotId", robotId)
                .executeUpdate();
    }

    @Transactional
    @Override
    public void updateEnterpriseCostCenterId(BigInteger enterpriseCostCenterId, List<BigInteger> robots) {
        var sql = "UPDATE Robot SET enterpriseCostCenterId = :enterpriseCostCenterId WHERE %query%";

        sql = GeneralFunction.addFilterIN(sql, robots, "id");

        entityManager.createQuery(sql)
                .setParameter("enterpriseCostCenterId", enterpriseCostCenterId)
                .executeUpdate();
    }
}
