package model;

public enum Tag {

  name("name"), time("time"), startDay("start-day"), start("start"), endDay("end-day"),
  end("end"), location("location"), online("online"), place("place"), users("users"), uid("uid");

  protected final String tag;

  Tag(String tag) {
    this.tag = tag;
  }
  public String toString(){
    return tag;
  }
}
