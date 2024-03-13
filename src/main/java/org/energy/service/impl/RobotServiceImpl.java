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
import java.util.*;
import java.util.stream.Stream;

@Service
public class RobotServiceImpl implements RobotService {

    private final RobotRepository robotRepository;
    private List<Robot> robotList;

    public RobotServiceImpl (RobotRepository robotRepository) {
        this.robotRepository = robotRepository;
    }

    @Override
    public List<Robot> getRobots() {
        return robotRepository.findAll();
    }

    @Override
    public boolean updateEnterpriseCostCenter(UpdateEnterpriseCostCenterRequest request) {
        robotRepository.findByEnterpriseCostCenterId(request.getEnterpriseCostCenterId())
                .forEach(robot -> {
                    if (!request.getRobots().contains(robot.getId())) {
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
    public boolean fillRobots() {
        if(robotList == null) {
            robotList = robotRepository.findAll();
        }
        return true;
    }

    @Override
    public List<Robot> findRobot(String nameToFind) {
        List<Robot> rta = new ArrayList<>();
        for (Robot robot : robotList) {
            if (robot.getName().contains(nameToFind)) {
                rta.add(robot);
            }
        }

        return rta;
    }

    @Override
    public List<Robot> findRobotStream(String nameToFind) {
        return robotList
                .stream()
                .parallel()
                .filter(robot -> robot.getName().contains(nameToFind))
                .toList();
    }

    @Override
    public long reduceRobots() {
        long rta = 0L;

        for (Robot robot: robotList) {
            rta += robot.getId().longValue();
        }

        return rta;
    }

    @Override
    public long reduceRobotsStream() {
        return robotList
                .stream()
                .parallel()
                .reduce(0L, (accumulator, robot) -> accumulator + robot.getId().longValue(), Long::sum);
    }

    @Override
    public String findNumbers(int number) {
        List<Integer> numbers = robotList.stream().flatMap(robot -> Stream.of(robot.getId().intValue())).toList();

        for (int a : numbers) {
            int diff = number - a;
            for (int b : numbers) {
                if (b != a && b == diff) {
                    return Arrays.toString(new int[]{a, b});
                }
            }
        }
        return Arrays.toString(new int[]{0, 0});
    }

    @Override
    public String findNumbersDownComplexity(int number) {
        List<Integer> numbers = robotList.stream().flatMap(robot -> Stream.of(robot.getId().intValue())).toList();
        Set<Integer> set = new HashSet<>();

        for (int a : numbers) {
            int diff = number - a;

            if (set.contains(diff))
                return Arrays.toString(new int[]{a, diff});
            else
                set.add(a);
        }
        return Arrays.toString(new int[]{0, 0});
    }

    @Override
    public Robot findRobotById(BigInteger id) {
        Robot rta = null;
        boolean found = false;

        for(int i = 0; i < robotList.size() && !found; i++) {
            var robot = robotList.get(i);
            if(robot.getId().compareTo(id) == 0) {
                rta = robot;
                found = true;
            }
        }
        return rta;
    }

    @Override
    public Robot findRobotByIdBinarySearch(BigInteger id) {
        int min = 0;
        int max = robotList.size();
        Robot rta = null;

        while (min <= max && rta == null) {
            var prom = (min + max) / 2;
            var robot = robotList.get(prom);
            if (id.compareTo(robot.getId()) == 0)
                rta = robot;
            else if (id.compareTo(robot.getId()) < 0)
                max = prom - 1;
            else
                min = prom + 1;
        }
        return rta;
    }

    @Override
    public Robot findRobotByIdRecursive(BigInteger id) {
        return GeneralFunction.binarySearchRecursive(robotList, 0, robotList.size(), id);
    }

    @Override
    public boolean utilOptimized() {
        //MathOperation.compareGeneratePassword();
        MathOperation.compareSum();
        //MathOperation.sum(BigDecimal.valueOf(1000000000));
        //MathOperation.sumNew(BigDecimal.valueOf(1000000000));
        return true;
    }
}
