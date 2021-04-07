package io.github.cgew85.nurl.services;

import io.github.cgew85.nurl.model.entities.UrlData;
import io.github.cgew85.nurl.repositories.UrlDataRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UrlDataService {

    @Value("${key.length}")
    private Integer keyLength;

    private final UrlDataRepository urlDataRepository;

    @Autowired
    public UrlDataService(UrlDataRepository urlDataRepository) {
        this.urlDataRepository = urlDataRepository;
    }

    @Transactional
    public String saveUrl(String url) {
        var hashedUrl = DigestUtils.sha256Hex(url);

        UrlData urlData;
        if(urlDataRepository.existsByEmail(hashedUrl)) {
            urlData = urlDataRepository.findByHashedUrl(hashedUrl);
            urlData.setVisits(urlData.getVisits() + 1);
        } else {
            urlData = new UrlData();
            urlData.setKey(generateRandomString());
            urlData.setHashedUrl(hashedUrl);
            urlData.setUrl(url);
            urlData.setVisits(0L);
            urlData.setCreatedDate(LocalDateTime.now());
        }
        urlData.setLastVisited(LocalDateTime.now());
        urlData = urlDataRepository.save(urlData);

        return urlData.getKey();
    }

    public String getUrlByKey(String key) {
        return urlDataRepository.findByKey(key).getUrl();
    }

    private String generateRandomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = keyLength;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString().toUpperCase();
    }
}
