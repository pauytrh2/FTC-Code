package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.SubSystems.IMUInit;
import org.firstinspires.ftc.teamcode.SubSystems.Motors;
import org.firstinspires.ftc.teamcode.Utils.Pathing;

@Autonomous(name="A_Home", group="Linear OpMode")
public class A_Home extends LinearOpMode {
    @Override
    public void runOpMode() {
        Motors.initMotors(hardwareMap);
        IMU imu = IMUInit.GetIMU(hardwareMap);
        imu.resetYaw();

        double[] home = {0, 0};

        waitForStart();

        if (opModeIsActive()) {
            Pathing.GoToPoint(imu, home);
        }
    }
}
