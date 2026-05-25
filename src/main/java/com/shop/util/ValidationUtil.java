package com.shop.util;

public class ValidationUtil {
    private static final String ID_CARD_PATTERN = "^[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\d{3}[\\dXx]$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    private ValidationUtil() {}

    public static boolean isValidIdCard(String idCard) {
        return idCard != null && idCard.matches(ID_CARD_PATTERN);
    }

    public static boolean isStrongPassword(String password) {
        return password != null && password.matches(PASSWORD_PATTERN);
    }

    public static boolean isSupportedRole(String role) {
        return "buyer".equals(role) || "seller".equals(role) || "admin".equals(role);
    }
}
