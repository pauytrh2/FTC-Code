package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.SubSystems.*;

@TeleOp(name="M_Controller", group="Linear OpMode")
public class M_Controller extends LinearOpMode {
    @Override
    public void runOpMode() { // this runs when run OpMode is pressed on the control hub
        IMU imu = IMUInit.GetIMU(hardwareMap); // this tells the robot it's direction
        Motors.initMotors(hardwareMap);        // these are the wheels' motors
        Pinpoint.initPinpoint(hardwareMap);    // this tells the robot it's position

        // variables we will use later
        double input_x;
        double input_y;
        double input_rx;
        double[] input;
        double[] power;
        double[] position;

        waitForStart(); // wait until start is pressed on the control hub

        if (isStopRequested()) return; // stop the OpMode if stop is pressed on the control hub

        while (opModeIsActive()) { // while the OpMode is running
            Pinpoint.update(); // get fresh data from odometry (position sensors)
            position = Pinpoint.getPosition(); // store that data

            // log all the data from the odometry
            telemetry.addData("X Position (cm)", position[0]);
            telemetry.addData("Y Position (cm)", position[1]);
            telemetry.addData("Rotation (deg)", position[2]);
            telemetry.update();

            input = Input.getInput(gamepad1, imu); // get input from controller

            // store that input in variables
            input_x = input[0];
            input_y = input[1];
            input_rx = input[2];

            power = CalcPower.GetPower(imu, input_x, input_y, input_rx); // calculate how much power to give to each wheel's motor

            Motors.setPower(power); // give the wheels' motors that amount of power
        }
    }
}