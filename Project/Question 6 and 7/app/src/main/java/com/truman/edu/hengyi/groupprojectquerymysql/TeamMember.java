package com.truman.edu.hengyi.groupprojectquerymysql;

public class TeamMember
{
  private int STUDENT_ID = 0;
  private String FIRST_NAME = "";
  private String LAST_NAME = "";
  private double LAT = 0.0;
  private double LONG_PARAM = 0.0;


  public TeamMember(int student_id, String first_name, String last_name,
                    double lat, double long_param)
  {
    STUDENT_ID = student_id;
    FIRST_NAME = first_name;
    LAST_NAME = last_name;
    LAT = lat;
    LONG_PARAM = long_param;
  }

  public int getID()
  {
    return STUDENT_ID;
  }

  public void setID(int id)
  {
    STUDENT_ID = id;
  }

  public String getFirstName()
  {
    return FIRST_NAME;
  }

  public void setFirstName(String first_name)
  {
    FIRST_NAME = first_name;
  }

  public String getLastName()
  {
    return LAST_NAME;
  }

  public void setLastName(String last_name)
  {
    LAST_NAME = last_name;
  }

  public double getLat()
  {
    return LAT;
  }

  public void setLat(double lat)
  {
    LAT = lat;
  }

  public double getLong()
  {
    return LONG_PARAM;
  }

  public void setLong(double long_param)
  {
    LONG_PARAM = long_param;
  }
}
