package com.wealthbook.android.eventbus;

public class Events {
    public static class PendingAlertType {
        int id;

        public PendingAlertType(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
