import java.io.Serializable;
import java.util.ArrayList;
public class LecturerList implements Serializable
{
   private ArrayList<Lecturer> lecturersList;
   
   public LecturerList() 
   {
      lecturersList = new ArrayList<Lecturer>();
   }
   
   public void add(Lecturer lecturer)
   {
      lecturersList.add(lecturer);
   }
   
   public void set(Lecturer lecturer, int index)
   {
      lecturersList.add(index, lecturer);
   }
   
   public Lecturer get(int index)
   {
      return lecturersList.get(index);
   }
   
   public Lecturer get(String name, String phoneNumber)
   {
      int indexOfLecturer = -1; 
      
      for(int i = 0; i < lecturersList.size(); i++)
      {
         if((lecturersList.get(i).getName().equals(name) && lecturersList.get(i).getPhoneNumber().equals(phoneNumber)))
         {
            indexOfLecturer = i;
            return lecturersList.get(indexOfLecturer);
         }
      }
      return null;
   }
   
   public void remove(int index)
   {
      lecturersList.remove(index);
   }
   
   
   public int getIndex(String name, String phoneNumber)
   {
      int indexOfLecturer = -1;
      
      for(int i = 0; i < lecturersList.size(); i++)
      {
         if((lecturersList.get(i).getName().equals(name) && lecturersList.get(i).getPhoneNumber().equals(phoneNumber)))
         {
            indexOfLecturer = i;
         }
      }
      return indexOfLecturer;
   }
   
   public int size()
   {
      return lecturersList.size();
   }
   
   public String toString()
   {
      String returnStr = "";
      
      for(int i = 0; i < lecturersList.size(); i++)
      {
         returnStr = returnStr + lecturersList.get(i).toString();
      }
      
      return returnStr;
   }
   
   public boolean isEmpty()
   {
      return lecturersList.isEmpty();      
   }
}
