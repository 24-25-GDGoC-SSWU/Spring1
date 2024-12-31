package hello.core.singleton;
//싱글톤 패턴을 구현하는 방법은 여러가지가 있다. 여기서는 객체를 미리 생성해두는 가장 단순하고 안전한 방법을 선택했다.
//애플리케이션에 영향을 안 주고 싱글톤만 간단하게 보기 위함
public class SingletonService {
    //1. static 영역에 객체를 딱 1개만 생성해둔다.
    // 자기자신을 내부의 private으로 생성(static으로) -> 클래스 레벨에 올라가기 때문에 딱 하나만 존재(static이라고 되어 있으면 그 static 영역에 딱 하나만 만들어져서 올라감)
    private static final SingletonService instance = new SingletonService();
    //ㄴ> SingletonService가 실행될 때 자기 자신을 생성해서 인스턴스에 넣어둠(자기자신 인스턴스 객체를 하나 생성해서 instance에 들어가있는 것)

    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.(조회할 때 사용)
    public static SingletonService getInstance() {
        return instance; //인스턴스의 참조를 꺼낼 수 있는 유일한 방법
    }

    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    // 다른 파일에서 SingletonService를 new해서 생성하려고 하면 오류가 날 것임 (생성할 수 있는 곳 x)
    private SingletonService() {
    }
    // 위 세 개의 메소드 -> 싱글톤 패턴을 구현하기 위해 무조건 넣어야 하는 코드

    //실제로 필요한 코드(사용하고자 하는 코드)
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
