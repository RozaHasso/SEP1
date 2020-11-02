import java.io.Serializable;
import java.util.ArrayList;
public class SponsorList implements Serializable
{
   private ArrayList<Sponsor> sponsorsList;
   
   public SponsorList()
   {
      sponsorsList = new ArrayList<>();
   }
   
   public void add(Sponsor sponsor)
   {
      sponsorsList.add(sponsor);
   }
   
   public void set(Sponsor sponsor, int index)
   {
      sponsorsList.add(index, sponsor);
   }
   
   public Sponsor get(int index)
   {
      return sponsorsList.get(index);
   }
   
   public Sponsor get(String name, String phoneNumber)
   {
      //?????????????????????
      int indexOfSponsor = -1; 
      
      for(int i = 0; i < sponsorsList.size(); i++)
      {
         if((sponsorsList.get(i).getName().equals(name) && sponsorsList.get(i).getPhoneNumber().equals(phoneNumber)))
         {
            indexOfSponsor = i;
            return sponsorsList.get(indexOfSponsor);
         }
      }
      return null;
   }
   
   public int getIndex(String name, String phoneNumber)
   {
      int indexOfSponsor = -1;
      
      for(int i = 0; i < sponsorsList.size(); i++)
      {
         if((sponsorsList.get(i).getName().equals(name) && sponsorsList.get(i).getPhoneNumber().equals(phoneNumber)))
         {
            indexOfSponsor = i;
         }
      }
      return indexOfSponsor;
   }
   
   public void remove(int index)
   {
      sponsorsList.remove(index);
   }
   
   public int size()
   {
      return sponsorsList.size();
   }
   
   public String toString()
   {
      String returnStr = "";
      
      for(int i = 0; i < sponsorsList.size(); i++)
      {
         returnStr = returnStr + sponsorsList.get(i).toString() + "\n";
      }
      
      return returnStr;
   }
   
   public boolean isEmpty()
   {
      return sponsorsList.isEmpty();      
   }
}