package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.SubSystems.*;

@TeleOp(name="M_Controller", group="Linear OpMode")
public class M_Controller extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotor[] motors = Motors.initMotors(hardwareMap);
        IMU imu = IMUInit.GetIMU(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double[] input = Input.GetInput(gamepad1, imu);
            double x = input[0];
            double y = input[1];
            double rx = input[2];

            double[] power = CalcPower.GetPower(imu, x, y, rx, telemetry);

            Motors.setPower(motors, power);
        }
    }
}