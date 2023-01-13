package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Scanning.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.ArrayList;

@Autonomous(name ="Don't Run 3")
public class Auto extends LinearOpMode {

    // the four motors are frontLeft, frontRight, backLeft, backRight
    DcMotor frontLm2;
    DcMotor frontRm2;
    DcMotor backLm2;
    DcMotor backRm2;
    CRServo clawS2;
    /*DcMotor clawML2;
    DcMotor clawMR2;
     */
    DcMotor clawMA2;

    @Override
    public void runOpMode() { // runs at the beginning, init is short of "initialize"
        // hardwareMap belows to OpMode, so it doesn't need to be defined by the child
        frontLm2 = hardwareMap.get(DcMotor.class, "frontL"); //if it has a m its the motor variable
        frontRm2 = hardwareMap.get(DcMotor.class, "frontR");
        backLm2 = hardwareMap.get(DcMotor.class, "backL");
        backRm2 = hardwareMap.get(DcMotor.class, "backR");
        clawS2 = hardwareMap.get(CRServo.class,"clawS");
        /*
        clawML2 = hardwareMap.get(DcMotor.class,"clawML");
        clawMR2 = hardwareMap.get(DcMotor.class,"clawMR");
         */
        clawMA2 = hardwareMap.get(DcMotor.class,"clawMA");

        frontRm2.setDirection(DcMotorSimple.Direction.REVERSE);
        backLm2.setDirection(DcMotorSimple.Direction.REVERSE);


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
        /*clawS2.setPower(0);
        clawML2.setPower(0);
        clawMR2.setPower(0);
        clawMA2.setPower(0);*/
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
    public void ClawOpen(long time, double power) {
        clawS2.setPower(power);
        sleep(time * 100);
    }
    public void ClawClose(long time, double power) {
        clawS2.setPower(-power);
        sleep(time * 100);
    }
    /*
    public void LinearUp(long time, double power) {
        clawML2.setPower(power);
        clawMR2.setPower(power);
        sleep(time * 100);
    }
    public void LinearDown(long time, double power) {
        clawML2.setPower(-power);
        clawMR2.setPower(-power);
        sleep(time * 100);
    }
    */
    public void ArmUp(long time, double power) {
        clawMA2.setPower(power);
        sleep(time * 100);
    }
    public void ArmDown(long time, double power) {
        clawMA2.setPower(-power);
        sleep(time * 100);
    }
}