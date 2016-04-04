package forms;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Party registration form.
 */
public class PartyRegistrationForm  implements Serializable
{
   private static final long serialVersionUID = 1L;

   private String firstName;
   private String lastName;
   private String phoneNumber;

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public String getPhoneNumber()
   {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber)
   {
      this.phoneNumber = phoneNumber;
   }
   
   @Override
   public String toString()
   {
      return ReflectionToStringBuilder.toString(this);
   }
}
