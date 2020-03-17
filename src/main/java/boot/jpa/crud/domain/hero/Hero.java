package boot.jpa.crud.domain.hero;

import boot.jpa.crud.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// @Entity를 사용해 데이터베이스 테이블에 대응하는 객체를 만들어 주는 클래스

// JPA : Java Persistence API

// access = AccessLevel.PROTECTED : 기본 생성자를 protected로 자동으로 만들어줌
// 프로젝트 코드에서 기본 생성자로 생성하는 것은 막으면서 JPA에서 Entity 클래스를 생성하는 것은 허용하기 위해 추가
// 클래스 명이 바뀌어도 자동으로 알아서 만들어줌
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// @Getter : Getter 자동 생성, Getter는 필드의 값을 반환하는 메소드
// 여기서 Setter는 절대! 만들면 안됨; setter는 데이터베이스 엑세스 하는 객체를 따로 만들어 줘야함
@Getter
// @Entity : database테이블(직접연결되는 객체)로 만들어 주는 것; DB테이블과 매칭될 클래스임을 알려줌
@Entity
public class Hero extends BaseTimeEntity {

    // @Id : 해당 테이블의 기본키(PK)필드를 직접 할당해주기 위해서 사용
    @Id
    // @GeneratedValue : 기본키에 원하는 생성 규칙을 지정해준다
    // Oracle 은 GenerationType.SEQUENCE; 나머지는 GenerationType.IDENTITY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @Column : 기본적으로 해당 클래스의 필드는 모두 컬럼으로 생성되지만, 컬럼에 추가 변경 사항이 있을 경우 사용
    // nullable = falsen : ull 금지
    @Column(nullable = false)
    private String name;
    private int age;
    // columnDefinition = "TEXT" : 디비에 TEXT로 넣겠다는 말
    @Column(columnDefinition = "TEXT")
    private String note;

    // @Builder : 해당 클래스의 빌더패턴 클래스를 생성해주고, 생성자 상단에 선언하면 생성자에 포함된 필드만 빌더에 포함한다.
    @Builder
    public Hero(String name, int age, String note) {
        this.name = name;
        this.age = age;
        this.note = note;
    }
}
