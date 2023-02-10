package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name ="Go Right to Park")
public class Go_Right extends LinearOpMode {

    // the four motors are frontLeft, frontRight, backLeft, backRight
    DcMotor frontLm2;
    DcMotor frontRm2;
    DcMotor backLm2;
    DcMotor backRm2;
    @Override
    public void runOpMode() { // runs at the beginning, init is short of "initialize"
        // hardwareMap belows to OpMode, so it doesn't need to be defined by the child
        frontLm2 = hardwareMap.get(DcMotor.class,    "frontL"); //if it has a m its the motor variable
        frontRm2 = hardwareMap.get(DcMotor.class, "frontR");
        backLm2 = hardwareMap.get(DcMotor.class, "backL");
        backRm2 = hardwareMap.get(DcMotor.class, "backR");

        frontRm2.setDirection(DcMotorSimple.Direction.REVERSE);
        backLm2.setDirection(DcMotorSimple.Direction.REVERSE);
        GoRight(17,.5);
        StopNow(1);
        terminateOpModeNow();
    }

    // all the go and stop code time is in .1 seconds
    // don't have the power be more than 1 or .8 so it wont stress the robot
    // StopNow stops everything not just wheels
    public void GoForward(long time, double power) {
        frontLm2.setPower(power);
        frontRm2.setPower(power);
        backLm2.setPower(power);
        backRm2.setPower(power);
        sleep(time * 100);
    }
    public void GoBack(long time, double power) {
        frontLm2.setPower(-power);
        frontRm2.setPower(-power);
        backLm2.setPower(-power);
        backRm2.setPower(-power);
        sleep(time * 100);
    }
    public void GoLeft(long time, double power) {
        frontLm2.setPower(-power);
        frontRm2.setPower(power);
        backLm2.setPower(power);
        backRm2.setPower(-power);
        sleep(time * 100);
    }
    public void GoRight(long time, double power) {
        frontLm2.setPower(power);
        frontRm2.setPower(-power);
        backLm2.setPower(-power);
        backRm2.setPower(power);
        sleep(time * 100);
    }
    public void TopLeft(long time, double power) {
        frontRm2.setPower(power);
        backLm2.setPower(power);
        sleep(time * 100);
    }
    public void BottomLeft(long time, double power) {
        frontRm2.setPower(-power);
        backLm2.setPower(-power);
        sleep(time * 100);
    }
    public void TopRight(long time, double power) {
        frontLm2.setPower(power);
        backRm2.setPower(power);
        sleep(time * 100);
    }
    public void BottomRight(long time, double power) {
        frontLm2.setPower(-power);
        backRm2.setPower(-power);
        sleep(time * 100);
    }
    public void StopNow(long time) {
        frontLm2.setPower(0);
        frontRm2.setPower(0);
        backLm2.setPower(0);
        backRm2.setPower(0);
        sleep(time * 100);
    }
    public void TurnLeft(long time, double power) {
        frontLm2.setPower(-power);
        frontRm2.setPower(power);
        backLm2.setPower(-power);
        backRm2.setPower(power);
        sleep(time * 100);
    }
    public void TurnRight(long time, double power) {
        frontLm2.setPower(power);
        frontRm2.setPower(-power);
        backLm2.setPower(power);
        backRm2.setPower(-power);
        sleep(time * 100);
    }
}
