package org.firstinspires.ftc.teamcode.Auto;

import static org.firstinspires.ftc.teamcode.opencv.OpenCVRed.getDistance;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.opencv.OpenCVRed;
import org.firstinspires.ftc.teamcode.opencv.OpenCVRed.RedBlobDetectionPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name = "Red Auto Close", group = "RedSide")
public class AutoRedClose extends LinearOpMode{
    private OpenCvCamera controlHubCam;  // Use OpenCvCamera class from FTC SDK
    private static final int CAMERA_WIDTH = 1920; // width  of wanted camera resolution
    private static final int CAMERA_HEIGHT = 1080; // height of wanted camera resolution
    public DcMotor leftFrontDrive   = null;
    public DcMotor rightFrontDrive  = null;
    public DcMotor leftBackDrive    = null;
    public DcMotor rightBackDrive   = null;
    public DcMotor rightSlideRotate = null;
    public DcMotor leftSlideRotate  = null;
    public DcMotorEx leftEncoder    = null;
    public DcMotorEx rightEncoder   = null;
    public Servo pixelHolder        = null;
    public int location = 2;
    @Override

    public void runOpMode(){
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFront");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFront");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftRear");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightRear");
        rightSlideRotate = hardwareMap.get(DcMotor.class, "rightSlideRotate");
        leftSlideRotate = hardwareMap.get(DcMotor.class, "leftSlideRotate");
        pixelHolder = hardwareMap.get(Servo.class, "pixelHolder");

        leftEncoder = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightEncoder = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);

        initOpenCV();
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        FtcDashboard.getInstance().startCameraStream(controlHubCam, 30);


        while (!opModeIsActive()) {
            telemetry.addData("Coordinate", "(" + (int) OpenCVRed.cX + ", " + (int) OpenCVRed.cY + ")");
            telemetry.addData("Distance in Inch", (getDistance(OpenCVRed.width)));
            telemetry.addData("Width", OpenCVRed.width);

            if(OpenCVRed.cX < 550 && OpenCVRed.cX >= 0){
                location = 0;
            } else if(OpenCVRed.cX >= 550 && OpenCVRed.cX < 2*CAMERA_WIDTH/3){
                location = 1;
            } else{
                location = 2;
            }


            telemetry.addData("Location: ",location);
            telemetry.update();

            // The OpenCV pipeline automatically processes frames and handles detection
        }

        // Release resources
        controlHubCam.stopStreaming();



        waitForStart();



        if (opModeIsActive()) {
            pixelHolder.setPosition(0);
            if (location ==1){
                for (long stop = System.nanoTime()+ 2000000000; stop>System.nanoTime();) {
                    leftFrontDrive.setPower(-0.4);
                    rightFrontDrive.setPower(-0.4);
                    leftBackDrive.setPower(-0.4);
                    rightBackDrive.setPower(-0.4);
                    rightSlideRotate.setPower(.35);
                    leftSlideRotate.setPower(.35);


                    telemetry.addData("Time: ", stop);
                    telemetry.update();
                    opModeIsActive();
                }
                for (long stop = System.nanoTime() + 1000000000; stop>System.nanoTime();){
                    leftFrontDrive.setPower(0);
                    rightFrontDrive.setPower(0);
                    leftBackDrive.setPower(0);
                    rightBackDrive.setPower(0);
                    rightSlideRotate.setPower(.35);
                    leftSlideRotate.setPower(.35);
                }
                for (long stop = System.nanoTime() + 1750000000; stop>System.nanoTime();){
                    leftFrontDrive.setPower(0.4);
                    rightFrontDrive.setPower(0.4);
                    leftBackDrive.setPower(0.4);
                    rightBackDrive.setPower(0.4);
                    rightSlideRotate.setPower(0.35);
                    leftSlideRotate.setPower(0.35);
                }
                for (long stop = System.nanoTime() + 1000000000; stop>System.nanoTime();){
                    leftFrontDrive.setPower(0);
                    rightFrontDrive.setPower(0);
                    leftBackDrive.setPower(0);
                    rightBackDrive.setPower(0);
                    rightSlideRotate.setPower(.35);
                    leftSlideRotate.setPower(.35);
                }
                for (long stop = System.nanoTime() + 2000000000; stop>System.nanoTime();){
                    leftFrontDrive.setPower(0.7 * -1);
                    rightFrontDrive.setPower(0.7);
                    leftBackDrive.setPower(0.7);
                    rightBackDrive.setPower(0.7 * -1);
                    rightSlideRotate.setPower(0.35);
                    leftSlideRotate.setPower(0.35);
                }
            }
            else if (location == 2) {
                for (long stop = System.nanoTime() + 1500000000; stop > System.nanoTime(); ) {
                    leftFrontDrive.setPower(-0.4);
                    rightFrontDrive.setPower(-0.4);
                    leftBackDrive.setPower(-0.4);
                    rightBackDrive.setPower(-0.4);
                    rightSlideRotate.setPower(.35);
                    leftSlideRotate.setPower(.35);
                    telemetry.addData("Time: ", stop);
                    telemetry.update();
                    opModeIsActive();
                }
                for (long stop = System.nanoTime() + 1000000000; stop > System.nanoTime(); ) {
                    leftFrontDrive.setPower(0);
                    rightFrontDrive.setPower(0);
                    leftBackDrive.setPower(0);
                    rightBackDrive.setPower(0);
                    rightSlideRotate.setPower(.35);
                    leftSlideRotate.setPower(.35);
                }


                for (long stop = System.nanoTime() + 1000000000; stop > System.nanoTime(); ) {
                    rightFrontDrive.setPower(-0.3);
                    leftBackDrive.setPower(0.525);
                    rightBackDrive.setPower(-0.525);
                    leftFrontDrive.setPower(-0.3);
                    rightSlideRotate.setPower(.3);
                    leftSlideRotate.setPower(.3);
                    telemetry.update();
                    opModeIsActive();
                }
                for (long stop = System.nanoTime() + 1000000000; stop > System.nanoTime(); ) {
                    leftFrontDrive.setPower(0);
                    rightFrontDrive.setPower(0);
                    leftBackDrive.setPower(0);
                    rightBackDrive.setPower(0);
                    rightSlideRotate.setPower(.3);
                    leftSlideRotate.setPower(.3);
                }
                for (long stop = System.nanoTime() + 800000000; stop > System.nanoTime(); ) {
                    rightFrontDrive.setPower(0.3);
                    leftBackDrive.setPower(0.3);
                    rightBackDrive.setPower(0.3);
                    leftFrontDrive.setPower(0.3);
                    rightSlideRotate.setPower(.3);
                    leftSlideRotate.setPower(.3);
                    telemetry.update();
                    opModeIsActive();
                }
                for (long stop = System.nanoTime() + 1000000000; stop > System.nanoTime(); ) {
                    leftFrontDrive.setPower(0);
                    rightFrontDrive.setPower(0);
                    leftBackDrive.setPower(0);
                    rightBackDrive.setPower(0);
                    rightSlideRotate.setPower(.3);
                    leftSlideRotate.setPower(.3);
                }
                for (long stop = System.nanoTime() + 1000000000; stop > System.nanoTime(); ) {
                        rightFrontDrive.setPower(0.3);
                        leftBackDrive.setPower(-0.525);
                        rightBackDrive.setPower(0.525);
                        leftFrontDrive.setPower(0.3);
                        rightSlideRotate.setPower(.3);
                        leftSlideRotate.setPower(.3);
                        telemetry.update();
                        opModeIsActive();

                }
                for (long stop = System.nanoTime() + 1000000000; stop > System.nanoTime(); ) {
                    leftFrontDrive.setPower(0);
                    rightFrontDrive.setPower(0);
                    leftBackDrive.setPower(0);
                    rightBackDrive.setPower(0);
                    rightSlideRotate.setPower(.3);
                    leftSlideRotate.setPower(.3);
                }
                for (long stop = System.nanoTime() + 800000000; stop > System.nanoTime(); ) {
                        rightFrontDrive.setPower(0.3);
                        leftBackDrive.setPower(0.3);
                        rightBackDrive.setPower(0.3);
                        leftFrontDrive.setPower(0.3);
                        rightSlideRotate.setPower(.3);
                        leftSlideRotate.setPower(.3);
                        telemetry.update();
                        opModeIsActive();
                }
                for (long stop = System.nanoTime() + 1000000000; stop > System.nanoTime(); ) {
                    leftFrontDrive.setPower(0);
                    rightFrontDrive.setPower(0);
                    leftBackDrive.setPower(0);
                    rightBackDrive.setPower(0);
                    rightSlideRotate.setPower(.3);
                    leftSlideRotate.setPower(.3);
                }
                for (long stop = System.nanoTime() + 1600000000; stop > System.nanoTime(); ) {
                        rightFrontDrive.setPower(0.35);
                        leftBackDrive.setPower(-0.35);
                        rightBackDrive.setPower(0.35);
                        leftFrontDrive.setPower(-0.35);
                        rightSlideRotate.setPower(.3);
                        leftSlideRotate.setPower(.3);
                        telemetry.update();
                        opModeIsActive();

                }
                for (long stop = System.nanoTime() + 1000000000; stop > System.nanoTime(); ) {
                    leftFrontDrive.setPower(0);
                    rightFrontDrive.setPower(0);
                    leftBackDrive.setPower(0);
                    rightBackDrive.setPower(0);
                    rightSlideRotate.setPower(.3);
                    leftSlideRotate.setPower(.3);
                }
                for (long stop = System.nanoTime() + 2147400000; stop > System.nanoTime(); ) {
                        rightFrontDrive.setPower(0.5);
                        leftBackDrive.setPower(0.5);
                        rightBackDrive.setPower(0.5);
                        leftFrontDrive.setPower(0.5);
                        rightSlideRotate.setPower(.3);
                        leftSlideRotate.setPower(.3);
                        telemetry.update();
                        opModeIsActive();

                    }
                }
            if (location == 0){
                while (leftEncoder.getCurrentPosition() > -39739.7416){
                    leftFrontDrive.setPower(-0.4);
                    rightFrontDrive.setPower(-0.4);
                    leftBackDrive.setPower(-0.4);
                    rightBackDrive.setPower(-0.4);
                    rightSlideRotate.setPower(.35);
                    leftSlideRotate.setPower(.35);
                }
//                for (long stop = System.nanoTime() + 2000000000 + 600000000; stop > System.nanoTime();) {
//                    rightFrontDrive.setPower(0);
//                    leftBackDrive.setPower(-0.43);
//                    rightBackDrive.setPower(0.43);
//                    leftFrontDrive.setPower(0);
//                    rightSlideRotate.setPower(.35);
//                    leftSlideRotate.setPower(.35);
//                    telemetry.update();
//                    opModeIsActive();
//                }
//                for (long stop = System.nanoTime() + 600000000; stop > System.nanoTime(); ) {
//                    leftFrontDrive.setPower(-0.4);
//                    rightFrontDrive.setPower(-0.4);
//                    leftBackDrive.setPower(-0.4);
//                    rightBackDrive.setPower(-0.4);
//                    rightSlideRotate.setPower(.35);
//                    leftSlideRotate.setPower(.35);
//                    telemetry.addData("Time: ", stop);
//                    telemetry.update();
//                    opModeIsActive();
//            }
//                for (long stop = System.nanoTime() + 1000000000; stop > System.nanoTime(); ) {
//                    leftFrontDrive.setPower(0.4);
//                    rightFrontDrive.setPower(0.4);
//                    leftBackDrive.setPower(0.4);
//                    rightBackDrive.setPower(0.4);
//                    rightSlideRotate.setPower(.35);
//                    leftSlideRotate.setPower(.35);
//                    telemetry.addData("Time: ", stop);
//                    telemetry.update();
//                    opModeIsActive();
//                }
//                for (long stop = System.nanoTime() + 2000000000; stop > System.nanoTime();) {
//                    rightFrontDrive.setPower(0);
//                    leftBackDrive.setPower(-0.43);
//                    rightBackDrive.setPower(0.43);
//                    leftFrontDrive.setPower(0);
//                    rightSlideRotate.setPower(.35);
//                    leftSlideRotate.setPower(.35);
//                    telemetry.update();
//                    opModeIsActive();
//                }
//                for (long stop = System.nanoTime() + 2000000000; stop > System.nanoTime();) {
//                    rightFrontDrive.setPower(0.5);
//                    leftBackDrive.setPower(0.5);
//                    rightBackDrive.setPower(0.5);
//                    leftFrontDrive.setPower(0.5);
//                    rightSlideRotate.setPower(.35);
//                    leftSlideRotate.setPower(.35);
//                    telemetry.update();
//                    opModeIsActive();
//                }
                }
        }

    }
    private void initOpenCV() {

        // Create an instance of the camera
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        // Use OpenCvCameraFactory class from FTC SDK to create camera instance
        controlHubCam = OpenCvCameraFactory.getInstance().createWebcam(
                hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        controlHubCam.setPipeline(new RedBlobDetectionPipeline());

        controlHubCam.openCameraDevice(); //I don't know why this is deprecated
        controlHubCam.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT);
    }
}
