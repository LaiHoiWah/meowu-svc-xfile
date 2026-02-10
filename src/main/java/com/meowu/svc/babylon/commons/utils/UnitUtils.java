package com.meowu.svc.babylon.commons.utils;

import com.meowu.starter.common.commons.utils.AssertUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Duration;

public class UnitUtils{

    //------------ size ------------//
    private static final long ONE_B  = 1;
    private static final long ONE_KB = 1024L;
    private static final long ONE_MB = ONE_KB * 1024;
    private static final long ONE_GB = ONE_MB * 1024;
    private static final long ONE_TB = ONE_GB * 1024;
    private static final long ONE_PB = ONE_TB * 1024;
    private static final long ONE_EB = ONE_PB * 1024;

    private static final String UNIT_B  = "B";
    private static final String UNIT_KB = "KB";
    private static final String UNIT_MB = "MB";
    private static final String UNIT_GB = "GB";
    private static final String UNIT_TB = "TB";
    private static final String UNIT_PB = "PB";
    private static final String UNIT_EB = "EB";

    //------------ bit rate ------------//
    private static final long ONE_BS = 1L;
    private static final long ONE_KBS = 1000L;

    private static final String UNIT_BS = "b/s";
    private static final String UNIT_KBS = "kb/s";

    //------------ fps ------------//
    private static final String UNIT_FPS = "fps";

    // ------------ duration ------------//
    public static final String DURATION_HOURS   = "HOURS";
    public static final String DURATION_MINUTES = "MINUTES";
    public static final String DURATION_SECONDS = "SECONDS";


    private UnitUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static String sizeFormat(long bytes){
        return sizeFormat(bytes, 0, false);
    }

    public static String sizeFormat(long bytes, int precision, boolean decimalForce){
        AssertUtils.isTrue(bytes >= 0, "UnitUtils: Illegal size number");

        // convert to double
        double value = (double) bytes;
        // unit
        String unit;

        if(bytes < ONE_KB){
            value /= ONE_B;
            unit   = UNIT_B;
        }else if(bytes < ONE_MB){
            value /= ONE_KB;
            unit   = UNIT_KB;
        }else if(bytes < ONE_GB){
            value /= ONE_MB;
            unit   = UNIT_MB;
        }else if(bytes < ONE_TB){
            value /= ONE_GB;
            unit   = UNIT_GB;
        }else if(bytes < ONE_PB){
            value /= ONE_TB;
            unit   = UNIT_TB;
        }else if(bytes < ONE_EB){
            value /= ONE_PB;
            unit   = UNIT_PB;
        }else{
            value /= ONE_EB;
            unit   = UNIT_EB;
        }

        StringBuilder pattern = new StringBuilder("#");
        if(precision > 0){
            pattern.append(".");
            pattern.append((decimalForce ? "0" : "#").repeat(precision));
        }

        DecimalFormat format = new DecimalFormat(pattern.toString());
        format.setRoundingMode(RoundingMode.DOWN);

        return format.format(value) + unit;
    }

    public static String durationFormat(long millis){
        return durationFormat(millis, DURATION_HOURS);
    }

    public static String durationFormat(long millis, String durationUnit){
        AssertUtils.isTrue(millis >= 0, "UnitUtils: Illegal millis number");

        Duration duration = Duration.ofMillis(millis / 1000);

        int      repeatCount   = 1;
        Object[] durationParts = null;

        switch(durationUnit){
            case DURATION_HOURS -> {
                repeatCount = 3;
                durationParts = new Object[]{
                    duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart()
                };
            }
            case DURATION_MINUTES -> {
                repeatCount = 2;
                durationParts = new Object[]{
                    duration.toMinutes(), duration.toSecondsPart()
                };
            }
            case DURATION_SECONDS -> {
                durationParts = new Object[]{
                    duration.toSeconds()
                };
            }
        }

        // pattern
        String pattern = StringUtils.repeat("%02d", ":", repeatCount);
        // return
        return String.format(pattern, durationParts);
    }

    public static String bitRateFormat(long bitRate){
        return bitRateFormat(bitRate, 0, false);
    }

    public static String bitRateFormat(long bitRate, int precision, boolean decimalForce){
        AssertUtils.isTrue(bitRate >= 0, "UnitUtils: Illegal bit rate number");

        // convert to double
        double value = (double) bitRate;
        // unit
        String unit;

        if(bitRate < ONE_KBS){
            value /= ONE_BS;
            unit   = UNIT_BS;
        }else{
            value /= ONE_KBS;
            unit   = UNIT_KBS;
        }

        StringBuilder pattern = new StringBuilder("#");
        if(precision > 0){
            pattern.append(".");
            pattern.append((decimalForce ? "0" : "#").repeat(precision));
        }

        DecimalFormat format = new DecimalFormat(pattern.toString());
        format.setRoundingMode(RoundingMode.DOWN);

        return format.format(value) + unit;
    }

    public static String fpsFormat(int fps){
        AssertUtils.isTrue(fps >= 0, "UnitUtils: Illegal fps number");

        return fps + UNIT_FPS;
    }
}
