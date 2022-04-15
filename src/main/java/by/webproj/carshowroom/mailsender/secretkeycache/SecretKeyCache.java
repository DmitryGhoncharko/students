package by.webproj.carshowroom.mailsender.secretkeycache;

import java.util.Optional;

public interface SecretKeyCache {

    void addKeyToCache(String key);

    void deleteKeyFromCache(String key);

    boolean containKeyOnCache(String key);
}
