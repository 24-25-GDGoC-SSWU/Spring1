package hello.hello.domain;

public class Member {
    private Long id; //임의의 값 -> 고객이 정하는 아이디가 아니라 시스템이 정하는 아이디임
                     // -> 즉 데이터를 구분하기 위해서 시스템이 저장하는 아이디(db로 치면 대체키?기본키?)
    private String name;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
