import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class SponsorFileAdapter implements Serializable
{
   private MyFileIO mfio;
   private String fileName;

   public SponsorFileAdapter(String fileName)
   {
      mfio = new MyFileIO();
      this.fileName = fileName;
   }

   public SponsorList loadSponsors()
   {
      SponsorList sponsors = new SponsorList();

      try
      {
         sponsors = (SponsorList) mfio.readObjectFromFile(fileName);
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
      return sponsors;

   }

   public void saveSponsors(SponsorList sponsors)
   {
      try
      {
         mfio.writeToFile(fileName, sponsors);
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
