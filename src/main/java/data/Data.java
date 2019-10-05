package data;

import java.util.Date;

public class Data<D>
{
  private D data;
  private Date createDate;
  private Date lastUseDate;
  private Integer numberOfUses;

  public Data(D data)
  {
    this.data = data;
    this.createDate = new Date();
    this.lastUseDate = new Date();
    this.numberOfUses = 0;
  }

  public D getData()
  {
    return data;
  }

  public void updateData()
  {
    lastUseDate = new Date();
    numberOfUses++;
  }

  public Integer getNumberOfUses()
  {
    return numberOfUses;
  }


  public Date getLastUseDate()
  {
    return lastUseDate;
  }

  public Date getCreateDate()
  {
    return createDate;
  }
}
