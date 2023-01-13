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

@TeleOp (name ="SERVOOOOOOOO")
public class testservo extends OpMode {
    CRServo servo;

    @Override
    public void init() { // runs at the beginning, init is short of "initialize"
        // hardwareMap belows to OpMode, so it doesn't need to be defined by the child
        servo = hardwareMap.get(CRServo.class, "servo");
//        clawMA1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    @Override
    public void loop() {
        if (gamepad1.left_bumper) {
            servo.setPower(1);
        }
        else if (gamepad1.right_bumper) {
            servo.setPower(-1);
        }
        else {
            servo.setPower(0);
        }
    }
}