package org.firstinspires.ftc.teamcode;
// left arm - down is 0.0 and up and 0.6--
// rught arm - down is 1.0 or 0.9 and up is 0.4
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

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp (name ="servo pos")
public class alservopos extends OpMode {
    Servo servo;

    @Override
    public void init() { // runs at the beginning, init is short of "initialize"
        // hardwareMap belows to OpMode, so it doesn't need to be defined by the child
        servo = hardwareMap.get(Servo.class, "servo");
//        clawMA1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void loop() {
        telemetry.addData("servo position", servo.getPosition());

        if (gamepad1.left_bumper) {
            servo.setPosition(0.0);
        }
        if (gamepad1.right_bumper) {
            servo.setPosition(0.1);
        }
        if (gamepad1.dpad_up) {
            servo.setPosition(0.4);
        }
        if (gamepad1.dpad_down) {
            servo.setPosition(0.5);
        }
        if (gamepad1.y) {
            servo.setPosition(0.6);
        }
        if (gamepad1.x) {
            servo.setPosition(0.7);
        }
        if (gamepad1.b) {
            servo.setPosition(0.8);

        }
        if (gamepad1.a) {
            servo.setPosition(0.9);
        }
        if (gamepad1.dpad_left) {
            servo.setPosition(1.0);
        }

    }
}