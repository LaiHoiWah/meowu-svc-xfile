package com.meowu.svc.babylon.commons.utils;

import com.meowu.starter.web.commons.mvc.constant.RequestHeaderConstants;
import com.meowu.starter.web.commons.utils.ServletRequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RangeUtils{

    // regular
    private final static String REGEX = "bytes=(\\d+)-(\\d+)?";
    // 1MB
    private final static long RANGE_SIZE = 1024 * 1024;

    private RangeUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static Range parse(HttpServletRequest request){
        if(Objects.isNull(request)){
            return Range.DEFAULT_RANGE;
        }

        // get range header
        String header = ServletRequestUtils.getHeader(request, RequestHeaderConstants.RANGE);
        // no range header
        if(StringUtils.isBlank(header)){
            return Range.FULL_RANGE;
        }

        // parse with regex
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(StringUtils.strip(header));

        if(matcher.find()){
            long startPoint = Long.parseLong(matcher.group(1));
            long endPoint   = startPoint + RANGE_SIZE - 1;

            // has end point
            String endPointString = matcher.group(2);
            if(StringUtils.isNotBlank(endPointString)){
                endPoint = Long.parseLong(endPointString);
            }

            return new Range(startPoint, endPoint);
        }

        return Range.DEFAULT_RANGE;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Range{

        public final static Range DEFAULT_RANGE = new Range(0, RANGE_SIZE - 1);
        public final static Range FULL_RANGE = new Range(0, -1);

        private long startPoint;
        private long endPoint;
    }
}
