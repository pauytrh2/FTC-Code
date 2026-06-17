package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.SubSystems.IMUInit;
import org.firstinspires.ftc.teamcode.SubSystems.Motors;

import java.util.concurrent.TimeUnit;

@Autonomous(name="A_Test", group="Linear OpMode")
public class A_Test extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotor[] motors = Motors.initMotors(hardwareMap);
        DcMotor frontLeftMotor = motors[0];
        DcMotor backLeftMotor = motors[1];
        DcMotor frontRightMotor = motors[2];
        DcMotor backRightMotor = motors[3];

        IMU imu = IMUInit.GetIMU(hardwareMap);

        telemetry.addData("Status", "Initialized & Ready");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            for (int i = 0; i < 4; i = i + 1) {
                imu.resetYaw();

                double targetAngle = 90;
                double currentAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
                double error = targetAngle - currentAngle;

                while (!(Math.abs(error) < 2) && opModeIsActive()) {
                    currentAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
                    error = targetAngle - currentAngle;

                    double target_speed = (error / 180) * 2;

                    frontLeftMotor.setPower(target_speed);
                    backLeftMotor.setPower(target_speed);

                    frontRightMotor.setPower(-target_speed);
                    backRightMotor.setPower(-target_speed);

                    telemetry.addData("Target", targetAngle);
                    telemetry.addData("Current", currentAngle);
                    telemetry.addData("Error", error);
                    telemetry.update();
                }

                frontLeftMotor.setPower(0);
                backLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backRightMotor.setPower(0);

                frontLeftMotor.setPower(0.2);
                backLeftMotor.setPower(0.2);
                frontRightMotor.setPower(0.2);
                backRightMotor.setPower(0.2);

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                frontLeftMotor.setPower(0);
                backLeftMotor.setPower(0);
                frontRightMotor.setPower(0);
                backRightMotor.setPower(0);
            }
        }
    }
}
