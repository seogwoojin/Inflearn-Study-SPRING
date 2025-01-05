package hello.jdbc.domain;

import lombok.Data;

@Data
public class Member {

    private String memberId;

    public Member(String memberId, int money) {
        this.memberId = memberId;
        this.money = money;
    }

    private int money;

    public Member(){

    }


}
