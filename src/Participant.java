import java.io.Serializable;

public class Participant implements Serializable
{
   private String name;
   private String address;
   private String phoneNumber;
   private String email;
   private boolean isMember;
   private int paymentYear;
   private MyDate membershipDate;

   public Participant(String name, String address, String phoneNumber,
         String email, boolean isMember, int paymentYear, MyDate membershipDate)
   {
      this.name = name;
      this.address = address;
      this.phoneNumber = phoneNumber;
      this.email = email;
      this.isMember = isMember;
      this.paymentYear = paymentYear;
      this.membershipDate = membershipDate;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public void setAddress(String address)
   {
      this.address = address;
   }

   public void setPhoneNumber(String phoneNumber)
   {
      this.phoneNumber = phoneNumber;
   }

   public void setEmail(String email)
   {
      this.email = email;

   }

   public void setIsMember(boolean isMember)
   {
      this.isMember = isMember;

   }

   public void setIsPaymentYear(int paymentYear)
   {
      this.paymentYear = paymentYear;

   }

   public void setMembershipDate(MyDate membershipDate)
   {
      this.membershipDate = membershipDate;

   }

   public String getName()
   {
      return name;
   }

   public String getAddress()
   {
      return address;
   }

   public String getPhoneNumber()
   {
      return phoneNumber;
   }

   public String getEmail()
   {
      return email;
   }

   public int getPaymentYear()
   {
      return paymentYear;
   }

   public MyDate getmembershipDate()
   {
      return membershipDate;
   }

   public boolean getIsMember()
   {
      return isMember;
   }

   public String toString()
   {
      return name + " " + address + " " + phoneNumber + " " + email;
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Participant))
         return false;

      Participant other = (Participant) obj;
      return other.name.equals(name) && other.address.equals(address)
            && other.email.equals(email) && other.phoneNumber == phoneNumber;

   }

   public Participant copy()
   {
      if (isMember)
         return new Participant(name, address, phoneNumber, email, isMember,
               paymentYear, membershipDate.copy());
      else
         return new Participant(name, address, phoneNumber, email, isMember, 0,
               null);
   }
}