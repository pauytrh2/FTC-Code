package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.SubSystems.CalcPower;
import org.firstinspires.ftc.teamcode.SubSystems.Motors;
import org.firstinspires.ftc.teamcode.SubSystems.Pinpoint;

import java.util.Arrays;

public class Pathing {
    final static double max_speed = 0.5; // max speed
    final static double slow_distance = 50; // the distance when the robot starts to slow
    final static double stop_dist = 10;
    static double speed; // current speed
    static double[] power;

    public static void GoToPoint(IMU imu, double[] end) {
        double[] current = Pinpoint.getPosition();

        double dx = end[0] - current[0];
        double dy = end[1] - current[1];
        double angle = Math.atan2(dx, dy); // tha angle the robot needs to drive to get to end
        double dist = Math.hypot(dx, dy); // calculate the distance of the robot to the endpoint

        while (dist >= stop_dist) { // run until pinpoint is at the end
            if (dist >= slow_distance) { // make it only go slower if it is close
                speed = max_speed;
            } else speed = dist / 100; // if it is close go slower
//            tel.addData("angle (deg): ", angle);
//            tel.addData("target (cm)", Arrays.toString(end));
//            tel.addData("x: (cm)", String.valueOf(dx));
//            tel.addData("y: (cm)", String.valueOf(dy));
//            tel.addData("speed: ", speed);

            power = CalcPower.GetPowerByAngle(imu, speed, angle, 0); // calculate power
            Motors.setPower(power); // set power to the motors
//            tel.addData("power: ", Arrays.toString(power));

            current = Pinpoint.getPosition(); // updating the parameters
            dx = end[0] - current[0];
            dy = end[1] - current[1];
            angle = Math.atan2(dx, dy);
            dist = Math.hypot(dx, dy);

//            tel.update();
        }

        Motors.setPower(new double[]{0, 0, 0, 0}); // make sure it won't go vroom vroom kaboom
    }
}