package com.ubs.exercise.chatwall.util;

import com.ubs.exercise.chatwall.model.ChatLine;

public class ChatLineFormatter {
    private static final long MILLIS_IN_MINUTE = 60_000;
    private static final long MILLIS_IN_SECOND = 1_000;
    private static final String MINUTE = "minute";
    private static final String SECOND = "second";
    private static final String SEPARATOR = " - ";

    public static String getFormattedLine(final ChatLine chatLine, final long currentTime) {
        return formatLine(chatLine, currentTime, false);
    }

    public static String getFormattedLineWithUser(final ChatLine chatLine, final long currentTime) {
        return formatLine(chatLine, currentTime, true);
    }

    private static String formatLine(final ChatLine chatLine, final long currentTime, boolean withUser) {
        final long elapsedTimeInMillis = currentTime - chatLine.getTimeOfPost();

        long elapsedTimeInProperUnit;
        String properUnit;

        if (isOverMinute(elapsedTimeInMillis)) {
            elapsedTimeInProperUnit = elapsedTimeInMillis / MILLIS_IN_MINUTE;
            properUnit = MINUTE;
        } else {
            elapsedTimeInProperUnit = elapsedTimeInMillis / MILLIS_IN_SECOND;
            properUnit = SECOND;
        }

        return buildLine(chatLine.getUserName(), chatLine.getLine(), withUser, elapsedTimeInProperUnit, properUnit);
    }

    private static String buildLine(final String userName, final String line, boolean withUser,
            final long elapsedTimeInProperUnit, final String properUnit) {
        StringBuilder builder = new StringBuilder();

        if (withUser) {
        builder.append(userName);
            builder.append(SEPARATOR);
        }
        builder.append(line);
        builder.append(" (");
        builder.append(elapsedTimeInProperUnit);
        builder.append(" ");
        builder.append(properUnit);
        if (elapsedTimeInProperUnit > 1) {
            builder.append('s');
        }
        builder.append(" ago)");

        return builder.toString();
    }

    private static boolean isOverMinute(long millis) {
        return millis >= MILLIS_IN_MINUTE;
    }

}
