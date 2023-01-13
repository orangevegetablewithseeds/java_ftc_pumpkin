package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
//This is an old claw code we made when we didn't have a claw or an arm to begin with(theoretical code)
@TeleOp (name ="Don't Run 2")
public class Claw extends OpMode {
    CRServo clawS1;
    DcMotor clawML1;
    DcMotor clawMR1;
    DcMotor clawMA1;

@Override
    public void init() {
        clawS1 = hardwareMap.get(CRServo.class, "clawS"); // servo code for the claw
        clawML1 = hardwareMap.get(DcMotor.class, "clawML"); // left linear slide code
        clawMR1 = hardwareMap.get(DcMotor.class, "clawMR"); // right linear slide code
        clawMA1 = hardwareMap.get(DcMotor.class, "clawMA"); // code to rotate the arm
    }

    @Override
    public void loop() {

        if (gamepad2.left_bumper == true) {
            clawML1.setPower(1);
            clawMR1.setPower(1);
        }
        if (gamepad2.right_bumper == true) {
            clawML1.setPower(-1);
            clawMR1.setPower(-1);
        }
        if (gamepad2.left_bumper == false && gamepad2.right_bumper == false) {
            clawML1.setPower(0);
            clawMR1.setPower(0);
        }
        if (gamepad2.left_trigger == 1) {
            clawS1.setPower(1);
        }
        if (gamepad2.right_trigger == 1) {
            clawS1.setPower(-1);
        }
        if (gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0) {
            clawS1.setPower(0);
        }
        if (gamepad2.a == true) {
            clawMA1.setPower(1);
        }
        if (gamepad2.b == true) {
            clawMA1.setPower(-1);
        }
        if (gamepad2.a == false && gamepad2.b == false) {
            clawMA1.setPower(0);
        }
    }
}