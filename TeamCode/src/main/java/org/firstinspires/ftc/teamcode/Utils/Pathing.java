package org.firstinspires.ftc.teamcode.Utils;
//imports if you could tell....
import static java.lang.Math.round;
import static java.lang.Math.sqrt;

import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.SubSystems.CalcPower;
import org.firstinspires.ftc.teamcode.SubSystems.Motors;
import org.firstinspires.ftc.teamcode.SubSystems.Pinpoint;

public class Pathing {
    public static void GoToPoint(IMU imu, double[] end) {
        double[] power;

        double[] current = Pinpoint.getPosition();//idk why the f*ck dos this use "roundList" and why dos "roundList" even exist. if end is not rounded it will go on with the loop forever (current != end) and it just makes it less accurate. >:(
        //double[] move = {end[0] - current[0], end[1] - current[1]};//localize the end pos relevantly to the pp(pinpoint). I like this. this good. <3
        double dx = end[0] - current[0];
        double dy = end[1] - current[1];
        double angle = Math.atan2(dy, dx); // tha angle the robot needs to drive to to get to end.
        double max_speed = 0.5; // max speed.
        double speed = max_speed; //current speed.
        double slow_distance = 5; //the distance wen the robot starts to slow.
        double dist = Math.hypot(dx, dy); // calculate the distance of the robot to the endpoint.
        double stop_dist = 0.5;

        while (dist >= stop_dist) {// run until pinpoint is at the end.
            if (dist >= slow_distance){
                speed = max_speed;
            }
            else speed = dist / 10;

            power = CalcPower.GetPowerByAngle(imu, angle, speed, 0);//calculate
            Motors.setPower(power);//set power to the motors

            current = Pinpoint.getPosition();//updating the parameters
            //move = new double[]{end[0] - current[0], end[1] - current[1]};
            dx = end[0] - current[0];
            dy = end[1] - current[1];
            angle = Math.atan2(dy, dx);
            dist = Math.hypot(dx, dy);
        }

        Motors.setPower(new double[]{0, 0, 0, 0});// make sure it won't go vroom vroom kaboom
    }

}
//plz work...