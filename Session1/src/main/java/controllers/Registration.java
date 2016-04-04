package controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import forms.PartyRegistrationForm;

@Controller
public class Registration
{

   @Autowired
   UserValidator validator;
   
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public ModelAndView registerToParty(@ModelAttribute("partyRegistrationForm") PartyRegistrationForm partyRegistrationForm, ModelMap model)
   {
      return new ModelAndView("registration", "command", new PartyRegistrationForm());
   }

   @RequestMapping(value = "/confirmRegistration", method = RequestMethod.POST)
   public ModelAndView confirmRegistration(@CookieValue(value = "registrationCookie", defaultValue = "") String registrationCookie, @Valid @ModelAttribute("partyRegistrationForm") PartyRegistrationForm partyRegistrationForm, BindingResult result, ModelMap model, HttpServletResponse response)
   {
      //Validation code
      validator.validate(partyRegistrationForm, result);
      
      if(result.hasErrors())
      {
         return new ModelAndView("registration", "command", partyRegistrationForm);
      }

      if(StringUtils.isNotBlank(registrationCookie))
      {
         model.addAttribute("result", "error.operation");
         return new ModelAndView("confirmation");
      }
      
      Cookie cookie = new Cookie("registrationCookie", partyRegistrationForm.getFirstName() + " " + partyRegistrationForm.getLastName());         
      response.addCookie(cookie); //put cookie in response 
      model.addAttribute("result", "success.operation");
      return new ModelAndView("confirmation");
   }
}
