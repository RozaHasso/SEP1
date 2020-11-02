import java.io.Serializable;

public class Lecturer implements Serializable
{
   private String name;
   private String phoneNumber;
   private String email;
   private String category;
   
   public Lecturer(String name, String phoneNumber, String email, String category) 
   {
      this.name = name;
      this.phoneNumber = phoneNumber;
      this.email = email;
      this.category = category;
   }
   
   public void setName(String name)
   {
      this.name = name;
   }
   
   public void setPhoneNumber(String phoneNumber)
   {
      this.phoneNumber = phoneNumber;
   }
   
   public void setEmail(String email)
   {
      this.email = email;
   }
   
   public void setCategory(String category)
   {
      this.category = category;
   }
   
   public String getName() 
   {
      return name;
   }
   
   public String getPhoneNumber() 
   {
      return phoneNumber;
   }
   
   public String getEmail()
   {
      return email;
   }
   
   public String getCategory()
   {
      return category;
   }
   
   public boolean equals(Object obj)
   {
      if(!(obj instanceof Lecturer))
         return false;
      
      Lecturer other = (Lecturer)obj;
      return other.name.equals(name) && other.phoneNumber.equals(phoneNumber) && other.category.equals(category) && other.email.equals(email);
   }
   
   public String toString()
   {
      return name + " " + phoneNumber + " " + email + " " + category;
   }
   
   public Lecturer copy()
   {
      return new Lecturer(name, phoneNumber, email, category);
   }
}