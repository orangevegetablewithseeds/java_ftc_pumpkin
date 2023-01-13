package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name ="TESTCLAW")
public class TestClaw extends OpMode{

    CRServo clawS1;

    @Override
    public void init() { // runs at the beginning, init is short of "initialize"
        clawS1 = hardwareMap.get(CRServo.class, "clawS");
    }
    @Override
    public void loop() {
        if (gamepad2.left_bumper && gamepad2.right_bumper) {
            clawS1.setPower(0);
        }
          else if (gamepad2.left_bumper) { // close claw (try this)
            clawS1.setPower(-0.5); // test this
        } else if (gamepad2.right_bumper) { // open claw
            clawS1.setPower(0.5); // test these values
        } else {
            clawS1.setPower(0);
        }
    }
}
