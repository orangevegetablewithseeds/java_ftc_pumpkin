package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name ="Don't Run 2")
public class Claw extends OpMode {
    CRServo clawS1;
    DcMotor clawM1;

    @Override
    public void init() {
        clawS1 = hardwareMap.get(CRServo.class, "clawS");
        clawM1 = hardwareMap.get(DcMotor.class, "clawM");
    }

    @Override
    public void loop() {

        if (gamepad2.left_bumper == true) {
            clawM1.setPower(1);}
        if (gamepad2.right_bumper == true) {
            clawM1.setPower(-1);}
        if (gamepad2.left_trigger == 1) {
            clawS1.setPower(1);}
        if (gamepad2.right_trigger == 1) {
            clawS1.setPower(-1);
        }




    }

}