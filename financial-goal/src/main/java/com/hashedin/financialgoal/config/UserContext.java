package com.hashedin.financialgoal.config;



public class UserContext {
    private static final ThreadLocal<Integer> userIdHolder = new ThreadLocal<>();
    private static final ThreadLocal<String> rolesHolder = new ThreadLocal<>();

    public static void setUserId(int userId) {
        userIdHolder.set(userId);
    }

    public static int getUserId() {
        return userIdHolder.get();
    }

    public static void setRoles(String role) {
        rolesHolder.set(role);
    }

    public static String getRoles() {
        return rolesHolder.get();
    }

    public static void clear() {
        userIdHolder.remove();
        rolesHolder.remove();
    }
}

