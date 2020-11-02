import java.io.Serializable;

public class MyDate implements Serializable
{
   private int day;
   private int month;
   private int year;
   private int hour;
   private int minute;

   public MyDate(int day, int month, int year)
   {
      this.day = day;
      this.month = month;
      this.year = year;
      this.hour = 0;
      this.minute = 0;
   }

   public MyDate(int day, int month, int year, int hour, int minute)
   {
      this.day = day;
      this.month = month;
      this.year = year;
      this.hour = hour;
      this.minute = minute;
   }

   public void setDay(int day)
   {
      this.day = day;
   }

   public void setMonth(int month)
   {
      this.month = month;
   }

   public void setYear(int year)
   {
      this.year = year;
   }

   public void setHour(int hour)
   {
      this.hour = hour;
   }

   public void setMinute(int minute)
   {
      this.minute = minute;
   }

   public int getDay()
   {
      return day;
   }

   public int getMonth()
   {
      return month;
   }

   public int getYear()
   {
      return year;
   }

   public int getHour()
   {
      return hour;
   }

   public int getMinute()
   {
      return minute;
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof MyDate))
         return false;

      MyDate other = (MyDate) obj;
      return other.day == day && other.month == month && other.year == year
            && other.hour == hour && other.minute == minute;
   }

   public MyDate copy()
   {
      return new MyDate(day, month, year, hour, minute);
   }

   public String toString()
   {
      
      if (hour == 0 && minute == 0)
         return day + "/" + month + "/" + year;
      else
         return ((day<10)?"0"+day:day) + "/" + ((month<10)?"0"+month:month) + "/" + year + "  " + ((hour<10)?"0"+hour:hour) + ":" + ((minute<10)?"0"+minute:minute);
   }
}
