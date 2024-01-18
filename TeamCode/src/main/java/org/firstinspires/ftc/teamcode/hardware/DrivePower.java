package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DrivePower {

    DcMotorEx fldrive;
    DcMotorEx frdrive;
    DcMotorEx bldrive;
    DcMotorEx brdrive;

    public DrivePower (HardwareMap hardwareMap) {

        fldrive = hardwareMap.get(DcMotorEx.class, "fldrive");
        frdrive = hardwareMap.get(DcMotorEx.class, "frdrive");
        brdrive = hardwareMap.get(DcMotorEx.class, "brdrive");
        bldrive = hardwareMap.get(DcMotorEx.class, "bldrive");

        fldrive.setDirection(DcMotor.Direction.FORWARD);
        frdrive.setDirection(DcMotor.Direction.FORWARD);
        brdrive.setDirection(DcMotor.Direction.FORWARD);
        bldrive.setDirection(DcMotor.Direction.FORWARD);
    }

    public void forward(double power) {
        fldrive.setPower(power);
        frdrive.setPower(power);
        bldrive.setPower(power);
        brdrive.setPower(power);
    }

    public void stop() {
        fldrive.setPower(0);
        frdrive.setPower(0);
        bldrive.setPower(0);
        brdrive.setPower(0);
    }



}