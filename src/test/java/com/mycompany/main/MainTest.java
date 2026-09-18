package com.mycompany.main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the static helper methods in Main.java.
 *
 * NOTE: registeredUsername / registeredPassword / registeredcellPhone are
 * package-private static fields on Main, so this test class (placed in the
 * same package) can set/reset them directly instead of driving the
 * Scanner-based registerUser() method, which reads from System.in.
 */
class MainTest {

    @AfterEach
    void resetState() {
        // Keep tests independent by clearing shared static state after each test
        Main.registeredUsername = null;
        Main.registeredPassword = null;
        Main.registeredcellPhone = null;
    }

    // ---------- checkUserName ----------

    @Test
    void checkUserName_validUsernameWithUnderscore_returnsTrue() {
        // Exactly 5 characters, contains an underscore
        assertTrue(Main.checkUserName("ab_cd"));
    }

    @Test
    void checkUserName_correctLengthButNoUnderscore_returnsFalse() {
        assertFalse(Main.checkUserName("abcde"));
    }

    @Test
    void checkUserName_underscorePresentButWrongLength_returnsFalse() {
        assertFalse(Main.checkUserName("ab_cde"));
    }

    @Test
    void checkUserName_emptyString_returnsFalse() {
        assertFalse(Main.checkUserName(""));
    }

    // ---------- checkPasswordComplexity ----------

    @Test
    void checkPasswordComplexity_meetsAllRequirements_returnsTrue() {
        // 8+ chars, upper, lower, digit, special character
        assertTrue(Main.checkPasswordComplexity("Abcdef1!"));
    }

    @Test
    void checkPasswordComplexity_tooShort_returnsFalse() {
        assertFalse(Main.checkPasswordComplexity("Ab1!"));
    }

    @Test
    void checkPasswordComplexity_missingUppercase_returnsFalse() {
        assertFalse(Main.checkPasswordComplexity("abcdef1!"));
    }

    @Test
    void checkPasswordComplexity_missingLowercase_returnsFalse() {
        assertFalse(Main.checkPasswordComplexity("ABCDEF1!"));
    }

    @Test
    void checkPasswordComplexity_missingDigit_returnsFalse() {
        assertFalse(Main.checkPasswordComplexity("Abcdefg!"));
    }

    @Test
    void checkPasswordComplexity_missingSpecialCharacter_returnsFalse() {
        assertFalse(Main.checkPasswordComplexity("Abcdefg1"));
    }

    @Test
    void checkPasswordComplexity_emptyString_returnsFalse() {
        assertFalse(Main.checkPasswordComplexity(""));
    }

    // ---------- checkCellPhoneNumber ----------
    //
    // NOTE ON A LIKELY BUG: the method's regex is "^//+27[0-9]{9}$". In that
    // pattern, "//+" means "one or more literal '/' characters", not an
    // escaped '+'. A correct South African number like "+27821234567" will
    // therefore NOT match. It looks like the intended regex was
    // "^\\+27[0-9]{9}$". The tests below document the CURRENT (buggy)
    // behavior; if the regex is fixed, the two tests marked below should be
    // swapped.

    @Test
    void checkCellPhoneNumber_realWorldPlus27Number_currentlyReturnsFalseDueToRegexBug() {
        assertFalse(Main.checkCellPhoneNumber("+27821234567"));
    }

    @Test
    void checkCellPhoneNumber_slashesInsteadOfPlus_matchesCurrentRegex() {
        // Demonstrates what the current (buggy) regex actually accepts:
        // one-or-more '/' characters followed by "27" and 9 digits.
        assertTrue(Main.checkCellPhoneNumber("/27821234567"));
    }

    @Test
    void checkCellPhoneNumber_tooFewDigits_returnsFalse() {
        assertFalse(Main.checkCellPhoneNumber("/2782123456"));
    }

    @Test
    void checkCellPhoneNumber_tooManyDigits_returnsFalse() {
        assertFalse(Main.checkCellPhoneNumber("/278212345678"));
    }

    @Test
    void checkCellPhoneNumber_emptyString_returnsFalse() {
        assertFalse(Main.checkCellPhoneNumber(""));
    }

    // ---------- loginUser ----------

    @Test
    void loginUser_matchingCredentials_returnsTrue() {
        Main.registeredUsername = "ab_cd";
        Main.registeredPassword = "Abcdef1!";

        assertTrue(Main.loginUser("ab_cd", "Abcdef1!"));
    }

    @Test
    void loginUser_wrongUsername_returnsFalse() {
        Main.registeredUsername = "ab_cd";
        Main.registeredPassword = "Abcdef1!";

        assertFalse(Main.loginUser("wrong", "Abcdef1!"));
    }

    @Test
    void loginUser_wrongPassword_returnsFalse() {
        Main.registeredUsername = "ab_cd";
        Main.registeredPassword = "Abcdef1!";

        assertFalse(Main.loginUser("ab_cd", "WrongPass1!"));
    }

    @Test
    void loginUser_noUserRegistered_returnsFalse() {
        // registeredUsername/registeredPassword are null (reset in @AfterEach
        // / never set), so any login attempt should fail rather than NPE.
        assertFalse(Main.loginUser("ab_cd", "Abcdef1!"));
    }

    // ---------- returnLoginStatus ----------

    @Test
    void returnLoginStatus_successfulLogin_returnsWelcomeMessage() {
        assertEquals("Welcome, it is great to see you again.",
                Main.returnLoginStatus(true));
    }

    @Test
    void returnLoginStatus_failedLogin_returnsErrorMessage() {
        assertEquals("Username or password incorrect, please try again.",
                Main.returnLoginStatus(false));
    }
}
