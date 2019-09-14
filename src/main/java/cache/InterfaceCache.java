package cache;


public interface InterfaceCache<K,V>
{
  void put (K key,V value);

  V get (K key);
}
