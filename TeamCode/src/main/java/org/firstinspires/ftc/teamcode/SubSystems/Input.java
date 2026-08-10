package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Utils.Pathing;
public class Input extends SubsystemBase {


    public static double[] getInput(Gamepad gp, IMU imu) {
        double[] home = {0, 0};
        double y = gp.left_stick_y * 0.5;    // front and back
        double x = -gp.left_stick_x * 0.5;   // left and right
        double rx = -gp.right_stick_x * 0.5; // spin

        // fast mode
        if (gp.right_trigger_pressed) {
            y *= 2;
            x *= 2;
            rx *= 2;
        }

        // slow mode
        if (gp.left_trigger_pressed) {
            x *= 0.5;
            y *= 0.5;
            rx *= 0.5;
        }

        // reset imu
        if (gp.options) {
            imu.resetYaw();
            Pinpoint.resetPosition();
        }

        // homing
        if (gp.shareWasPressed()) {
            Pathing.GoToPoint(imu, home);
        }

        return new double[]{x, y, rx};
    }
}