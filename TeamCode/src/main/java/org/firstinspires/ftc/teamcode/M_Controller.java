package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.SubSystems.*;

@TeleOp(name="M_Controller", group="Linear OpMode")
public class M_Controller extends LinearOpMode {
    @Override
    public void runOpMode() {
        IMU imu = IMUInit.GetIMU(hardwareMap);
        Motors.initMotors(hardwareMap);
        Pinpoint.initPinpoint(hardwareMap);

        double x;
        double y;
        double rx;
        double[] input;
        double[] power;
        double[] position;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            Pinpoint.update();
            position = Pinpoint.getPosition();
            telemetry.addData("X Position (cm)", position[0]);
            telemetry.addData("Y Position (cm)", position[1]);
            telemetry.addData("Rotation (deg)", position[2]);

            input = Input.getInput(gamepad1, imu);
            x = input[0];
            y = input[1];
            rx = input[2];

            power = CalcPower.GetPower(imu, x, y, rx);

            Motors.setPower(power);
        }
    }
}