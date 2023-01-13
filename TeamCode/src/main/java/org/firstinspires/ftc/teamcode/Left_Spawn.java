package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.encode.TICKS_PER_INCH_ARM;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name ="Blue Polar Opposite of Direction Right Location Spawn")
public class Left_Spawn extends LinearOpMode {

    // the four motors are frontLeft, frontRight, backLeft, backRight
    DcMotor frontLm2;
    DcMotor frontRm2;
    DcMotor backLm2;
    DcMotor backRm2;
    // Servo clawS2;
    /*
    DcMotor clawML2;
    DcMotor clawMR2;
    */
 //   DcMotor clawMA2;
    double scaleFactor = 1;
    double armH = 0;
    double low = 10;
    double middle = 30 * scaleFactor;
    double high = 50 * scaleFactor;
    double ground = 0;

    @Override
    public void runOpMode() { //runs at the beginning, init is short of "initialize"
        // hardwareMap belows to OpMode, so it doesn't need to be defined by the child
        frontLm2 = hardwareMap.get(DcMotor.class, "frontL"); //if it has a m its the motor variable
        frontRm2 = hardwareMap.get(DcMotor.class, "frontR");
        backLm2 = hardwareMap.get(DcMotor.class, "backL");
        backRm2 = hardwareMap.get(DcMotor.class, "backR");
        // clawS2 = hardwareMap.get(Servo.class,"clawS");
        /*
        clawML2 = hardwareMap.get(DcMotor.class,"clawML");
        clawMR2 = hardwareMap.get(DcMotor.class,"clawMR");
        */
     //   clawMA2 = hardwareMap.get(DcMotor.class, "clawMA");

        frontLm2.setDirection(DcMotorSimple.Direction.REVERSE);
        backLm2.setDirection(DcMotorSimple.Direction.REVERSE);

  /*      clawMA2.setDirection(DcMotorSimple.Direction.REVERSE);

        clawMA2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawMA2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); */

        waitForStart();
        GoForward(12,0.5);
        ForwardRight(10, 0.5);
        GoForward(2,0.5);
        //put claw + lienar slide code here
        StopNow(5);
        GoBack(3,0.5);
        StopNow(3);
        TurnLeft(15,0.5);
        GoForward(8,0.5);

        StopNow(5);
        terminateOpModeNow();
    }

    // all the go and stop code time is in .1 seconds
    // don't have the power be more than 1
    public void ForwardRight(long time, double power) {
        frontLm2.setPower(power);
        frontRm2.setPower(0.3 * power);
        backLm2.setPower(power);
        backRm2.setPower(0.3 * power);
        sleep(time * 100);
    }

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
        frontRm2.setPower(power);
        backLm2.setPower(power);
        sleep(time * 100);
    }

    public void BottomRight(long time, double power) {
        frontRm2.setPower(-power);
        backLm2.setPower(-power);
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
/*    public void ClawOpen(long time, double power) {
        clawS2.setPosition(0); // test this
        sleep(time * 100);
    }
    public void ClawClose(long time, double power) {
        clawS2.setPosition(0.5);
        sleep(time * 100);
    }*/
    /*
    public void LinearUp(long time, double power) {
        clawM2.setPower(power);
        //clawML2.setPower(power);
        //clawMR2.setPower(power);
        sleep(time * 100);
    }
    public void LinearDown(long time, double power) {
        clawM2.setPower(-power);
        //clawML2.setPower(-power);
        //clawMR2.setPower(-power);
        sleep(time * 100);
    }
    */
/*
    public void Ground(long time, double power) {
        armH = ground;
        int targetPosition = (int) (armH * TICKS_PER_INCH_ARM) ;

        clawMA2.setTargetPosition((int) (armH * TICKS_PER_INCH_ARM));
        clawMA2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        clawMA2.setPower(Math.signum(targetPosition - clawMA2.getCurrentPosition()) * 0.75);
        sleep( time * 100);
    }
    public void Low(long time, double power) {
        armH = low;
        int targetPosition = (int) (armH * TICKS_PER_INCH_ARM) ;

        clawMA2.setTargetPosition((int) (armH * TICKS_PER_INCH_ARM));
        clawMA2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        clawMA2.setPower(Math.signum(targetPosition - clawMA2.getCurrentPosition()) * 0.75);
        sleep( time * 100);
    }
    public void Middle(long time, double power) {
        armH = middle;
        int targetPosition = (int) (armH * TICKS_PER_INCH_ARM) ;

        clawMA2.setTargetPosition((int) (armH * TICKS_PER_INCH_ARM));
        clawMA2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        clawMA2.setPower(Math.signum(targetPosition - clawMA2.getCurrentPosition()) * 0.75);
        sleep( time * 100);
    }
    public void High(long time, double power) {
        armH = high;
        int targetPosition = (int) (armH * TICKS_PER_INCH_ARM) ;

        clawMA2.setTargetPosition((int) (armH * TICKS_PER_INCH_ARM));
        clawMA2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        clawMA2.setPower(Math.signum(targetPosition - clawMA2.getCurrentPosition()) * 0.75);
        sleep( time * 100);
    }

}*/
/*
    int targetPosition = (int) (armH * TICKS_PER_INCH_ARM) ;

            clawMA1.setTargetPosition((int) (armH * TICKS_PER_INCH_ARM));
                    clawMA1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    clawMA1.setPower(Math.signum(targetPosition - clawMA1.getCurrentPosition()) * 0.75);
*/
    /* If spawn is right
    GoLeft(5, .8);
    GoForward(5, .8);
    ClawUp(3, .8);
    ClawOpen(1, .3);
    GoBack(5, .8);
    GoRight(5, .8);
    terminateOpModeNow();
     */