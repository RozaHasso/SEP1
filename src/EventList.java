import java.io.Serializable;
import java.util.ArrayList;
public class EventList implements Serializable
{
   private ArrayList<Event> eventsList;
   
   public EventList() 
   {
     eventsList = new ArrayList<Event>();
   }
   
   public void add(Event event)
   {
      eventsList.add(event);
   }
   
   public void remove(int index)
   {
      eventsList.remove(index);
   }
   
   public void set(Event event, int index)
   {
      eventsList.add(index, event);
   }
   
   public Event get(int index)
   {
      return eventsList.get(index);
   }
   
   public Event get(String title, MyDate startDate)
   {
      int indexOfEvent = -1; 
      
      for(int i = 0; i < eventsList.size(); i++)
      {
         if(eventsList.get(i).getTitle().equals(title) && eventsList.get(i).getStartDate().equals(startDate))
         {
            indexOfEvent = i;
            return eventsList.get(indexOfEvent);
         }
      }
      return null;
   }
   
   public int getIndex(String title, MyDate startDate)
   {
      int indexOfEvent = -1;
      
      for(int i = 0; i < eventsList.size(); i++)
      {
         if(eventsList.get(i).getTitle().equals(title) && eventsList.get(i).getStartDate().equals(startDate))
         {
            indexOfEvent = i;
         }
      }
      return indexOfEvent;
   }
   
   public int size()
   {
      return eventsList.size();
   }
   
   public String toString()
   {
      String returnStr = "";
      
      for(int i = 0; i < eventsList.size(); i++)
      {
         returnStr = returnStr + eventsList.get(i).toString();
      }
      
      return returnStr;
   }
}
