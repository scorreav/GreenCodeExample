package org.energy.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;

@Data
@Entity
@Table(name = "ROBOT")
public class Robot {

    @Id
    @Column(name = "ID")
    private BigInteger id;

    @Column(name = "ROBOT_NAME")
    private String name;

    @Column(name = "ENTERPRISE_COST_CENTER_ID")
    private BigInteger enterpriseCostCenterId;
}
