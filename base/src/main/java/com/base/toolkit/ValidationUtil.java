package com.base.toolkit;

import com.base.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ValidationUtil {
    public static <T> void validBean(T object) {
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                checkAnnotations(field.get(object), field.getAnnotations());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <T> void validList(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new BaseException("数组不能为空");
        }
        list.forEach(ValidationUtil::validBean);
    }

    private static void checkAnnotations(Object value, Annotation[] annotations) {
        Arrays.stream(annotations).forEach(annotation -> {
            if (annotation instanceof NotNull) {
                if (value == null) throw new BaseException(((NotNull) annotation).message());
            } else if (annotation instanceof Max) {
                if (value != null && Long.valueOf(value.toString()) > ((Max) annotation).value()) {
                    throw new BaseException(((Max) annotation).message());
                }
            } else if (annotation instanceof Min) {
                if (value != null && Long.valueOf(value.toString()) < ((Min) annotation).value()) {
                    throw new BaseException(((Min) annotation).message());
                }
            } else if (annotation instanceof DecimalMax) {
                if (value != null &&
                        new BigDecimal(value.toString()).compareTo(
                                new BigDecimal(((DecimalMax) annotation).value())) > 0) {
                    throw new BaseException(((DecimalMax) annotation).message());
                }
            } else if (annotation instanceof DecimalMin) {
                if (value != null &&
                        new BigDecimal(value.toString()).compareTo(new BigDecimal(((DecimalMin) annotation).value())) < 0) {
                    throw new BaseException(((DecimalMin) annotation).message());
                }
            } else if (annotation instanceof Size) {
                if (value != null) {
                    int length = value.toString().length();
                    if (length > ((Size) annotation).max() || length < ((Size) annotation).min()) {
                        throw new BaseException(((Size) annotation).message());
                    }
                }
            } else if (annotation instanceof AssertTrue) {
                if (value != null && !Boolean.valueOf(value.toString())) {
                    throw new BaseException(((AssertTrue) annotation).message());
                }
            } else if (annotation instanceof AssertFalse) {
                if (value != null && Boolean.valueOf(value.toString())) {
                    throw new BaseException(((AssertFalse) annotation).message());
                }
            } else if (annotation instanceof Digits) {
                if (value != null && !StringUtils.isNumeric(value.toString())) {
                    throw new BaseException(((Digits) annotation).message());
                }
            }
        });
    }

}
