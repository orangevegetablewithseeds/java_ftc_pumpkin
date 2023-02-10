package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Scanning.AprilTagDetectionPipeline;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

@Autonomous(name ="KamTick")
public class KamTick extends LinearOpMode {

    DcMotor frontLm2;
    DcMotor frontRm2;
    DcMotor backLm2;
    DcMotor backRm2;
    Servo clawS;
    Servo leftarm;
    Servo rightarm;

    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    // Tag ID 1,2,3 from the 36h11 family
    int LEFT = 9;
    int MIDDLE = 12;
    int RIGHT = 16;

    AprilTagDetection tagOfInterest = null;

    @Override
    public void runOpMode() throws InterruptedException {
        AutoDrive drive = new AutoDrive(hardwareMap);
        frontLm2 = hardwareMap.get(DcMotor.class, "frontL");
        frontRm2 = hardwareMap.get(DcMotor.class, "frontR");
        backLm2 = hardwareMap.get(DcMotor.class, "backL");
        backRm2 = hardwareMap.get(DcMotor.class, "backR");
        clawS = hardwareMap.get(Servo.class,"clawS");
        leftarm = hardwareMap.get(Servo.class,"leftarm");
        rightarm = hardwareMap.get(Servo.class,"rightarm");


        frontLm2.setDirection(DcMotorSimple.Direction.REVERSE);
        backLm2.setDirection(DcMotorSimple.Direction.REVERSE);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        telemetry.setMsTransmissionInterval(50);


      //   * The INIT-loop:
         clawS.setPosition(0.9);
         leftarm.setPosition(0.3);
         rightarm.setPosition(0.7);
    //     * This REPLACES waitForStart!-----------------------------------------------

        while (!isStarted() && !isStopRequested())
        {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0)
            {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections)
                {
                    if(tag.id == LEFT || tag.id == MIDDLE || tag.id == RIGHT)
                    {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound)
                {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                }
                else
                {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null)
                    {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else
                    {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }

            }
            else
            {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null)
                {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else
                {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }

            }

            telemetry.update();
            sleep(20);
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if(tagOfInterest != null)
        {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        }
        else
        {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }

        /* Actually do something useful */

        if(tagOfInterest == null || tagOfInterest.id == LEFT) {
            drive.forward(50, 0.69);
            drive.right(14, 0.69);
            terminateOpModeNow();
        } else if(tagOfInterest.id == MIDDLE) {
            drive.forward(43, 0.69);
            terminateOpModeNow();
        } else {
            drive.forward(50,0.69);
            drive.left(14, 0.69);
            terminateOpModeNow();
        }

        /* You wouldn't have this in your autonomous, this is just to prevent the sample from ending */
        while (opModeIsActive()) {sleep(20);}
    }

    void tagToTelemetry(AprilTagDetection detection)
    {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }
    public void GoForward(long time, double power) {
        frontLm2.setPower(power);
        frontRm2.setPower(power);
        backLm2.setPower(power);
        backRm2.setPower(power);
        sleep(time * 100);
    }
    public void GoBack(long time, double power) {
        frontLm2.setPower(-power);
        frontRm2.setPower(-power);
        backLm2.setPower(-power);
        backRm2.setPower(-power);
        sleep(time * 100);
    }
    public void GoLeft(long time, double power) {
        frontLm2.setPower(-power);
        frontRm2.setPower(power);
        backLm2.setPower(power);
        backRm2.setPower(-power);
        sleep(time * 100);
    }
    public void GoRight(long time, double power) {
        frontLm2.setPower(power);
        frontRm2.setPower(-power);
        backLm2.setPower(-power);
        backRm2.setPower(power);
        sleep(time * 100);
    }
    public void TopLeft(long time, double power) {
        frontRm2.setPower(power);
        backLm2.setPower(power);
        sleep(time * 100);
    }
    public void BottomLeft(long time, double power) {
        frontRm2.setPower(-power);
        backLm2.setPower(-power);
        sleep(time * 100);
    }
    public void TopRight(long time, double power) {
        frontLm2.setPower(power);
        backRm2.setPower(power);
        sleep(time * 100);
    }
    public void BottomRight(long time, double power) {
        frontLm2.setPower(-power);
        backRm2.setPower(-power);
        sleep(time * 100);
    }
    public void StopNow(long time) {
        frontLm2.setPower(0);
        frontRm2.setPower(0);
        backLm2.setPower(0);
        backRm2.setPower(0);
        sleep(time * 100);
    }
    public void TurnLeft(long time, double power) {
        frontLm2.setPower(-power);
        frontRm2.setPower(power);
        backLm2.setPower(-power);
        backRm2.setPower(power);
        sleep(time * 100);
    }
    public void TurnRight(long time, double power) {
        frontLm2.setPower(power);
        frontRm2.setPower(-power);
        backLm2.setPower(power);
        backRm2.setPower(-power);
        sleep(time * 100);
    }
    /*
    public void ClawOpen(long time, double power) {
        clawS2.setPower(power);
        sleep(time * 100);
    }
    public void ClawClose(long time, double power) {
        clawS2.setPower(-power);
        sleep(time * 100);
    }
    public void ArmUp(long time, double power) {
        clawMA2.setPower(power);
        sleep(time * 100);
    }
    public void ArmDown(long time, double power) {
        clawMA2.setPower(-power);
        sleep(time * 100);
    }
     */
}