package org.example.backFrontEnd;

public class StringDivider {
    public static KeyValuePair divideString(String inputString) {
        KeyValuePair keyValuePair;
        int firstSpaceIndex = inputString.indexOf(' ');

        if (firstSpaceIndex >= 0) {
            keyValuePair = new KeyValuePair(inputString.substring(0, firstSpaceIndex),
                    inputString.substring(firstSpaceIndex + 1));
        } else {
            keyValuePair = new KeyValuePair(inputString, "");
        }

        return keyValuePair;
    }
}
