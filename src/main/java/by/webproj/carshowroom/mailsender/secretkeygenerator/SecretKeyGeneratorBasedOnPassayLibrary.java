package by.webproj.carshowroom.mailsender.secretkeygenerator;

import by.webproj.carshowroom.mailsender.secretkeycache.SecretKeyCache;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.passay.DigestDictionaryRule.ERROR_CODE;


public class SecretKeyGeneratorBasedOnPassayLibrary implements SecretKeyGenerator{
    private static final Logger LOG = LoggerFactory.getLogger(SecretKeyGeneratorBasedOnPassayLibrary.class);
    private final SecretKeyCache secretKeyCache;
    public SecretKeyGeneratorBasedOnPassayLibrary(SecretKeyCache secretKeyCache) {
        this.secretKeyCache = secretKeyCache;
    }

    @Override
    public String generateSecretKey() {
       final PasswordGenerator gen = new PasswordGenerator();
       final CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
       final CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

       final CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
       final CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

       final CharacterData digitChars = EnglishCharacterData.Digit;
       final CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

      final   CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "[email protected]#$%^&*()_+";
            }
        };
        final CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        final String secretKey = gen.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
        secretKeyCache.addKeyToCache(secretKey);
        return secretKey;
    }
}
