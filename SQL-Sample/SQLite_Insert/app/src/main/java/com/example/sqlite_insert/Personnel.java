/**
 * It's a class that holds the data for a single row in the database
 */
package com.example.sqlite_insert;

public class Personnel
{
  private String NAME = "";
  private String JOB = "";
  private int personID = 0;

  // It's a constructor. It's a special method that is called when an object is
  // created from a class and it allows the programmer to set the initial values
  // for the object's attributes.
  public Personnel(int id, String name, String job)
  {
    personID = id;
    NAME = name;
    JOB = job;
  }

  /**
   * This function returns the personID of the person.
   * @return The personID
   */
  public int getID()
  {
    return personID;
  }

  /**
   * This function sets the personID variable to the value of the id parameter.
   * @param id The ID of the person.
   */
  public void setID(int id)
  {
    personID = id;
  }

  /**
   * This function returns the name of the object.
   * @return The name of the class.
   */
  public String getName()
  {
    return NAME;
  }

  /**
   * This function sets the value of the NAME variable to the value of the name
   * parameter.
   * @param name The name of the command.
   */
  public void setName(String name)
  {
    NAME = name;
  }

  /**
   * This function returns the value of the JOB variable.
   * @return The job variable is being returned.
   */
  public String getJob()
  {
    return JOB;
  }

  /**
   * This function sets the value of the JOB variable to the value of the job
   * variable.
   * @param job The name of the job you want to run.
   */
  public void setJob(String job)
  {
    JOB = job;
  }
}
