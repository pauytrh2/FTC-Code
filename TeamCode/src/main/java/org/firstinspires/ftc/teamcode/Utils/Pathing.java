package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.SubSystems.CalcPower;
import org.firstinspires.ftc.teamcode.SubSystems.Motors;
import org.firstinspires.ftc.teamcode.SubSystems.Pinpoint;


public class Pathing {
    static double[] power;
    static double max_speed = 0.5; // max speed
    static double speed; // current speed
    static double slow_distance = 5; // the distance when the robot starts to slow
    static double stop_dist = 0.5;

    public static void GoToPoint(IMU imu, double[] end) {
        double[] current = Pinpoint.getPosition();

        double dx = end[0] - current[0];
        double dy = end[1] - current[1];
        double angle = Math.atan2(dy, dx); // tha angle the robot needs to drive to to get to end
        double dist = Math.hypot(dx, dy); // calculate the distance of the robot to the endpoint

        while (dist >= stop_dist) { // run until pinpoint is at the end
            if (dist >= slow_distance) { // make it only go slower if it is close
                speed = max_speed;
            } else speed = dist / 10; // if it is close go slower

            power = CalcPower.GetPowerByAngle(imu, angle, speed, 0); // calculate power
            Motors.setPower(power); // set power to the motors

            current = Pinpoint.getPosition(); // updating the parameters
            dx = end[0] - current[0];
            dy = end[1] - current[1];
            angle = Math.atan2(dy, dx);
            dist = Math.hypot(dx, dy);
        }

        Motors.setPower(new double[]{0, 0, 0, 0}); // make sure it won't go vroom vroom kaboom
    }
}