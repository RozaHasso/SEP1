import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class LecturerFileAdapter implements Serializable
{
   private MyFileIO mfio;
   private String fileName;

   public LecturerFileAdapter(String fileName)
   {
      mfio = new MyFileIO();
      this.fileName = fileName;
   }

   public LecturerList loadLecturers()
   {
      LecturerList lecturers = new LecturerList();

      try
      {
         lecturers = (LecturerList) mfio.readObjectFromFile(fileName);
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
      return lecturers;

   }

   public void saveLecturers(LecturerList lecturers)
   {
      try
      {
         mfio.writeToFile(fileName, lecturers);
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
