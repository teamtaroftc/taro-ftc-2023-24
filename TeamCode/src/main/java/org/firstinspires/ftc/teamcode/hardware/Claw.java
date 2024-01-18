package org.firstinspires.ftc.teamcode.hardware;

import static org.firstinspires.ftc.teamcode.hardware.Constants.OPEN_POS;
import static org.firstinspires.ftc.teamcode.hardware.Constants.CLOSE_POS;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {

    Servo claw;

    public Claw (HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "claw");
    }

    public void openClaw() {
        claw.setPosition(OPEN_POS);
    }

    public void closeClaw() {
        claw.setPosition(CLOSE_POS);
    }

    public double getPosition() {
        return claw.getPosition();
    }

}
