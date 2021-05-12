package com.incs83.hrm.utils;

import com.incs83.hrm.entities.Parent;

import java.sql.Timestamp;
import java.util.Date;

public final class CommonUtils {

    public static Timestamp getCurrentTime() {
        return new Timestamp(new Date().getTime());
    }

    public static void createAudit(Object object) {
        if (object instanceof Parent) {
            Parent parent = (Parent) object;
            parent.setCreatedAt(getCurrentTime());
            parent.setCreatedBy("Dev_Department");
        }
    }

    public static void updateAudit(Object object) {
        if (object instanceof Parent) {
            Parent parent = (Parent) object;
            parent.setUpdatedAt(getCurrentTime());
            parent.setUpdatedBy("Dev_Department");
        }
    }

}
