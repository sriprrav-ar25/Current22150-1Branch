package org.firstinspires.ftc.teamcode.practice;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Shooter Velocity Physics Test", group = "Shooter")
public class ShooterVelocityOpMode extends OpMode {

    private DcMotorEx shooterMotor;

    private ShooterVelocityCalculation velocityCalc;

    @Override
    public void init() {

        shooterMotor = hardwareMap.get(DcMotorEx.class, "shooterMotor");

        shooterMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        shooterMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);

        velocityCalc = new ShooterVelocityCalculation();
        velocityCalc.init(hardwareMap, telemetry);

        telemetry.addLine("Shooter Velocity OpMode Initialized");
        telemetry.update();
    }

    @Override
    public void start() {
        shooterMotor.setVelocity(250); // preload flywheel (rad/s)
    }

    @Override
    public void loop() {

        // Get Required Angular Velocity
        double omegaTarget = velocityCalc.getRequiredOmega();

        // Apply to Motor
        shooterMotor.setVelocity(omegaTarget);

        telemetry.addData("Target Omega (rad/s)", omegaTarget);
        telemetry.addData("Actual Omega (rad/s)",
                shooterMotor.getVelocity());
        telemetry.update();
    }

    @Override
    public void stop() {
        shooterMotor.setPower(0);
        velocityCalc.stop();
    }
}
