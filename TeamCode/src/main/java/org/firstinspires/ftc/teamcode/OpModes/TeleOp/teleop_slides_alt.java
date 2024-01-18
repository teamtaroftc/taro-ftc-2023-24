package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.hardware.Slides;
import org.firstinspires.ftc.teamcode.hardware.Claw;
import static org.firstinspires.ftc.teamcode.hardware.Constants.maxSlides;

// version 3 of taro's driver control code
// basic drivetrain opmode w/ other components (& servo claw) and split gamepads

// @Disabled
@TeleOp(name="teleop_slides_alt", group="Linear Opmode")
public class teleop_slides_alt extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();

    //name motor variables
    private DcMotor fldrive, frdrive, brdrive, bldrive, turret;
    Slides slides;
    Claw claw;

    public enum ClawPosition {
        CLAW_OPEN,
        CLAW_CLOSED
    }

    // not used
    public enum SlowMode {
        SLOW,
        REGULAR
    }

    @Override
    public void runOpMode()
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        slides = new Slides(hardwareMap);
        slides.resetEncoders();

        // map the motor variables to actual motors
        fldrive = hardwareMap.get(DcMotor.class, "fldrive");
        frdrive = hardwareMap.get(DcMotor.class, "frdrive");
        brdrive = hardwareMap.get(DcMotor.class, "brdrive");
        bldrive = hardwareMap.get(DcMotor.class, "bldrive");
        turret = hardwareMap.get(DcMotor.class, "turret");

        // set direction of motors
        fldrive.setDirection(DcMotor.Direction.FORWARD);
        frdrive.setDirection(DcMotor.Direction.REVERSE);
        brdrive.setDirection(DcMotor.Direction.REVERSE);
        bldrive.setDirection(DcMotor.Direction.FORWARD);
        turret.setDirection(DcMotor.Direction.REVERSE);

        claw = new Claw(hardwareMap);
        claw.openClaw();
        ClawPosition status = ClawPosition.CLAW_OPEN;

        // wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // slowmode
        boolean slowmode = false;
        boolean xControl = false;

        while (opModeIsActive())
        {
            double speed = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;
            double t = gamepad2.right_stick_x;
            double s = gamepad2.left_stick_y;

            // determine power for each motor
            double fl = turn+speed-strafe;
            double fr = turn-speed-strafe;
            double br = turn-speed+strafe;
            double bl = turn+speed+strafe;

            // slow mode
            if (slowmode){
                fl *= 0.7;
                fr *= 0.7;
                bl *= 0.7;
                br *= 0.7;
            }

            // set power to motors with range of -1 to 1
            fldrive.setPower(Range.clip(fl, -1.0, 1.0));
            frdrive.setPower(Range.clip(fr, -1.0, 1.0));
            brdrive.setPower(Range.clip(br, -1.0, 1.0));
            bldrive.setPower(Range.clip(bl, -1.0, 1.0));
            turret.setPower(Range.clip(t, -0.4, 0.4));


            if (s > 0 && slides.getHeight() > 0) slides.incrementDown();
            if (s < 0 && slides.getHeight() < maxSlides) slides.incrementUp();

            slides.setSpeed((int) Range.clip(s, -3000, 3000));

            if (gamepad2.b) slides.extendLow();
            if (gamepad2.a) slides.retract();

            if (gamepad1.x && xControl) slowmode = !slowmode;

            xControl = !gamepad1.x;

            switch (status) {

                case CLAW_OPEN:
                    if (gamepad2.y) {
                        claw.closeClaw();
                        status = ClawPosition.CLAW_CLOSED;
                    }
                    break;

                case CLAW_CLOSED:
                    if (gamepad2.x) {
                        claw.openClaw();
                        status = ClawPosition.CLAW_OPEN;
                    }
                    break;

            }

            // debug messages for each motor
            telemetry.addData("fldrive", Double.toString(fldrive.getPower()));
            telemetry.addData("frdrive", Double.toString(frdrive.getPower()));
            telemetry.addData("brdrive", Double.toString(brdrive.getPower()));
            telemetry.addData("bldrive", Double.toString(bldrive.getPower()));
            telemetry.addData("turret", Double.toString(turret.getPower()));
            telemetry.addData("servo", Double.toString(claw.getPosition()));
            telemetry.addData("slides", Double.toString(slides.getHeight()));

            telemetry.update();
        }
    }
}