package cache;

import data.Data;
import defaultOptions.Options;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MemoryCache<V> implements Cache<V>
{

  private Map<String, Data<V>> cache = new LinkedHashMap<>();
  private Integer checkPeriod;
  private Integer lifeTime;
  private Integer cacheCapacity;

  public MemoryCache(Options options)
  {
    this.checkPeriod = options.gerCheckPeriod();
    this.lifeTime = options.getLifeTime();
    this.cacheCapacity = options.getCacheCapacity();

    startCache();
  }

  @Override
  public Integer size()
  {
    try
    {
      return cache.size();
    }
    catch (NullPointerException o)
    {
      return 0;
    }
  }

  @Override
  public void put(String key, V value)
  {
    cache.put(key, new Data<>(value));
  }

  @Override
  public V get(String key)
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
      while (!Thread.currentThread().isInterrupted())
      {
        caching();

        try
        {
          TimeUnit.MILLISECONDS.sleep(checkPeriod);
        }
        catch (InterruptedException e)
        {
          System.out.println("Interrupted");
        }
      }
    };
    Thread thread = new Thread(task);
    thread.start();
  }

  private void caching()
  {
    Date currentTime = new Date();
    Iterator<Data<V>> itr = cache.values().iterator();
    boolean flag = true;
    while (itr.hasNext() && flag)
    {
      if (itr.next().getCreateDate().getTime() + lifeTime < currentTime.getTime())
        itr.remove();
      else if (cache.size() > cacheCapacity)
        itr.remove();
      else
        flag = false;
    }
  }
}
