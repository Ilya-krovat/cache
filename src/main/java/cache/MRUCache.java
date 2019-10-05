package cache;

import defaultOptions.Options;

public class MRUCache<V> extends AbstractCache<V>
{
  public MRUCache(Options options)
  {
    super(options);
  }

  @Override
  boolean elementsComparison()
  {
    return cache.get(actualKey).getLastUseDate().after(cache.get(mostInappropriateKey).getLastUseDate());
  }
}
