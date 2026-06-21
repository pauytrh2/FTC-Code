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

        boolean frozen = false;
        boolean lastR1 = false;
        double[] f_Input = new double[]{0, 0, 0};

        while (opModeIsActive()) {
            boolean currentR1State = gamepad1.right_bumper;

            if (currentR1State && !lastR1) {
                frozen = !frozen;
            }
            lastR1 = currentR1State;

            double[] input;
            if (frozen) {
                input = f_Input;
            } else {
                input = Input.GetInput(gamepad1, imu);
                f_Input = input;
            }

            double x = input[0];
            double y = input[1];
            double rx = input[2];

            double[] power = CalcPower.GetPower(imu, x, y, rx);

            Motors.setPower(motors, power);
        }
    }
}