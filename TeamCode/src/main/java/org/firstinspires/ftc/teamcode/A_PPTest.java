package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name="A_PPTest", group="Linear OpMode")
public class A_PPTest extends OpMode {
    public static Follower follower;

    private PathChain path;

    private final Pose startPose = new Pose(0, 0, Math.toRadians(90));
    private final Pose topPose = new Pose(40, 40, Math.toRadians(180));
    private final Pose downPose = new Pose(40, 10, Math.toRadians(0));
    private final Pose endPose = new Pose(0, 0, Math.toRadians(90));

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose());
    }

    @Override
    public void start() {
        follower.setStartingPose(startPose);

        path = follower.pathBuilder()
                .addPath(new BezierLine(startPose, topPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), topPose.getHeading())
                .addPath(new BezierLine(topPose, downPose))
                .setTangentHeadingInterpolation()
                .addPath(new BezierLine(downPose, endPose))
                .setConstantHeadingInterpolation(endPose.getHeading())
                .build();

        follower.followPath(path);
    }

    @Override
    public void loop() {
        follower.update();

        if (!follower.isBusy()) {
            follower.followPath(path, true);
        }
    }
}
