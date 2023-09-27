package org.energy.repository.custom;

import java.math.BigInteger;
import java.util.List;

public interface RobotCustomRepository {

    void updateEnterpriseCostCenterId(BigInteger enterpriseCostCenterId, BigInteger robotId);
    void updateEnterpriseCostCenterId(BigInteger enterpriseCostCenterId, List<BigInteger> robotId);
}
