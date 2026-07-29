package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class Pinpoint extends SubsystemBase {
    static GoBildaPinpointDriver pinpoint;

    public static void initPinpoint(HardwareMap hw) {
        pinpoint = hw.get(GoBildaPinpointDriver.class, "pinpoint");
        configurePinpoint();
        pinpoint.setPosition(new Pose2D(DistanceUnit.CM, 0, 0, AngleUnit.DEGREES, 0));
    }

    public static void update() {
        pinpoint.update();
    }

    public static double[] getPosition() {
        Pose2D pose = pinpoint.getPosition();

        return new double[]{pose.getX(DistanceUnit.CM), pose.getY(DistanceUnit.CM), pose.getHeading(AngleUnit.DEGREES)};
    }

    public static void configurePinpoint(){
        pinpoint.setOffsets(-84.0, -168.0, DistanceUnit.MM);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);
        pinpoint.resetPosAndIMU();
    }
}