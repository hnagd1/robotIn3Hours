package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class teleOp extends OpMode {

    private DcMotor legie0;
    private DcMotor legie1;
    private CRServo Armie;

    @Override
    public void init() {
        legie0 = hardwareMap.get(DcMotor.class, "Motor0");
        legie1 = hardwareMap.get(DcMotor.class, "Motor1");
        Armie = hardwareMap.get(CRServo.class, "CRServo0");

        legie0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        legie1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        //right motor is motor0, left is motor1
        //If you want to turn right, the right motor has to slow down
        double RATIO = 312/84;
        double turnie = gamepad1.left_stick_x;
        double movie = gamepad1.left_stick_y;
        if (turnie > 0) {
            legie0.setPower((movie / RATIO)/turnie);
            legie1.setPower(movie);
        } else if (turnie < 0) {
            legie0.setPower((movie / RATIO));
            legie1.setPower(movie/turnie);
        }

        double armieStick = gamepad1.right_stick_y;
        Armie.setPower(armieStick);

        boolean doADoughnut = gamepad1.a;
        boolean doADonut = gamepad1.b;
        if (doADoughnut && !doADonut) {
            legie0.setPower(1);
            legie1.setPower(0.5);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else if (doADonut && !doADoughnut) {
            legie0.setPower(0.5);
            legie1.setPower(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
