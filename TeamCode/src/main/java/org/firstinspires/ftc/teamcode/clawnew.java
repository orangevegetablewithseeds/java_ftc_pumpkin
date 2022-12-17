package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "tes claw")
public class clawnew extends OpMode {
    DcMotor clawM3;
    @Override
    public void init() {
        clawM3 = hardwareMap.get(DcMotor.class, "clawM");
    }
    @Override
    public void loop() {
        if (gamepad2.left_bumper) {
            clawM3.setPower(-1);
        }
        if (gamepad2.right_bumper) {
            clawM3.setPower(1);
        }
        if (!gamepad2.left_bumper && !gamepad1.right_bumper) {
            clawM3.setPower(0);
        }
    }
}