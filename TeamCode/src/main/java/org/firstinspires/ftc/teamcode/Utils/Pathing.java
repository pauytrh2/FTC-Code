package org.firstinspires.ftc.teamcode.Utils;

import static java.lang.Math.round;

import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.SubSystems.CalcPower;
import org.firstinspires.ftc.teamcode.SubSystems.Motors;
import org.firstinspires.ftc.teamcode.SubSystems.Pinpoint;

public class Pathing {
    public static void GoToPoint(IMU imu, double[] end) {
        double[] power;

        double[] current = roundList(Pinpoint.getPosition());
        double[] move = {end[0] - current[0], end[1] - current[1]};

        while (current != end) {
            power = CalcPower.GetPower(imu, move[0], move[1], 0);
            Motors.setPower(power);

            current = roundList(Pinpoint.getPosition());
            move = new double[]{end[0] - current[0], end[1] - current[1]};
        }
    }

    public static double[] roundList(double[] list) {
        list[0] = round(list[0]);
        list[1] = round(list[1]);

        return list;
    }
}
