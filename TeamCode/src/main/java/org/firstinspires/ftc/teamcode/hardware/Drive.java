package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drive {

    DcMotorEx fldrive;
    DcMotorEx frdrive;
    DcMotorEx bldrive;
    DcMotorEx brdrive;

    public Drive (HardwareMap hardwareMap) {

        fldrive = hardwareMap.get(DcMotorEx.class, "fldrive");
        frdrive = hardwareMap.get(DcMotorEx.class, "frdrive");
        brdrive = hardwareMap.get(DcMotorEx.class, "brdrive");
        bldrive = hardwareMap.get(DcMotorEx.class, "bldrive");

        fldrive.setDirection(DcMotor.Direction.FORWARD);
        frdrive.setDirection(DcMotor.Direction.REVERSE);
        brdrive.setDirection(DcMotor.Direction.REVERSE);
        bldrive.setDirection(DcMotor.Direction.FORWARD);

        fldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fldrive.setTargetPosition(0);
        frdrive.setTargetPosition(0);
        bldrive.setTargetPosition(0);
        brdrive.setTargetPosition(0);

        fldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void resetEncoders() {
        fldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bldrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brdrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fldrive.setTargetPosition(0);
        frdrive.setTargetPosition(0);
        bldrive.setTargetPosition(0);
        brdrive.setTargetPosition(0);

        fldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bldrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        brdrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void forward(int distance) {
        fldrive.setTargetPosition(distance);
        frdrive.setTargetPosition(distance);
        bldrive.setTargetPosition(distance);
        brdrive.setTargetPosition(distance);

        fldrive.setVelocity(3000);
        frdrive.setVelocity(3000);
        bldrive.setVelocity(3000);
        brdrive.setVelocity(3000);

        /* while (fldrive.isBusy() && frdrive.isBusy() && bldrive.isBusy() && brdrive.isBusy()) {
            //until point reached
        } */
    }

}