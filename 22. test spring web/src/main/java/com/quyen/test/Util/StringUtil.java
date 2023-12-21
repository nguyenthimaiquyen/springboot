package com.quyen.test.Util;

import org.springframework.util.StringUtils;

public class StringUtil {
    public static String escapeWildCardCharacter(String str) {
        if (!StringUtils.hasText(str)) {
            return null;
        }
        return str.trim()
                .replace("%", "\\%")
                .replace("[", "\\[")
                .replace("]", "\\]")
                .replace("{", "\\{")
                .replace("}", "\\}")
                .replace("-", "\\-")
                .replace("^", "\\^")
                .replace("_", "\\_");
    }
}
