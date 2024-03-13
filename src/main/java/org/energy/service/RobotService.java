package org.energy.service;

import org.energy.controller.request.UpdateEnterpriseCostCenterRequest;
import org.energy.entity.Robot;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface RobotService {

    List<Robot> getRobots();

    boolean updateEnterpriseCostCenter(UpdateEnterpriseCostCenterRequest request);

    boolean updateEnterpriseCostCenterOptimized(UpdateEnterpriseCostCenterRequest request);

    boolean fillRobots();

    List<Robot> findRobot(String nameToFind);

    List<Robot> findRobotStream(String nameToFind);

    long reduceRobots();

    long reduceRobotsStream();

    String findNumbers(int number);

    String findNumbersDownComplexity(int number);

    Robot findRobotById(BigInteger id);

    Robot findRobotByIdBinarySearch(BigInteger id);

    Robot findRobotByIdRecursive(BigInteger id);

    boolean utilOptimized();
}