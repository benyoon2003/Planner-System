public enum Day {

  Sunday("Sunday"), Monday("Monday"), Tuesday("Tuesday"), Wednesday("Wednesday"),
  Thursday("Thursday"), Friday("Friday"), Saturday("Saturday");

  protected final String day;

  Day(String day) {
    this.day = day;
  }
  public String toString(){
    return day;
  }
}
