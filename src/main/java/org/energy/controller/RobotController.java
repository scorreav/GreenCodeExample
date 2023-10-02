package org.energy.controller;

import lombok.RequiredArgsConstructor;
import org.energy.controller.request.UpdateEnterpriseCostCenterRequest;
import org.energy.entity.Robot;
import org.energy.service.RobotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/compare")
    public ResponseEntity<Boolean> utilOptimized(@RequestBody UpdateEnterpriseCostCenterRequest request) {
        return new ResponseEntity<>(robotService.utilOptimized(), HttpStatus.OK);
    }
}
