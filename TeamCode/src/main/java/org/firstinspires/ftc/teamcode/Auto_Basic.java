package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import java.util.concurrent.TimeUnit;

@Autonomous(name="Auto_Basic", group="Linear OpMode")
public class Auto_Basic extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("left_front");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("left_back");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("right_front");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("right_back");

        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        IMU imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        telemetry.addData("Status", "Initialized & Ready");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            imu.resetYaw();
            double targetAngle = 90;

            while (opModeIsActive()) {
                double currentAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

                double error = targetAngle - currentAngle;

                if (Math.abs(error) < 2) {
                    break;
                }

                double tspeed = (error / 180) * 2;

                frontLeftMotor.setPower(tspeed);
                backLeftMotor.setPower(tspeed);

                frontRightMotor.setPower(-tspeed);
                backRightMotor.setPower(-tspeed);

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
