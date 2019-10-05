package cache;

import defaultOptions.Options;

public class LFUCache extends AbstractCache
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
