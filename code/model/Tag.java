package model;

public enum Tag {

  name("name"), startDay("start-day"), start("start"), endDay("end-day"),
  end("end"), online("online"), place("place"), users("users");

  protected final String tag;

  Tag(String tag) {
    this.tag = tag;
  }
  public String toString(){
    return tag;
  }
}
