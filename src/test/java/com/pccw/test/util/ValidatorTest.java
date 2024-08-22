package com.pccw.test.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    public void testEmailValidator() throws Exception{

        assertTrue(EmailValidator.isValidEmail("mylie@qq.com"), "The condition should be true");
        assertFalse(EmailValidator.isValidEmail("mylieqq.com."), "The condition should be true");
    }

    @Test
    public void testPhoneNumberValidator() throws Exception{


        assertTrue(PhoneNumberValidator.isValidPhoneNumber("15901929444"), "The condition should be true");
        assertFalse(PhoneNumberValidator.isValidPhoneNumber("15341342."), "The condition should be true");
    }
}
