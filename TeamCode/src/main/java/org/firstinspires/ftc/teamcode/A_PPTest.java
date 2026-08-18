package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class A_PPTest extends OpMode {
    public static Follower follower;

    private PathChain path;

    private final double startX = 72;
    private final double startY = 72;

    private final Pose startPose = new Pose(startX, startY, Math.toRadians(90));
    private final Pose topPose = new Pose(startX + 60, startY + 60, Math.toRadians(180));
    private final Pose downPose = new Pose(startX + 60, startY + 10, Math.toRadians(0));
    private final Pose endPose = new Pose(startX, startY, Math.toRadians(90));

    @Override
    public void init() {
        follower.setStartingPose(new Pose(startX, startY));
    }

    @Override
    public void start() {
        follower.setStartingPose(startPose);

        path = follower.pathBuilder()
                .addPath(new BezierLine(startPose, topPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), topPose.getHeading())
                .addPath(new BezierLine(topPose, downPose))
                .setLinearHeadingInterpolation(topPose.getHeading(), downPose.getHeading())
                .addPath(new BezierLine(downPose, endPose))
                .setLinearHeadingInterpolation(downPose.getHeading(), endPose.getHeading())
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
