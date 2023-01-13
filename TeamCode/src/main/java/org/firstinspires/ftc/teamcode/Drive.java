package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.encode.TICKS_PER_INCH_ARM;
import static org.firstinspires.ftc.teamcode.encode.TICKS_PER_MOTOR_ROTATION;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name ="Drivable Manual Controls for Human Hand Use")
public class Drive extends OpMode {

    // the four motors are frontLeft, frontRight, backLeft, backRight
    DcMotor frontLm1;
    DcMotor frontRm1;
    DcMotor backLm1;
    DcMotor backRm1;
    DcMotor leftslidem1;
    DcMotor rightslidem1;
    Servo clawS1;
    Servo leftarmS1;
    Servo rightarmS1;

    /*DcMotor clawML1;
    DcMotor clawMR1;
     */
    // DcMotor clawMA1;
    double ground = 0;

    double scaleFactor = 1;

    double low = 10 * scaleFactor;
    double middle = 30 * scaleFactor;
    double high = 50 * scaleFactor;
    double armH = 0;

    @Override
    public void init() { // runs at the beginning, init is short of "initialize"
        // hardwareMap belows to OpMode, so it doesn't need to be defined by the child
        frontLm1 = hardwareMap.get(DcMotor.class, "frontL"); //if it has a m its the motor variable
        frontRm1 = hardwareMap.get(DcMotor.class, "frontR");
        backLm1 = hardwareMap.get(DcMotor.class, "backL");
        backRm1 = hardwareMap.get(DcMotor.class, "backR");
        clawS1 = hardwareMap.get(Servo.class, "clawS");
        //below is the linear slide code
        /*clawML1 = hardwareMap.get(DcMotor.class, "clawML");
        clawMR1 = hardwareMap.get(DcMotor.class, "clawMR");
        */
        //    clawMA1 = hardwareMap.get(DcMotor.class,"clawMA");
        frontLm1.setDirection(DcMotorSimple.Direction.REVERSE);
        backLm1.setDirection(DcMotorSimple.Direction.REVERSE);
        //    clawMA1.setDirection(DcMotorSimple.Direction.REVERSE);

        //     clawMA1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //  clawMA1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        clawMA1.setTargetPosition(0);
//        clawMA1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void loop() {

        double x1 = Math.pow(gamepad1.left_stick_x, 3);
        double y1 = Math.pow(-gamepad1.left_stick_y, 3);
        double l1 = Math.pow(gamepad1.right_stick_x, 3);
        double denominator = Math.max(Math.abs(x1) + Math.abs(l1) + Math.abs(y1), 1.0);

        if (gamepad1.left_trigger == 1) {
            frontLm1.setPower(((y1 + x1 + l1) / denominator) * .25);
            frontRm1.setPower(((y1 - x1 - l1) / denominator) * .25);
            backLm1.setPower(((y1 - x1 + l1) / denominator) * .25);
            backRm1.setPower(((y1 + x1 - l1) / denominator) * .25);
        } else {
            frontLm1.setPower((y1 + x1 + l1) / denominator * .8);
            frontRm1.setPower((y1 - x1 - l1) / denominator * .8);
            backLm1.setPower((y1 - x1 + l1) / denominator * .8);
            backRm1.setPower((y1 + x1 - l1) / denominator * .8);
        }

        telemetry.addData("servo position", clawS1.getPosition());

        if (gamepad2.left_bumper) {
            clawS1.setPosition(0.8);
            telemetry.addData("Bumper closed", true);
            telemetry.update();

        }
        if (gamepad2.right_bumper) {
            clawS1.setPosition(0.6);
            telemetry.addData("Bumper open", true);
            telemetry.update();
        }
        if (gamepad2.y || gamepad2.a) {
            if (gamepad2.y);

        }

//archived arm code
/*        if (gamepad2.a || gamepad2.b || gamepad2.x || gamepad2.y) {
            if (gamepad2.a)
                armH = ground;
            else if (gamepad2.b)
                armH = low;
            else if (gamepad2.x)
                armH = middle;
            else if (gamepad2.y)
                armH = high;

            int targetPosition = (int) (armH * TICKS_PER_INCH_ARM) ;

            clawMA1.setTargetPosition((int) (armH * TICKS_PER_INCH_ARM));
            clawMA1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            clawMA1.setPower(Math.signum(targetPosition - clawMA1.getCurrentPosition()) * 0.75);
/*            while (clawMA1.isBusy()) {
                telemetry.addData("target position", targetPosition);
                telemetry.addData("armH", armH);
                telemetry.addData("current position", clawMA1.getCurrentPosition());
                telemetry.update();
*/   /*         }
            clawMA1.setPower(0);
        if (gamepad2.left_bumper) { // close claw (try this)
            clawS1.setPower(-0.5); // test this
        }
        else if (gamepad2.right_bumper) { // open claw
            clawS1.setPower(0.5); // test these values
        }
        else {
            clawS1.setPower(0);
        }



        }
*/
        // below is old arm code
/*
        if (gamepad2.left_trigger >  0 && gamepad2.right_trigger > 0) {
            clawMA1.setPower(0);
        }
        else { // either only one of them is positive, or none of them
            if (gamepad2.right_trigger > 0) {
                clawMA1.setPower(0.35);
            }
            else if (gamepad2.left_trigger > 0) {
                clawMA1.setPower(-0.35);
            }
            else { // none of them are positive
                clawMA1.setPower(0);
            }
        }
*/

        //Below is linear slide code
        /*
        if (gamepad2.left_bumper == true) {
            clawML1.setPower(.8);
            clawMR1.setPower(.8);
        }
        if (gamepad2.right_bumper == true) {
            clawML1.setPower(-.8);
            clawMR1.setPower(-.8);
        }
        if (gamepad2.left_bumper == false && gamepad2.right_bumper == false) {
            clawML1.setPower(0);
            clawMR1.setPower(0);
        }
        */
        /*if (gamepad2.left_bumper) {
            clawS1.setPower(0.5);
            if (gamepad2.right_bumper) {
                clawS1.setPower(-0.5);
            }
            else{
                    clawS1.setPower(0);
                }
        }*/

    }
}


































































































