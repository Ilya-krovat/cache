package cache;

import defaultOptions.Options;

public class LRUCache extends AbstractCache
{
  public LRUCache(Options options)
  {
    super(options);
  }

  @Override
  boolean elementsComparison()
  {
    return cache.get(actualKey).getLastUseDate().before(cache.get(mostInappropriateKey).getLastUseDate());
  }
}
