package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.SubSystems.IMUInit;
import org.firstinspires.ftc.teamcode.SubSystems.Motors;
import org.firstinspires.ftc.teamcode.SubSystems.Pinpoint;
import org.firstinspires.ftc.teamcode.Utils.Pathing;

@Autonomous(name="A_ImportantDay", group="Linear OpMode")
public class A_ImportantDay extends LinearOpMode {
    @Override
    public void runOpMode() {
        Motors.initMotors(hardwareMap);
        Pinpoint.initPinpoint(hardwareMap);
        IMU imu = IMUInit.GetIMU(hardwareMap);
        imu.resetYaw();

        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                Pathing.GoToPoint(imu, new double[]{0, -40});
                Pathing.GoToPoint(imu, new double[]{40, -40});

            }
        }
    }
}
