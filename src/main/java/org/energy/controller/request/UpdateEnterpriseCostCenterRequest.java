package org.energy.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UpdateEnterpriseCostCenterRequest {

    private BigInteger enterpriseCostCenterId;
    private List<BigInteger> robots;

}
