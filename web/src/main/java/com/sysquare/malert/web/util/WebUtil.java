package com.sysquare.malert.web.util;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.sysquare.malert.model.domain.User;

public class WebUtil {
    
    public static final Pattern emailPattern = Pattern.compile("^|\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", Pattern.CASE_INSENSITIVE);

    public static boolean isValidEmail(String email) {
        if(email == null || email.trim().isEmpty()) {
            return false;
        }
        return emailPattern.matcher(email).matches();
    }
    
    public static User getLoginUser(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loggedInUser");
        return user;
    }

    public static void setErrors(BindingResult result, List<String> warnings) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            if (fieldErrors != null && !fieldErrors.isEmpty()) {
                for (FieldError fieldError : fieldErrors) {
                    warnings.add(fieldError.getField() + ":" + fieldError.getDefaultMessage() + "[" + fieldError.getCode() + "]");
                }
            }
        }
    }
}
