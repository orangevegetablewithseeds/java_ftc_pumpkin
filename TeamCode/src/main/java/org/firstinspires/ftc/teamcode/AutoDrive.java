package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AutoDrive {
    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;

    HardwareMap hardwareMap;

    final double turnspeed = 0.3;
    final double TICKS_PER_ROTATION = 537.7;
    final double WHEEL_DIAMETER = 3.85827;
    final double TICKS_PER_INCH = TICKS_PER_ROTATION / (WHEEL_DIAMETER * Math.PI);

    //last resort is preset
    // all of the hardware stored on this object/class
    public AutoDrive (HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        frontLeft = this.hardwareMap.get(DcMotorEx.class, "frontL");
        frontRight = this.hardwareMap.get(DcMotorEx.class, "frontR");
        backLeft = this.hardwareMap.get(DcMotorEx.class, "backL");
        backRight = this.hardwareMap.get(DcMotorEx.class, "backR");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public int inchesToTicks(double inches) {
        return (int) (inches * TICKS_PER_INCH);
    }
    public void forward(int inches, double power) {
        driveInches(inches, power, Drive.FORWARD);
    }
    public void backward(int inches, double power) {
        driveInches(inches, power, Drive.BACKWARD);
    }
    public void right(int inches, double power) {
        driveInches(inches, power, Drive.RIGHT);
    }
    public void left(int inches, double power) {
        driveInches(inches, power, Drive.LEFT);
    }
    public void forwardleft(int inches, double power) {
        driveInches(inches, power, Drive.FORWARDRIGHT);
    }
    public void forwardright(int inches, double power) {
        driveInches(inches, power, Drive.FORWARDRIGHT);
    }
    public void backleft(int inches, double power) {
        driveInches(inches, power, Drive.FORWARDRIGHT);
    }
    public void backright(int inches, double power) {
        driveInches(inches, power, Drive.FORWARDRIGHT);
    }

    public void goToTickPosition(int ticks, double power, Drive direction) {

//        frontLeft.setMo/de(DcMotor.RunMode.STOP_AND_RESET_ENCODER);/

        if (direction == Drive.FORWARD) {
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition()+ticks);
            frontRight.setTargetPosition(frontRight.getCurrentPosition()+ticks);
            backLeft.setTargetPosition(backLeft.getCurrentPosition()+ticks);
            backRight.setTargetPosition(backRight.getCurrentPosition()+ticks);
        } else if (direction == Drive.BACKWARD) {
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition()-ticks);
            frontRight.setTargetPosition(frontRight.getCurrentPosition()-ticks);
            backLeft.setTargetPosition(backLeft.getCurrentPosition()-ticks);
            backRight.setTargetPosition(backRight.getCurrentPosition()-ticks);
        } else if (direction == Drive.RIGHT) {
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition()-ticks);
            frontRight.setTargetPosition(frontRight.getCurrentPosition()+ticks);
            backLeft.setTargetPosition(backLeft.getCurrentPosition()+ticks);
            backRight.setTargetPosition(backRight.getCurrentPosition()-ticks);
        }
        else if (direction == Drive.LEFT) {
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition()+ticks);
            frontRight.setTargetPosition(frontRight.getCurrentPosition()-ticks);
            backLeft.setTargetPosition(backLeft.getCurrentPosition()-ticks);
            backRight.setTargetPosition(backRight.getCurrentPosition()+ticks);
        }
        else if (direction == Drive.FORWARDLEFT) {
            frontLeft.setPower(power * turnspeed);
            backLeft.setPower(power * turnspeed);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + ticks);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() + ticks);
            backLeft.setTargetPosition(backLeft.getCurrentPosition() + ticks);
            backRight.setTargetPosition(backRight.getCurrentPosition() + ticks);
        }
        else if (direction == Drive.FORWARDRIGHT) {
            frontRight.setPower(power * turnspeed);
            backRight.setPower(power * turnspeed);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + ticks);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() + ticks);
            backLeft.setTargetPosition(backLeft.getCurrentPosition() + ticks);
            backRight.setTargetPosition(backRight.getCurrentPosition() + ticks);
        }
        else if (direction == Drive.BACKLEFT) {
            frontLeft.setPower(power * turnspeed);
            backLeft.setPower(power * turnspeed);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - ticks);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() - ticks);
            backLeft.setTargetPosition(backLeft.getCurrentPosition() - ticks);
            backRight.setTargetPosition(backRight.getCurrentPosition() - ticks);
        }
        else if (direction == Drive.BACKRIGHT) {
            frontRight.setPower(power * turnspeed);
            backRight.setPower(power * turnspeed);
            frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - ticks);
            frontRight.setTargetPosition(frontRight.getCurrentPosition() - ticks);
            backLeft.setTargetPosition(backLeft.getCurrentPosition() - ticks);
            backRight.setTargetPosition(backRight.getCurrentPosition() - ticks);
        }
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
        frontRight.setPower(power);

        while (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {} // isBusy will return true until the motor reaches the target position

        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);

    }
    public void driveInches(int inches,double power, Drive direction) {
        goToTickPosition(inchesToTicks(inches), power, direction);
    }

    enum Drive {
        FORWARD,
        BACKWARD,
        RIGHT,
        LEFT,
        FORWARDLEFT,
        FORWARDRIGHT,
        BACKLEFT,
        BACKRIGHT,
    }
}