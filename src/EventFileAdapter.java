import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class EventFileAdapter implements Serializable
{
   private MyFileIO mfio;
   private String fileName;

   public EventFileAdapter(String fileName)
   {
      mfio = new MyFileIO();
      this.fileName = fileName;
   }

   public EventList getAllEvents()
   {
      EventList events = new EventList();

      try
      {
         events = (EventList) mfio.readObjectFromFile(fileName);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("Events file not found");
      }
      catch (IOException e)
      {
         System.out.println("IO Error reading events file");
      }
      catch (ClassNotFoundException e)
      {
         System.out.println("EventList class Not Found");
      }
      return events;

   }

   public void saveEvents(EventList events)
   {
      try
      {
         mfio.writeToFile(fileName, events);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("Events file not found");
      }
      catch (IOException e)
      {
         System.out.println("IO Error writing to events file");
      }
   }

}
