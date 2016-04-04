package controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import forms.PartyRegistrationForm;

@Component
public class UserValidator implements Validator 
{
 
    public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
        return PartyRegistrationForm.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object target, Errors errors) 
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "notEmpty.partyRegistrationForm.firstName", "First name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "notEmpty.partyRegistrationForm.lastName", "Last name is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "notEmpty.partyRegistrationForm.phoneNumber", "Phone is required.");
        PartyRegistrationForm form = (PartyRegistrationForm) target;
        if(!StringUtils.isNumeric(form.getPhoneNumber()))
        {
            errors.rejectValue("phoneNumber","numberFormat.partyRegistrationForm.phoneNumber");
        }
        if(form.getPhoneNumber().length() != 8)
        {
            errors.rejectValue("phoneNumber","size.partyRegistrationForm.phoneNumber",new Object[]{"8"}, "Phone number must be of 8 characters.");
        }
    }
 
}