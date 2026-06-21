package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.teamcode.SubSystems.CalcPower;
import org.firstinspires.ftc.teamcode.SubSystems.IMUInit;
import org.firstinspires.ftc.teamcode.SubSystems.Motors;

import java.util.concurrent.TimeUnit;

@Autonomous(name="A_ParentsDay", group="Linear OpMode")
public class A_ParentsDay extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotor[] motors = Motors.initMotors(hardwareMap);

        IMU imu = IMUInit.GetIMU(hardwareMap);
        imu.resetYaw();

        waitForStart();

        if (opModeIsActive()) {
            Do(imu, motors, 2300, 0, 0.5, 0);

            Motors.setPower(motors, CalcPower.GetPower(imu, -0.5, 0, 0));
            SleepMil(2400);

            Motors.setPower(motors, CalcPower.GetPower(imu, 0, 0.5, 0));
            SleepMil(1300);

            Motors.setPower(motors, CalcPower.GetPower(imu, 0.55, 0, 0));
            SleepMil(1300);

            Do(imu, motors, 1500, 0, 0.5, 0.5);
        }
    }


    public static void SleepMil(long timeout) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void Do(IMU imu, DcMotor[] motors, long time, double x, double y, double rx) {
        long endTime = System.currentTimeMillis() + time;
        while (System.currentTimeMillis() < endTime) {
            Motors.setPower(motors, CalcPower.GetPower(imu, x, y, rx));
        }
    }

//    public static void Spin(IMU imu, DcMotor[] motors) {
//        for (int i = 0; i < 4; i = i + 1) {
//            imu.resetYaw();
//
//            double targetAngle = 90;
//            double currentAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
//            double error = targetAngle - currentAngle;
//
//            while (!(Math.abs(error) < 2)) {
//                currentAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
//                error = targetAngle - currentAngle;
//
//                double target_speed = (error / 180) * 2;
//
//                Motors.setPower(motors, new double[]{target_speed, target_speed, -target_speed, -target_speed});
//            }
//
//            Motors.setPower(motors, new double[]{0, 0, 0, 0});
//
//            Motors.setPower(motors, new double[]{0.1, 0.1, 0.1, 0.1});
//
//            SleepMi(1000);
//
//            Motors.setPower(motors, new double[]{0, 0, 0, 0});
//        }
//    }
}
