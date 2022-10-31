package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.Role;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String email;
    private String name;
    private String number;
    private String oauth;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String email, String name, String number, String oauth) {

        this.attributes = attributes;
        this.email = email;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.number = number;
        this.oauth = oauth;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        OAuthAttributes oAuthAttributes = OAuthAttributes.builder()
                .email((String) response.get("email"))
                .name((String) response.get("name"))
                .oauth("oauth")
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();

        return oAuthAttributes;
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {

        return OAuthAttributes.builder()
                .email((String)attributes.get("email"))
                .name((String) attributes.get("name"))
                .attributes(attributes)
                .oauth("oauth")
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .oauth(oauth)
                .role(Role.GUEST)
                .build();
    }
}