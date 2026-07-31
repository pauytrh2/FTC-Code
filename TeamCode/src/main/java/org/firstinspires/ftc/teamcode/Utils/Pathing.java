package org.firstinspires.ftc.teamcode.Utils;
//imports if you could tell....
import static java.lang.Math.round;

import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.SubSystems.CalcPower;
import org.firstinspires.ftc.teamcode.SubSystems.Motors;
import org.firstinspires.ftc.teamcode.SubSystems.Pinpoint;

public class Pathing {
    public static void GoToPoint(IMU imu, double[] end) {
        double[] power;

        double[] current = roundList(Pinpoint.getPosition());//idk why the f*ck dos this use "roundList" and why dos "roundList" even exist. if end is not rounded it will go on with the loop forever (current != end) and it just makes it less accurate. >:(
        double[] move = {end[0] - current[0], end[1] - current[1]};//localize the end pos relevantly to the pp(pinpoint). I like this. this good. <3

        while (current != end) {// run until pinpoint is at the point
            power = CalcPower.GetPower(imu, move[0], move[1], 0);//nononono NO!!! I hate the fact that this could work this is throwing inches at a class that expects joystick input... INCH is NOT JOYSTICK, !=, not the same, na ah. it probably would work, but it's not adjustable...... its not even that!!! its throwing coordinates at power 0-1 noooooooo!!!!! no no NO NO NO!!!! AHHHHHHHHHHHHHHHHHHH!!! NO.
            Motors.setPower(power);//set power to the motors

            current = roundList(Pinpoint.getPosition());//updating the parameters
            move = new double[]{end[0] - current[0], end[1] - current[1]};
        }

        Motors.setPower(new double[]{0, 0, 0, 0});// make sure it won't go vroom vroom kaboom
    }

    public static double[] roundList(double[] list) {//wtf no i hate this, this has no use(either that or i'm just dumb)
        list[0] = round(list[0]);
        list[1] = round(list[1]);

        return list;
    }
}
