package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name ="Don't Run 1")
public class Wheels extends OpMode {

    // the four motors are frontLeft, frontRight, backLeft, backRight
    DcMotor frontLm1;
    DcMotor frontRm1;
    DcMotor backLm1;
    DcMotor backRm1;

    @Override
    public void init() { // runs at the beginning, init is short of "initialize"
        // hardwareMap belows to OpMode, so it doesn't need to be defined by the child
        frontLm1 = hardwareMap.get(DcMotor.class, "frontL"); //if it has a m its the motor variable
        frontRm1 = hardwareMap.get(DcMotor.class, "frontR");
        backLm1 = hardwareMap.get(DcMotor.class, "backL");
        backRm1 = hardwareMap.get(DcMotor.class, "backR");

        frontRm1.setDirection(DcMotorSimple.Direction.REVERSE);
        backLm1.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {

        double x1 = Math.pow(gamepad1.left_stick_x, 3);
        double y1 = Math.pow(-gamepad1.left_stick_y, 3);
        double l1 = Math.pow(gamepad1.right_stick_x, 3);
        double denominator = Math.max(Math.abs(x1) + Math.abs(l1) + Math.abs(y1), 1.0);

        if (gamepad1.right_bumper == true) {
            frontLm1.setPower(((y1 + x1 + l1) / denominator)*.25);
            frontRm1.setPower(((y1 - x1 - l1) / denominator)*.25);
            backLm1.setPower(((y1 - x1 + l1) / denominator)*.25);
            backRm1.setPower(((y1 + x1 - l1) / denominator)*.25);}
        else {
            frontLm1.setPower((y1 + x1 + l1) / denominator*.8);
            frontRm1.setPower((y1 - x1 - l1) / denominator*.8);
            backLm1.setPower((y1 - x1 + l1) / denominator*.8);
            backRm1.setPower((y1 + x1 - l1) / denominator*.8);
        }
    }
}