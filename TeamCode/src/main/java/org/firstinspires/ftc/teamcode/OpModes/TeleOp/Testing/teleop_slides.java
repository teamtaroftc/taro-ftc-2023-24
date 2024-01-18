package org.firstinspires.ftc.teamcode.OpModes.TeleOp.Testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

// version 3 of taro's driver control code
// basic drivetrain opmode w/ other components (& servo claw) and split gamepads

@Disabled
@TeleOp(name="teleop_slides", group="Linear Opmode")
public class teleop_slides extends LinearOpMode
{
    private ElapsedTime runtime = new ElapsedTime();

    //name motor variables
    private DcMotor fldrive, frdrive, brdrive, bldrive, slideleft, slideright;

    @Override
    public void runOpMode()
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // map the motor variables to actual motors
        fldrive = hardwareMap.get(DcMotor.class, "fldrive");
        frdrive = hardwareMap.get(DcMotor.class, "frdrive");
        brdrive = hardwareMap.get(DcMotor.class, "brdrive");
        bldrive = hardwareMap.get(DcMotor.class, "bldrive");
        slideleft = hardwareMap.get(DcMotor.class, "slideleft");
        slideright = hardwareMap.get(DcMotor.class, "slideright");


        // set direction of motors
        fldrive.setDirection(DcMotor.Direction.FORWARD);
        frdrive.setDirection(DcMotor.Direction.FORWARD);
        brdrive.setDirection(DcMotor.Direction.FORWARD);
        bldrive.setDirection(DcMotor.Direction.FORWARD);
        slideleft.setDirection(DcMotor.Direction.FORWARD);
        slideright.setDirection(DcMotor.Direction.FORWARD);

        // wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        //clawPosition = 0.5;

        // slowmode
        boolean slowmode = false;
        boolean xControl = false;

        while (opModeIsActive())
        {
            double speed = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;
            // double lsfor = gamepad2.right_trigger;
            // double lsback = gamepad2.left_trigger;
            double ls = gamepad2.left_stick_y;
            double tright = gamepad1.right_trigger;
            double tleft = gamepad1.left_trigger;

            //determine power for each motor
            double fl = turn+speed+strafe;
            double fr = turn-speed-strafe;
            double br = turn-speed+strafe;
            double bl = turn+speed-strafe;
            //double ls = lsfor - lsback;
            double t = tright - tleft;

            // slow mode
            if (slowmode){
                fl /= 10;
                fr /= 10;
                bl /= 10;
                br /= 10;
                ls /= 5;
            }

            //set power to motors with range of -1 to 1
            fldrive.setPower(Range.clip(fl, -1.0, 1.0));
            frdrive.setPower(Range.clip(fr, -1.0, 1.0));
            brdrive.setPower(Range.clip(br, -1.0, 1.0));
            bldrive.setPower(Range.clip(bl, -1.0, 1.0));
            slideleft.setPower(Range.clip(ls, -0.3, 0.3));
            slideright.setPower(Range.clip(ls, -0.3, 0.3));
/*
         if (cfor) carousel.setPower(0.5);
         else if (cback) carousel.setPower(-0.5);
         else carousel.setPower(0);
         if (gamepad1.x && xControl) slowmode = !slowmode;
*/

            xControl = !gamepad1.x;

            //debug messages for each motor
            telemetry.addData("fldrive",Double.toString(fldrive.getPower()));
            telemetry.addData("frdrive",Double.toString(frdrive.getPower()));
            telemetry.addData("brdrive",Double.toString(brdrive.getPower()));
            telemetry.addData("bldrive",Double.toString(bldrive.getPower()));
            telemetry.addData("slideleft",Double.toString(slideleft.getPower()));
            telemetry.addData("slideright",Double.toString(slideright.getPower()));
            //telemetry.addData("claw position",Double.toString(clawPosition));

            telemetry.update();
        }
    }
}