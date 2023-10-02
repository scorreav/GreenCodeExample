package org.energy.service.impl;

import lombok.RequiredArgsConstructor;
import org.energy.controller.request.UpdateEnterpriseCostCenterRequest;
import org.energy.entity.Robot;
import org.energy.repository.RobotRepository;
import org.energy.service.RobotService;
import org.energy.util.GeneralFunction;
import org.energy.util.MathOperation;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RobotServiceImpl implements RobotService {

    private final RobotRepository robotRepository;

    @Override
    public List<Robot> getRobots() {
        return robotRepository.findAll();
    }

    @Override
    public boolean updateEnterpriseCostCenter(UpdateEnterpriseCostCenterRequest request) {
        robotRepository.findByEnterpriseCostCenterId(request.getEnterpriseCostCenterId())
                .forEach(robot -> {
                    if(!request.getRobots().contains(robot.getId())) {
                        request.getRobots().remove(robot.getId());
                        robotRepository.updateEnterpriseCostCenterId(null, robot.getId());
                    }
                });

        request.getRobots()
                .forEach(bigInteger -> robotRepository.updateEnterpriseCostCenterId(request.getEnterpriseCostCenterId(), bigInteger));

        return true;
    }

    @Override
    public boolean updateEnterpriseCostCenterOptimized(UpdateEnterpriseCostCenterRequest request) {

        List<Robot> oldList = robotRepository.findByEnterpriseCostCenterId(request.getEnterpriseCostCenterId());
        List<BigInteger>[] rta = GeneralFunction.compareList(oldList.stream().map(Robot::getId).toList(), request.getRobots());

        if (!rta[4].isEmpty())
            robotRepository.updateEnterpriseCostCenterId(null, rta[4]);
        if (!rta[3].isEmpty())
            robotRepository.updateEnterpriseCostCenterId(request.getEnterpriseCostCenterId(), rta[3]);

        return true;
    }

    @Override
    public boolean utilFuctionOptimized(){
        MathOperation.compareGeneratePassword();
        return true;
    }

}
