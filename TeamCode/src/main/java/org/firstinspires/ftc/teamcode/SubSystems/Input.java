package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Input extends SubsystemBase {
    public static double[] GetInput(Gamepad gp, IMU imu) {
        double y = -gp.left_stick_y;
        double x = gp.left_stick_x;
        double rx = gp.right_stick_x;

        if (gp.options) {
            imu.resetYaw();
        }

        return new double[]{y, x, rx};
    }
}