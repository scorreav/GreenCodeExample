package org.energy.service;

import org.energy.controller.request.UpdateEnterpriseCostCenterRequest;
import org.energy.entity.Robot;

import java.util.List;
import java.util.Optional;

public interface RobotService {

    List<Robot> getRobots();

    boolean updateEnterpriseCostCenter(UpdateEnterpriseCostCenterRequest request);

    boolean updateEnterpriseCostCenterOptimized(UpdateEnterpriseCostCenterRequest request);

    List<Robot> findRobot(String nameToFind);

    List<Robot> findRobotStream(String nameToFind);

    String findNumbers(int number);

    String findNumbersDownComplexity(int number);

    boolean utilOptimized();
}