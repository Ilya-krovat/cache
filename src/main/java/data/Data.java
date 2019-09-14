package data;

import java.util.Date;

public class Data<D>
{
  private D data;
  private Date createDate;

  public  Data(D data)
  {
    this.data = data;
    this.createDate = new Date();
  }

  public D getData()
  {
    return data;
  }

  public Date getCreateDate()
  {
    return createDate;
  }
}
