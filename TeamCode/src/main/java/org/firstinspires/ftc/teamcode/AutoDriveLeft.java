package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutoDriveLeft")
public class AutoDriveLeft extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        AutoDrive drive = new AutoDrive(hardwareMap);

        waitForStart();
        drive.forward(15, 0.9 );
        drive.forwardright(25, 0.69);
        drive.backleft(1,0.5);
        drive.forward(30, 0.69);
        terminateOpModeNow();
    }
}
