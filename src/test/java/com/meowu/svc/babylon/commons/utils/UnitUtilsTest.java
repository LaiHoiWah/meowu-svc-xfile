package com.meowu.svc.babylon.commons.utils;

import org.junit.Test;

public class UnitUtilsTest{

    @Test
    public void sizeFormat(){
        long size_1 = 176380462L;
        long size_2 = 70937538L;
        long size_3 = 12043L;
        long size_4 = 300L;
        long size_5 = 1024;

        System.out.println(UnitUtils.sizeFormat(size_1));
        System.out.println(UnitUtils.sizeFormat(size_2));
        System.out.println(UnitUtils.sizeFormat(size_3));
        System.out.println(UnitUtils.sizeFormat(size_4));
        System.out.println(UnitUtils.sizeFormat(size_5));
    }

    @Test
    public void durationFormat(){
        long duration = 2853546667L;

        System.out.println(UnitUtils.durationFormat(duration, UnitUtils.DURATION_HOURS));
        System.out.println(UnitUtils.durationFormat(duration, UnitUtils.DURATION_MINUTES));
        System.out.println(UnitUtils.durationFormat(duration, UnitUtils.DURATION_SECONDS));
    }

    @Test
    public void bitRateFormat(){
        long bitRate_1 = 416317L;
        long bitRate_2 = 75542L;

        System.out.println(UnitUtils.bitRateFormat(bitRate_1));
        System.out.println(UnitUtils.bitRateFormat(bitRate_2));
    }
}
