package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Claw;
import org.firstinspires.ftc.teamcode.hardware.DrivePower;

// @Disabled
@Autonomous(name="auton_1000", group="Linear OpMode")

public class auton_1000 extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();

    DrivePower drive;
    Claw claw;

    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Intialized");
        telemetry.update();

        claw = new Claw(hardwareMap);
        claw.openClaw();

        drive = new DrivePower(hardwareMap);
        // drive.resetEncoders();

        waitForStart();
        runtime.reset();

        // write code here using functions
        // claw.closeClaw();
        drive.forward(0.5);
        sleep(1000);
        drive.stop();
        // claw.openClaw();

    }
}