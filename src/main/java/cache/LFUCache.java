package cache;

import defaultOptions.Options;

public class LFUCache<V> extends AbstractCache<V>
{
  public LFUCache(Options options)
  {
    super(options);
  }

  @Override
  boolean elementsComparison()
  {
    return cache.get(actualKey).getNumberOfUses() < cache.get(mostInappropriateKey).getNumberOfUses();
  }
}
