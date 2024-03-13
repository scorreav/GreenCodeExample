package org.energy.controller;

import lombok.RequiredArgsConstructor;
import org.energy.controller.request.UpdateEnterpriseCostCenterRequest;
import org.energy.entity.Robot;
import org.energy.service.RobotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/robot")
public class RobotController {

    private final RobotService robotService;

    @GetMapping("/list")
    public ResponseEntity<List<Robot>> getRobots() {
        return new ResponseEntity<>(robotService.getRobots(), HttpStatus.OK);
    }

    @PutMapping("/update-cost-center")
    public ResponseEntity<Boolean> updateRobots(@RequestBody UpdateEnterpriseCostCenterRequest request) {
        return new ResponseEntity<>(robotService.updateEnterpriseCostCenter(request), HttpStatus.OK);
    }

    @PutMapping("/update-cost-center-optimized")
    public ResponseEntity<Boolean> updateRobotsOptimized(@RequestBody UpdateEnterpriseCostCenterRequest request) {
        return new ResponseEntity<>(robotService.updateEnterpriseCostCenterOptimized(request), HttpStatus.OK);
    }

    @GetMapping("/find-robot")
    public ResponseEntity<List<Robot>> findRobot(@RequestParam("robot-name") String robotName) {
        return new ResponseEntity<>(robotService.findRobot(robotName), HttpStatus.OK);
    }

    @GetMapping("/find-robot-stream")
    public ResponseEntity<List<Robot>> findRobotStream(@RequestParam("robot-name") String robotName) {
        return new ResponseEntity<>(robotService.findRobotStream(robotName), HttpStatus.OK);
    }

    @GetMapping("/find-number")
    public ResponseEntity<String> findNumber(@RequestParam int number) {
        return new ResponseEntity<>(robotService.findNumbers(number), HttpStatus.OK);
    }

    @GetMapping("/find-number-complexity")
    public ResponseEntity<String> findNumberDownComplexity(@RequestParam int number) {
        return new ResponseEntity<>(robotService.findNumbersDownComplexity(number), HttpStatus.OK);
    }

    @GetMapping("/search-robot")
    public ResponseEntity<Robot> findRobotSearch(@RequestParam BigInteger robotId) {
        return new ResponseEntity<>(robotService.findRobotById(robotId), HttpStatus.OK);
    }

    @GetMapping("/search-robot-binary")
    public ResponseEntity<Robot> findRobotBinarySearch(@RequestParam BigInteger robotId) {
        return new ResponseEntity<>(robotService.findRobotByIdBinarySearch(robotId), HttpStatus.OK);
    }

    @GetMapping("/search-robot-binary-recursive")
    public ResponseEntity<Robot> findRobotBinaryRecursive(@RequestParam BigInteger robotId) {
        return new ResponseEntity<>(robotService.findRobotByIdRecursive(robotId), HttpStatus.OK);
    }

    @GetMapping("/fill-robots")
    public ResponseEntity<Boolean> fillRobots() {
        return new ResponseEntity<>(robotService.fillRobots(), HttpStatus.OK);
    }

    @GetMapping("/reduce-robots")
    public ResponseEntity<Long> reduceRobots() {
        return new ResponseEntity<>(robotService.reduceRobots(), HttpStatus.OK);
    }

    @GetMapping("/reduce-robots-stream")
    public ResponseEntity<Long> reduceRobotsStream() {
        return new ResponseEntity<>(robotService.reduceRobotsStream(), HttpStatus.OK);
    }

    @PostMapping("/compare")
    public ResponseEntity<Boolean> utilOptimized(@RequestBody UpdateEnterpriseCostCenterRequest request) {
        return new ResponseEntity<>(robotService.utilOptimized(), HttpStatus.OK);
    }
}
