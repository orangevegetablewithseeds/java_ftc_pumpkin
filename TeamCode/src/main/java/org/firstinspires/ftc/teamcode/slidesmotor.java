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
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp (name ="slidesmotoor")
public class slidesmotor extends OpMode {
    DcMotor leftslide;
    DcMotor rightslide;

    ElapsedTime time;
    public static double TICKS_PER_CENTIMETER = 537.7 / 11.2;
    public static double CENTIMETERS_PER_TICK = 1 / TICKS_PER_CENTIMETER;

    public static double MAX_LS_HEIGHT = 10;

    @Override
    public void init() { // runs at the beginning, init is short of "initialize"
        // hardwareMap belows to OpMode, so it doesn't need to be defined by the child
        leftslide = hardwareMap.get(DcMotor.class, "leftslide");
        rightslide = hardwareMap.get(DcMotor.class, "rightsilde");

        rightslide.setDirection(DcMotorSimple.Direction.REVERSE);

//        clawMA1.setTargetPosition(0);
//        clawMA1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void loop() {
        telemetry.addData("linear slide left position (cm)", leftslide.getCurrentPosition() * CENTIMETERS_PER_TICK);
        telemetry.addData("linear slide right position (cm)", rightslide.getCurrentPosition() * CENTIMETERS_PER_TICK);

        if (gamepad1.y) {
            leftslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            if (gamepad2.a && leftslide.getCurrentPosition() < MAX_LS_HEIGHT * TICKS_PER_CENTIMETER
                    && rightslide.getCurrentPosition() < MAX_LS_HEIGHT * TICKS_PER_CENTIMETER) {
                leftslide.setPower(0.7);
                rightslide.setPower(0.7);
            } else if (gamepad2.y && leftslide.getCurrentPosition() > 0 && rightslide.getCurrentPosition() > 0) {
                leftslide.setPower(-0.7);
                rightslide.setPower(-0.7);
            } else {
                leftslide.setPower(0);
                rightslide.setPower(0);
            }
        }
    }
}