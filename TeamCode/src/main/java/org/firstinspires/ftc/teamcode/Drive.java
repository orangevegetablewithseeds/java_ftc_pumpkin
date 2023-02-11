package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp (name ="Drivable Manual Controls for Human Hand Use")
public class Drive<time1> extends OpMode {

    // the four motors are frontLeft, frontRight, backLeft, backRight
    private DcMotor frontLm1;
    private DcMotor frontRm1;
    private DcMotor backLm1;
    private DcMotor backRm1;
    private DcMotor leftslide;
    private DcMotor rightslide;
    private Servo clawS1;
    private Servo leftarm;
    private Servo rightarm;

    public static final double TICKS_PER_CENTIMETER = 537.7/11.2;
    public static final double CENTIMETERS_PER_TICK = 1/TICKS_PER_CENTIMETER;

    public static final double MAX_LS_HEIGHT = 50;

    private ElapsedTime time;

//    private boolean automate = false;

    /*DcMotor clawML1;
    DcMotor clawMR1;
     */
    // DcMotor clawMA1;
    private final double ground = 0;

    private final double scaleFactor = 1;

    private final double low = 10 * scaleFactor;
    private final double middle = 30 * scaleFactor;
    private final double high = 50 * scaleFactor;
    private double leftarmpos = 0.2;
    private double rightarmpos = 0.8;

    @Override
    public void init() { // runs at the beginning, init is short of "initialize"
        // hardwareMap belows to OpMode, so it doesn't need to be defined by the child
        this.frontLm1 = hardwareMap.get(DcMotor.class, "frontL"); //if it has a m its the motor variable
        this.frontRm1 = hardwareMap.get(DcMotor.class, "frontR");
        this.backLm1 = hardwareMap.get(DcMotor.class, "backL");
        this.backRm1 = hardwareMap.get(DcMotor.class, "backR");
        this.clawS1 = hardwareMap.get(Servo.class, "clawS");
        this.leftarm = hardwareMap.get(Servo.class, "leftarm");
        this.rightarm = hardwareMap.get(Servo.class, "rightarm");
        this.leftslide = hardwareMap.get(DcMotor.class, "leftSlide");
        this.rightslide = hardwareMap.get(DcMotor.class, "rightSlide");

        //below is the linear slide code
        /*clawML1 = hardwareMap.get(DcMotor.class, "clawML");
        clawMR1 = hardwareMap.get(DcMotor.class, "clawMR");
        */
        //    clawMA1 = hardwareMap.get(DcMotor.class,"clawMA");
        this.frontLm1.setDirection(DcMotorSimple.Direction.REVERSE);
        this.backLm1.setDirection(DcMotorSimple.Direction.REVERSE);
        this.leftslide.setDirection(DcMotorSimple.Direction.REVERSE);

        this.leftslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //  rightslide.setDirection(DcMotorSimple.Direction.REVERSE);
        //    clawMA1.setDirection(DcMotorSimple.Direction.REVERSE);

        //     clawMA1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //  clawMA1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        clawMA1.setTargetPosition(0);
//        clawMA1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.time = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
    }

    @Override
    public void loop() {

        //wheels
        double x1 = Math.pow(gamepad1.left_stick_x, 3);
        double y1 = Math.pow(-gamepad1.left_stick_y, 3);
        double l1 = Math.pow(gamepad1.right_stick_x, 3);
        double denominator = Math.max(Math.abs(x1) + Math.abs(l1) + Math.abs(y1), 1.0);
     //   telemetry.addData("wheel velocity", (frontLm1.getCurrentPosition() * TICKS_PER_CENTIMETER)) / time.time(time1));
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

        telemetry.addData("linear slide left position (cm)", leftslide.getCurrentPosition() * CENTIMETERS_PER_TICK);
        telemetry.addData("linear slide right position (cm)", rightslide.getCurrentPosition() * CENTIMETERS_PER_TICK);

            if (gamepad1.y) {
                leftslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rightslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                leftslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                rightslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
            if (gamepad2.a && leftslide.getCurrentPosition() < MAX_LS_HEIGHT * TICKS_PER_CENTIMETER
                    && rightslide.getCurrentPosition() < MAX_LS_HEIGHT * TICKS_PER_CENTIMETER) {
                leftslide.setPower(0.7);
                rightslide.setPower(0.7);
            } else if (gamepad2.y && leftslide.getCurrentPosition() < MAX_LS_HEIGHT * TICKS_PER_CENTIMETER
                    && rightslide.getCurrentPosition() < MAX_LS_HEIGHT * TICKS_PER_CENTIMETER) {
                leftslide.setPower(-0.7);
                rightslide.setPower(-0.7);
            } else {
                leftslide.setPower(0);
                rightslide.setPower(0);
            }

//            if (gamepad2.dpad_left) {
//                this.slidesToHeight(10, 0.7);
//            }
//            if (gamepad2.dpad_up) {
//                this.slidesToHeight(19, 0.7);
//            }

        leftarm.setPosition(leftarmpos);
        rightarm.setPosition(rightarmpos);

        telemetry.addData("leftarmpos", leftarm.getPosition());
        telemetry.addData("rightarmpos", rightarm.getPosition());

        if (gamepad2.b) {
            leftarmpos = 0.6;
            rightarmpos = 0.4;
        }
        if (gamepad2.x) {
            leftarmpos = 0.0;
            rightarmpos = 1.0;
        }

        //claw
        telemetry.addData("servo position", clawS1.getPosition());

        clawS1.setPosition(0.9);

        if (gamepad1.left_bumper) {
            clawS1.setPosition(0.6);
            telemetry.addData("Bumper open", true);
            telemetry.update();
        }
    }

    private void waitUntilTimeElapsed(double timeGoal, boolean emergencyBoolean) {
        time.reset();
        while(time.time() < timeGoal && !emergencyBoolean) telemetry.update(); // continue to log robot position updates
    }

    private void slidesToHeight(double heightCM, double power) {
        rightslide.setTargetPosition((int) (heightCM * TICKS_PER_CENTIMETER));
        leftslide.setTargetPosition((int) (heightCM * TICKS_PER_CENTIMETER));

        rightslide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftslide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightslide.setPower(power);
        leftslide.setPower(power);

//        waitUntilTimeElapsed(100 /* 100s buffer */, (!leftslide.isBusy() && !rightslide.isBusy()));

        while (this.leftslide.isBusy() && this.rightslide.isBusy()) {
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

            telemetry.addData("linear slide left position (cm)", leftslide.getCurrentPosition() * CENTIMETERS_PER_TICK);
            telemetry.addData("linear slide right position (cm)", rightslide.getCurrentPosition() * CENTIMETERS_PER_TICK);
            telemetry.update();
        }

        leftslide.setPower(0);
        rightslide.setPower(0);

//      just in case, I don't remember needing to do this - Vincent
        leftslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}









        //if (gamepad1)
/*        if (gamepad2.y || gamepad2.a) {
            if (gamepad2.y);
            clawMA1.setTargetPosition((int) (armH * TICKS_PER_INCH_ARM));
            clawMA1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            clawMA1.setPower(Math.signum(targetPosition - clawMA1.getCurrentPosition()) * 0.75);*/
/*            while (clawMA1.isBusy()) {
                telemetry.addData("target position", targetPosition);
                telemetry.addData("armH", armH);
                telemetry.addData("current position", clawMA1.getCurrentPosition());
                telemetry.update();
*/   /*         }
        }
        //linear



        //arm

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


































































































