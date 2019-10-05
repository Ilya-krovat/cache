package cache;

import data.Data;
import defaultOptions.Options;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractCache implements Cache
{
  Map<String, Data> cache = new LinkedHashMap<>();
  private Integer cacheCapacity;

  String mostInappropriateKey;
  String actualKey;

  AbstractCache(Options options)
  {
    this.cacheCapacity = options.getCacheCapacity();
  }

  @Override
  public Integer size()
  {
    try
    {
      return cache.size();
    }
    catch (NullPointerException e)
    {
      return 0;
    }
  }

  @Override
  public Object get(String key)
  {
    try
    {
      cache.get(key).updateData();
      return cache.get(key).getData();
    }
    catch (NullPointerException e)
    {
      return null;
    }
  }

  @Override
  public void put(String key, Object value)
  {
    cacheCapacity--;
    caching();
    cacheCapacity++;
    cache.put(key, new Data<>(value));
  }

  private void caching()
  {
    while (cache.size() > cacheCapacity)
    {
      Iterator<String> itr = cache.keySet().iterator();
      mostInappropriateKey = cache.keySet().iterator().next();
      while (itr.hasNext())
      {
        actualKey = itr.next();
        if (elementsComparison())
          mostInappropriateKey = actualKey;
      }
      cache.remove(mostInappropriateKey);
    }
  }

  abstract boolean elementsComparison();
}