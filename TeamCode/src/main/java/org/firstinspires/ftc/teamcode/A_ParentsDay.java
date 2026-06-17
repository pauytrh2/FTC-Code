package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.SubSystems.IMUInit;
import org.firstinspires.ftc.teamcode.SubSystems.Motors;

import java.util.concurrent.TimeUnit;

@Autonomous(name="A_ParentsDay", group="Linear OpMode")
public class A_ParentsDay extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotor[] motors = Motors.initMotors(hardwareMap);
        IMU imu = IMUInit.GetIMU(hardwareMap);

        telemetry.addData("Status", "Initialized & Ready");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) return;

        if (opModeIsActive()) {
            imu.resetYaw();

            Motors.setPower(motors, new double[]{0.2, 0.2, 0.2, 0.2});
            Sleep(1);

            double targetAngle = 90;
            while (!(Math.abs(GetSpin(imu, targetAngle)[0]) < 2) && opModeIsActive()) {
                double[] spin = GetSpin(imu, targetAngle);
                double error = spin[0];
                double targetSpeed = spin[1];

                telemetry.addData("Target Angle", targetAngle);
                telemetry.addData("Target Speed", targetSpeed);
                telemetry.addData("Error", error);
                telemetry.update();

                Motors.setPower(motors, new double[]{targetSpeed, targetSpeed, -targetSpeed, -targetSpeed});
            }

            Motors.setPower(motors, new double[]{0, 0, 0, 0});
        }
    }

    public static void Sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static double[] GetSpin(IMU imu, double targetAngle) {
        imu.resetYaw();

        double currentAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        double error = targetAngle - currentAngle;
        double target_speed = (error / 180) * 2;

        return new double[]{error, target_speed};
    }
}
