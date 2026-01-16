package org.firstinspires.ftc.teamcode.teleop;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.intake;

public class intakeArtifacts extends OpMode {
     intake intake = new intake();

    @Override
    public void init() {
        intake.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.left_trigger > 0) {
            intake.setPower(gamepad1.left_trigger);
        } else if (gamepad1.right_trigger > 0) {
            intake.setPower(-gamepad1.right_trigger);
        } else {
            intake.setPower(0);
        }
    }
}
