package org.firstinspires.ftc.teamcode.practice;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


@TeleOp(name = "Getting Velocity", group = "TeleOp")
public class TestCode extends OpMode {
    private DcMotorEx shooter;

    private double RADIUS_M = 0.0762;

    @Override
    public void init() {
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");

        shooter.setDirection(DcMotorEx.Direction.REVERSE);
        shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    @Override
    public void loop() {
        shooter.setPower(1.0);

        double velocityTicksPerSec = shooter.getVelocity();
        double omega = shooter.getVelocity(AngleUnit.RADIANS);

        double rpm = (omega * 60) / (2 * Math.PI);

        double frequencyInSec = velocityTicksPerSec / 28;

        telemetry.addData("Velocity (m/s)", 2 * Math.PI * RADIUS_M * frequencyInSec);
        telemetry.addData("Angular Velocity ", 2 * Math.PI * frequencyInSec);
        telemetry.addData("rpm", frequencyInSec * 60);
    }
}

