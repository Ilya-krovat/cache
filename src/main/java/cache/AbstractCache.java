package cache;

import data.Data;
import defaultOptions.Options;

import java.util.*;

public abstract class AbstractCache implements Cache
{
  private Map<Object,Data> cache = new LinkedHashMap<>();
  private Integer checkPeriod;
  private Integer lifeTime;

  public AbstractCache(Options options)
  {
    this.checkPeriod = options.gerCheckPeriod();
    this.lifeTime = options.getLifeTime();

    startCache();
  }

  @Override
  public void put(Object key, Object value)
  {
    cache.put(key, new Data(value));
  }

  @Override
  public Object get(Object key)
  {
    try
    {
      return cache.get(key).getData();
    }
    catch (NullPointerException e)
    {
      return null;
    }
  }

  private void startCache()
  {
    Runnable task = () ->
    {
      while (true)
      {
        Date currentTime = new Date();
        Iterator<Data> itr = cache.values().iterator();
        boolean flag = true;
        while (itr.hasNext() && flag)
        {
          if (itr.next().getCreateDate().getTime() + lifeTime < currentTime.getTime())
            itr.remove();
          else
            flag = false;
        }
        try
        {
          Thread.currentThread().sleep(checkPeriod);
        }
        catch (InterruptedException e)
        {
          // do nothing
        }
      }
    };
    Thread thread = new Thread(task);
    thread.start();
  }
}
