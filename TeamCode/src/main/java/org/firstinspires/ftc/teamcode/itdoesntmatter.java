package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class itdoesntmatter extends OpMode {

    // the four motors are frontLeft, frontRight, backLeft, backRight
    // what's teleop???????????!??!?!?!!?
    DcMotor frontL;
    DcMotor frontR;
    DcMotor backL;
    DcMotor backR;



    @Override
    public void init() { // runs at the beginning, init is short of "initialize"
        // hardwareMap belows to OpMode, so it doesn't need to be defined by the child
        frontL = hardwareMap.get(DcMotor.class, "frontL");
        frontR = hardwareMap.get(DcMotor.class, "frontR");
        backL = hardwareMap.get(DcMotor.class, "backL");
        backR = hardwareMap.get(DcMotor.class, "backR");
        frontR.setDirection(DcMotor.Direction.REVERSE);
        backR.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double r = gamepad1.right_stick_x;

        frontL.setPower(y+r+x); // front motors have same sign
        frontR.setPower(y-r-x);
        backL.setPower(y+r-x); // back motors have opposite
        backR.setPower(y-r+x);

    }
}
