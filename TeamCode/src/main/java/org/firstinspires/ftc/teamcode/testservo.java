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

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp (name ="SERVOOOOOOOO")
public class testservo extends OpMode {
    Servo servo;

    @Override
    public void init() { // runs at the beginning, init is short of "initialize"
        // hardwareMap belows to OpMode, so it doesn't need to be defined by the child
        servo = hardwareMap.get(Servo.class, "servo");
//        clawMA1.setMode(DcMotor.RunMode.RUN c                     _TO_POSITION);
    }
    @Override
    public void loop() {
        telemetry.addData("servo position", servo.getPosition());

        if (gamepad1.left_bumper) {
            servo.setPosition(0.8);
            telemetry.addData("Bumper closed", true);
            telemetry.update();

        }
        if (gamepad1.right_bumper) {
            servo.setPosition(0.6);
            telemetry.addData("Bumper open", true);
            telemetry.update();

        }
    }

//

}