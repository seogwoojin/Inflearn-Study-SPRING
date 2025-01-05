package jpabook.jpashop;


public interface OAuth2Userinfo {

    String getProviderId(); // google,naver, facebook
    String getGetProvider();// google, naver, facebook
    String getEmail();
    String getName();

}