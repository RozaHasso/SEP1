
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class ParticipantFileAdapter implements Serializable
{
   private MyFileIO mfio;
   private String fileName;

   public ParticipantFileAdapter(String fileName)
   {
      mfio = new MyFileIO();
      this.fileName = fileName;
   }

   public ParticipantList loadParticipants()
   {
      ParticipantList participants = new ParticipantList();

      try
      {
         participants = (ParticipantList) mfio.readObjectFromFile(fileName);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File not found");
      }
      catch (IOException e)
      {
         System.out.println("IO Error reading file");
      }
      catch (ClassNotFoundException e)
      {
         System.out.println("Class Not Found");
      }
      return participants;

   }

   public void saveParticipants(ParticipantList participants)
   {
      try
      {
         mfio.writeToFile(fileName, participants);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File not found");
      }
      catch (IOException e)
      {
         System.out.println("IO Error writing to file");
      }
   }

}
