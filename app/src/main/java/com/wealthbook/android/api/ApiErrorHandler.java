package com.wealthbook.android.api;

import com.wealthbook.android.R;

import java.util.HashMap;
import java.util.Map;


class ApiErrorHandler {

    private static final Map<String, Integer> ERROR_MAP = createErrorMap();

    private static Map<String, Integer> createErrorMap() {
        Map<String, Integer> errorConstantMap = new HashMap<String, Integer>();
        errorConstantMap.put("INTERNAL_ERROR", R.string.INTERNAL_SERVER_ERROR);
        return errorConstantMap;
    }

    /**
     * Checks whether the look up table contains the message entry for the given error code.
     * @param errorCode the error code.
     * @return true if the mapping exists false otherwise.
     */
    private static boolean isResolvable(String errorCode) {
        return ERROR_MAP.containsKey(errorCode);
    }

    /**
     * Resolves the error code to the display message.
     * @param errorCode the error code.
     * @return the translated message equivalent of the error code.
     */
    static int resolve(String errorCode) {
        return isResolvable(errorCode) ? ERROR_MAP.get(errorCode) : R.string.INTERNAL_SERVER_ERROR;
    }
}
