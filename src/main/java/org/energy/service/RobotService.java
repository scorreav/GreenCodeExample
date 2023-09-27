package org.energy.service;

import org.energy.controller.request.UpdateEnterpriseCostCenterRequest;
import org.energy.entity.Robot;

import java.util.List;

public interface RobotService {

    List<Robot> getRobots();

    boolean updateEnterpriseCostCenter(UpdateEnterpriseCostCenterRequest request);

    boolean updateEnterpriseCostCenterOptimized(UpdateEnterpriseCostCenterRequest request);
}
