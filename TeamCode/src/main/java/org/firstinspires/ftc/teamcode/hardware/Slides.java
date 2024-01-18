package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.teamcode.hardware.Constants.base;
import static org.firstinspires.ftc.teamcode.hardware.Constants.low;
import static org.firstinspires.ftc.teamcode.hardware.Constants.mid;
import static org.firstinspires.ftc.teamcode.hardware.Constants.high;
import static org.firstinspires.ftc.teamcode.hardware.Constants.junction;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {

    DcMotorEx leftSlide;
    DcMotorEx rightSlide;

    private static int increment = 50;
    private static int velocity = 3000;

    public Slides (HardwareMap hardwareMap) {
        leftSlide = hardwareMap.get(DcMotorEx.class, "slideleft");
        rightSlide = hardwareMap.get(DcMotorEx.class, "slideright");
        leftSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        rightSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setTargetPosition(0);
        rightSlide.setTargetPosition(0);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void resetEncoders() {
        leftSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlide.setTargetPosition(0);
        rightSlide.setTargetPosition(0);
        leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void incVelocity(int increase) {
        velocity += increase;
    }

    public void decVelocity(int decrease) {
        velocity -= decrease;
    }

    public void setSpeed(int newVel) { velocity = newVel; }

    // broken method
    public void increment(double change) {
        if (leftSlide.getCurrentPosition() >= 0 && leftSlide.getCurrentPosition() < 2000) {
            leftSlide.setTargetPosition(leftSlide.getCurrentPosition() + (int) (change * increment));
            rightSlide.setTargetPosition(leftSlide.getCurrentPosition() + (int) (change * increment));
            leftSlide.setVelocity(velocity);
            rightSlide.setVelocity(velocity);
        }
    }

    public void incrementDown() {
        leftSlide.setTargetPosition(leftSlide.getCurrentPosition() - increment);
        rightSlide.setTargetPosition(leftSlide.getCurrentPosition() - increment);
        leftSlide.setVelocity(velocity);
        rightSlide.setVelocity(velocity);
    }

    public void incrementUp() {
        leftSlide.setTargetPosition(leftSlide.getCurrentPosition() + increment);
        rightSlide.setTargetPosition(leftSlide.getCurrentPosition() + increment);
        leftSlide.setVelocity(velocity);
        rightSlide.setVelocity(velocity);
    }

    public void retract() {
        leftSlide.setTargetPosition(base);
        rightSlide.setTargetPosition(base);
        leftSlide.setVelocity(3000);
        rightSlide.setVelocity(3000);
    }

    public int getHeight() {
        return leftSlide.getCurrentPosition();
    }

    public boolean isBusy() {
        return Math.abs(leftSlide.getCurrentPosition() - leftSlide.getTargetPosition()) < 30;
    }

    public void extendHigh() {
        leftSlide.setTargetPosition(high);
        rightSlide.setTargetPosition(high);
        leftSlide.setVelocity(velocity);
        rightSlide.setVelocity(velocity);
    }

    public void extendMid() {
        leftSlide.setTargetPosition(mid);
        rightSlide.setTargetPosition(mid);
        leftSlide.setVelocity(velocity);
        rightSlide.setVelocity(velocity);
    }


    public void extendLow() {
        leftSlide.setTargetPosition(low);
        rightSlide.setTargetPosition(low);
        leftSlide.setVelocity(3000);
        rightSlide.setVelocity(3000);
    }

    public void extendJunction() {
        leftSlide.setTargetPosition(junction);
        rightSlide.setTargetPosition(junction);
        leftSlide.setVelocity(velocity);
        rightSlide.setVelocity(velocity);
    }

}