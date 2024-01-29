package org.energy.controller;

import lombok.RequiredArgsConstructor;
import org.energy.util.MathOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/math")
public class MathOperationController {

    @GetMapping("/generate-password")
    public ResponseEntity<String> generatePassword(@RequestParam int length) {
        return ResponseEntity.ok(MathOperation.generatePassword(length));
    }

    @GetMapping("/generate-password-optimized")
    public ResponseEntity<String> generatePasswordNew(@RequestParam int length) {
        return ResponseEntity.ok(MathOperation.generatePasswordNew1(length));
    }

    @GetMapping("/sum")
    public ResponseEntity<BigDecimal> sum(@RequestParam BigDecimal... num) {
        return ResponseEntity.ok(MathOperation.sum(num));
    }

    @GetMapping("/sum-optimized")
    public ResponseEntity<BigDecimal> sumNew(@RequestParam BigDecimal... num) {
        return ResponseEntity.ok(MathOperation.sumNew(num));
    }
}
