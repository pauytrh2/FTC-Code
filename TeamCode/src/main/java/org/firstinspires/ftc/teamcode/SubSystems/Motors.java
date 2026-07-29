package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Motors extends SubsystemBase {
    static DcMotor frontLeftMotor;
    static DcMotor backLeftMotor;
    static DcMotor frontRightMotor;
    static DcMotor backRightMotor;

     public static void initMotors(HardwareMap hw) {
        frontLeftMotor = hw.dcMotor.get("left_front");
        backLeftMotor = hw.dcMotor.get("left_back");
        frontRightMotor = hw.dcMotor.get("right_front");
        backRightMotor = hw.dcMotor.get("right_back");

         frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
         backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
     }

    public static void setPower(double[] power) {
        frontLeftMotor.setPower(power[0]);
        backLeftMotor.setPower(power[1]);
        frontRightMotor.setPower(power[2]);
        backRightMotor.setPower(power[3]);
    }
}