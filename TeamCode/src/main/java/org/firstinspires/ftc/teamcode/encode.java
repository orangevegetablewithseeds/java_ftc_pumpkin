package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class encode {

    DcMotorEx clawMA3;

    static final double TICKS_PER_MOTOR_ROTATION = 537.7;
    static final double GEAR_REDUCTION = 0.2;
    static final double ARM_DIAMETER_INCHES = 1.404;
    static final double TICKS_PER_INCH_ARM = (TICKS_PER_MOTOR_ROTATION * GEAR_REDUCTION) / (ARM_DIAMETER_INCHES * Math.PI);

    public encode (HardwareMap hardwareMap){
        clawMA3 = hardwareMap.get(DcMotorEx.class, "clawMA");

        clawMA3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void ArmUp(double velocity, int distanceInches) {
        clawMA3.setTargetPosition((int)(distanceInches * TICKS_PER_INCH_ARM));
        clawMA3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        clawMA3.setVelocity(velocity);
    }

}
