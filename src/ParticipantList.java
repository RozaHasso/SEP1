import java.io.Serializable;
import java.util.ArrayList;
public class ParticipantList  implements Serializable
{
   private ArrayList<Participant> participantsList;
   
   public ParticipantList() 
   {
      participantsList = new ArrayList<Participant>();
   }
   
   public void add(Participant participant)
   {
      participantsList.add(participant);
   }
   public void remove(int index)
   {
      participantsList.remove(index);
   }
   
   public void set(Participant participant, int index)
   {
      participantsList.add(index, participant);
   }
   
   public Participant get(int index)
   {
      return participantsList.get(index);
   }
   
   public Participant get(String name, String phoneNumber)
   {
      int indexOfParticipant = -1; 
      
      for(int i = 0; i < participantsList.size(); i++)
      {
         if((participantsList.get(i).getName().equals(name) && participantsList.get(i).getPhoneNumber().equals(phoneNumber)))
         {
            indexOfParticipant = i;
            return participantsList.get(indexOfParticipant);
         }
      }
      return null;
   }
   
   public int getIndex(String name, String phoneNumber)
   {
      int indexOfParticipant = -1;
      
      for(int i = 0; i < participantsList.size(); i++)
      {
         if((participantsList.get(i).getName().equals(name) && participantsList.get(i).getPhoneNumber().equals(phoneNumber)))
         {
            indexOfParticipant = i;
         }
      }
      return indexOfParticipant;
   }
   
   public int size()
   {
      return participantsList.size();
   }
   
   public String toString()
   {
      String returnStr = "";
      
      for(int i = 0; i < participantsList.size(); i++)
      {
         returnStr = returnStr + participantsList.get(i).toString();
      }
      
      return returnStr;
   }
   
   public boolean isEmpty()
   {
      return participantsList.isEmpty();
   }
}
