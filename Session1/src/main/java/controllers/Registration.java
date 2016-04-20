package controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
   //public ModelAndView confirmRegistration(@CookieValue(value = "registrationCookie", defaultValue = "") String registrationCookie, @Valid @ModelAttribute("partyRegistrationForm") PartyRegistrationForm partyRegistrationForm, BindingResult result, ModelMap model, HttpServletResponse response)
   public ModelAndView confirmRegistration(@Valid @ModelAttribute("partyRegistrationForm") PartyRegistrationForm partyRegistrationForm, BindingResult result, ModelMap model, HttpServletResponse response, HttpServletRequest request)
   {
      //Validation code
      validator.validate(partyRegistrationForm, result);
      
      if(result.hasErrors())
      {
         return new ModelAndView("registration", "command", partyRegistrationForm);
      }

      String cookieName = partyRegistrationForm.getFirstName() + partyRegistrationForm.getLastName();
      boolean cookieFound = false;
      Cookie[] cookies = request.getCookies();

      if(cookies != null)
      {
         for(Cookie cookie : cookies)
         {
            if(cookie.getName().equals(cookieName))
            {
               cookieFound = true;
               break;
            }
         }
      }
      
      if(cookieFound)
      {
         model.addAttribute("result", "error.operation");
         return new ModelAndView("confirmation");
      }
      
      Cookie cookie = new Cookie(cookieName, "true");  
      cookie.setPath("/");
      cookie.setHttpOnly(true);
      response.addCookie(cookie); //put cookie in response 
      model.addAttribute("result", "success.operation");
      return new ModelAndView("confirmation");
   }
}
