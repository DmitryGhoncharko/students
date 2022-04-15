package by.webproj.carshowroom.mailsender.secretkeycache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedArrayListBasedSecretKeyCache implements SecretKeyCache{
    private static final Logger LOG = LoggerFactory.getLogger(SynchronizedArrayListBasedSecretKeyCache.class);
    private static final List<String> CACHE = Collections.synchronizedList(new ArrayList<>());
    @Override
    public void addKeyToCache(String key) {
        CACHE.add(key);
    }

    @Override
    public void deleteKeyFromCache(String key) {
        CACHE.remove(key);
    }

    @Override
    public boolean containKeyOnCache(String key) {
        return CACHE.contains(key);
    }
}
