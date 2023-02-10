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

@TeleOp (name ="TEST THESE DAMN MOTORS!!!")
public class testinganymotor extends OpMode {
    DcMotor themotor;

    @Override
    public void init() { // runs at the beginning, init is short of "initialize"
        // hardwareMap belows to OpMode, so it doesn't need to be defined by the child
        themotor = hardwareMap.get(DcMotor.class, "motor");
//        clawMA1.setTargetPosition(0);
//        clawMA1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    @Override
    public void loop() {
        telemetry.addData("current motor location", themotor.getCurrentPosition());

        if (gamepad1.left_bumper) {
            themotor.setPower(0.25);
        }
        else if (gamepad1.right_bumper) {
            themotor.setPower(-0.25);
        }
        else {
            themotor.setPower(0);
        }
    }
}