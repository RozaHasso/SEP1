import java.io.Serializable;

public class Event implements Serializable
{
   private String title;
   private String type;
   private double duration;
   private String category;
   private int places;
   private boolean isFinalized;
   private String description;

   private LecturerList lecturers;
   private ParticipantList participants;
   private SponsorList sponsors;
   private MyDate startDate;

   public Event(String title, String type, double duration, String category,
         int places, boolean isFinalised, String description, MyDate startDate)
   {
      this.title = title;
      this.type = type;
      this.duration = duration;
      this.category = category;
      this.places = places;
      this.isFinalized = isFinalised;
      this.description = description;
      this.startDate = startDate.copy();
      this.lecturers = new LecturerList();
      this.participants = new ParticipantList();
      this.sponsors = new SponsorList();
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public void setType(String type)
   {
      this.type = type;
   }

   public void setDuration(double duration)
   {
      this.duration = duration;
   }

   public void setCategory(String category)
   {
      this.category = category;
   }

   public void setPlaces(int places)
   {
      this.places = places;
   }

   public void setIsFinalizes(boolean isFinalized)
   {
      this.isFinalized = isFinalized;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public void setStartDate(MyDate startDate)
   {
      this.startDate = startDate.copy();
   }

   public String getTitle()
   {
      return title;
   }

   public String getType()
   {
      return type;
   }

   public double getDuration()
   {
      return duration;
   }

   public String getCategory()
   {
      return category;
   }

   public int getPlaces()
   {
      return places;
   }

   public boolean getIsFinalized()
   {
      return isFinalized;
   }

   public String getDescription()
   {
      return description;
   }

   public MyDate getStartDate()
   {
      return startDate;
   }

   public ParticipantList getParticipants()
   {
      return participants;
   }

   public LecturerList getLecturers()
   {
      return lecturers;
   }

   public SponsorList getSponsors()
   {
      return sponsors;
   }

   public int getNumberOfParicipants()
   {
      if (!participants.isEmpty())
         return participants.size();
      else
         return 0;
   }

   public int getNumberOfMembers()
   {
      int membersNum = 0;
      for (int i = 0; i < getNumberOfParicipants(); i++)
      {
         if (participants.get(i).getIsMember())
            membersNum++;
      }
      return membersNum;
   }

   public int getFreeSpacesLeft()
   {
      if (participants.isEmpty())
         return places;
      else
         return places - getNumberOfParicipants();
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Event))
         return false;

      Event other = (Event) obj;
      return other.title.equals(title) && other.type.equals(type)
            && other.duration == duration && other.category.equals(category)
            && other.places == places && other.isFinalized == isFinalized
            && other.description.equals(description)
            && other.lecturers.equals(lecturers)
            && other.sponsors.equals(sponsors)
            && other.participants.equals(participants)
            && other.startDate.equals(startDate);
   }

   public String toString()
   {
      return title + ", type: " + type + ", duration: " + duration + ", "
            + category + " " + places + ", is finalised: " + isFinalized;
   }
}
