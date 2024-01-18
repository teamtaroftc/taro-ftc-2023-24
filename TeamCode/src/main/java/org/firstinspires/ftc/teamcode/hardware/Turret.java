package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Turret {

    DcMotorEx turret;
    // core hex motor, 288 counts/rev

    private static int increment = 50;
    private static int velocity = 3000;
    private static int countsPerRev = 288;

    public Turret (HardwareMap hardwareMap) {
        turret = hardwareMap.get(DcMotorEx.class, "turret");
        turret.setDirection(DcMotorSimple.Direction.FORWARD);
        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turret.setTargetPosition(0);
        turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void resetEncoders() {
        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turret.setTargetPosition(0);
        turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void turnLeft() {
        turret.setTargetPosition(turret.getCurrentPosition() - increment);
        turret.setVelocity(velocity);
    }

    public void turnRight() {
        turret.setTargetPosition(turret.getCurrentPosition() + increment);
        turret.setVelocity(velocity);
    }

    public void returnToCenter() {
        turret.setTargetPosition(countsPerRev/4);
        turret.setVelocity(velocity);
    }

    public void turnDegrees() {

    }

}
