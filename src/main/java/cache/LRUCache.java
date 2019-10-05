package cache;

import defaultOptions.Options;

public class LRUCache<V> extends AbstractCache<V>
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
