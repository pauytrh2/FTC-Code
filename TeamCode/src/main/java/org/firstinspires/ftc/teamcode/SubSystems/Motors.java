package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Motors extends SubsystemBase {
     private static DcMotor[] getMotors(HardwareMap hw) {
        DcMotor frontLeftMotor = hw.dcMotor.get("left_front");
        DcMotor backLeftMotor = hw.dcMotor.get("left_back");
        DcMotor frontRightMotor = hw.dcMotor.get("right_front");
        DcMotor backRightMotor = hw.dcMotor.get("right_back");

        return new DcMotor[]{frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor};
    }

    public static void setPower(DcMotor[] motors, double[] power) {
        motors[0].setPower(power[0]);
        motors[1].setPower(power[1]);
        motors[2].setPower(power[2]);
        motors[3].setPower(power[3]);
    }

    public static DcMotor[] initMotors(HardwareMap hw) {
        DcMotor[] motors = getMotors(hw);
        DcMotor frontLeftMotor = motors[0];
        DcMotor backLeftMotor = motors[1];
        DcMotor frontRightMotor = motors[2];
        DcMotor backRightMotor = motors[3];

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        return new DcMotor[]{frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor};
    }
}