/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.HardwareClasses.testHardware;

import java.util.concurrent.TimeUnit;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="testTele", group="Linear Opmode")

public class testTele extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private testHardware robot = new testHardware();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double verticalComponent = -gamepad1.left_stick_y;
            double lateralComponent = gamepad1.left_stick_x;
            double turnComponent = gamepad1.right_stick_x;
            double slideRotatePos = gamepad2.left_trigger;
            double slideRotateNeg = gamepad2.right_trigger;
            boolean slideOut = gamepad2.left_bumper;
            boolean slideIn = gamepad2.right_bumper;
            boolean rollers = gamepad2.x;
            boolean rollerDown = gamepad2.a;
            boolean rollerUp = gamepad2.y;
            boolean launchPlane1 = gamepad1.b;
            boolean launchPlane2 = gamepad1.x;
            boolean pixelHolding = gamepad2.b;
            boolean rotateHookPos = gamepad1.y;
            boolean rotateHookNeg = gamepad1.a;
            boolean pixelRemove = gamepad2.back;
            boolean slowFront = gamepad2.dpad_up;
            boolean slowBack = gamepad2.dpad_down;
            boolean slowLeft = gamepad2.dpad_left;
            boolean slowRight = gamepad2.dpad_right;
            boolean autoOutToIn = gamepad2.left_stick_button;
            boolean autoInToOut = gamepad2.right_stick_button;

            double SPEED_MULTIPLIER = 0.95;


            double normalizingFactor = Math.max(Math.abs(verticalComponent)
                    + Math.abs(lateralComponent) + Math.abs(turnComponent), 1);

            double fl = SPEED_MULTIPLIER * (verticalComponent + lateralComponent + turnComponent) / normalizingFactor;
            double fr = SPEED_MULTIPLIER * (verticalComponent - lateralComponent - turnComponent) / normalizingFactor;
            double bl = SPEED_MULTIPLIER * (verticalComponent - lateralComponent + turnComponent) / normalizingFactor;
            double br = SPEED_MULTIPLIER * (verticalComponent + lateralComponent - turnComponent) / normalizingFactor;

            //Slow Movements - DPAD

            if (slowFront){
                fl = 0.35;
                fr = 0.35;
                bl = 0.35;
                br = 0.35;
            }
            else if (slowBack){
                fl = 0.35 * -1;
                fr = 0.35 * -1;
                bl = 0.35 * -1;
                br = 0.35 * -1;
            }
            else if (slowLeft){
                fl = -1 * 0.5;
                fr = 0.5;
                bl = 0.5;
                br = -1 * 0.5;
            }
            else if (slowRight){
                fl = 0.5;
                fr = -1 * 0.5;
                bl = -1*0.5;
                br = 0.5;
            }

            robot.leftFrontDrive.setPower(fl);
            robot.rightFrontDrive.setPower(fr);
            robot.leftBackDrive.setPower(bl);
            robot.rightBackDrive.setPower(br);



            //Slide Rotate

            if (slideRotatePos>0){
                robot.leftSlideRotate.setPower(0.5);
                robot.rightSlideRotate.setPower(0.5);
            }
            else if (slideRotateNeg > 0){
                robot.leftSlideRotate.setPower(0.5 * -1);
                robot.rightSlideRotate.setPower(0.5 * -1);
            }
            else{
                robot.leftSlideRotate.setPower(0);
                robot.rightSlideRotate.setPower(0);
            }

            //Slides Out/In

            if (slideOut){
                robot.slides.setPower(0.4);
            }
            else if (slideIn){
                robot.slides.setPower(0.4 * -1);
            }
            else{
                robot.slides.setPower(0);
            }

            //Rollers

            if (rollers){
                robot.leftRoller.setPower(-1);
                robot.rightRoller.setPower(-1);
            }
            else{
                robot.leftRoller.setPower(0);
                robot.rightRoller.setPower(0);
            }

            //Roller Arm

            if (rollerDown){
                robot.leftRollerArm.setPower(0.5);
                robot.rightRollerArm.setPower(0.5);
            }
            else if (rollerUp){
                robot.leftRollerArm.setPower(0.5 * -1);
                robot.rightRollerArm.setPower(0.5 * -1);
            }
            else{
                robot.leftRollerArm.setPower(0);
                robot.rightRollerArm.setPower(0);
            }

            //Airplane Launching

            if (launchPlane1 && launchPlane2){
                robot.airplaneLauncher.setPower(0.5);
            }
            else{
                robot.airplaneLauncher.setPower(0);
            }


            if (pixelHolding){
                robot.pixelHolder.setPower(1);
            }
            else if (pixelRemove){
                robot.pixelHolder.setPower(-1);
            }
            else{
                robot.pixelHolder.setPower(0);
            }



            if (rotateHookPos){
                robot.hook.setPower(1);
            }
            else if (rotateHookNeg){
                robot.hook.setPower(-1);
            }
            else{
                robot.hook.setPower(0);
            }

            if (autoInToOut){
                for (int i = 0; i < 400; i++) {
                    robot.slides.setPower(-1 * 0.6);
                }
                for (int k = 0; k < 50000; k ++){
                    robot.leftSlideRotate.setPower(0.7);
                    robot.rightSlideRotate.setPower(0.7);
                }
                for (int d = 0; d < 50000; d ++){
                    robot.leftRollerArm.setPower(0.75);
                    robot.rightRollerArm.setPower(0.75);
                }
                for (int f = 0; f < 10000; f ++){
                    robot.airplaneLauncher.setPower(0);
                }
                for (int s = 0; s < 40000; s ++){
                    robot.leftSlideRotate.setPower(0.7 * -1);
                    robot.rightSlideRotate.setPower(0.7 * -1);
                }

            }

            if (autoOutToIn) {
                for (int s = 0; s < 20000; s++) {
                    robot.leftSlideRotate.setPower(0.7);
                    robot.rightSlideRotate.setPower(0.7);
                }
                for (int o = 0; o < 50000; o++){
                    robot.leftRollerArm.setPower(-1 * 0.75);
                    robot.rightRollerArm.setPower(-1 * 0.75);
                }
                for (int p = 0; p < 25000; p++){
                    robot.leftSlideRotate.setPower(-1 * 0.4);
                    robot.rightSlideRotate.setPower(-1 * 0.4);
                }
                for (int e = 0; e < 50000; e++){
                    robot.leftRollerArm.setPower(0.75);
                    robot.rightRollerArm.setPower(0.75);
                }
                Thread.sleep(250);
                for (int l = 0; l < 30000; l++){
                    robot.slides.setPower(0.7);
                }
            }

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}
