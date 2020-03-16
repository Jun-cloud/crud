package boot.jpa.crud.domain.hero;

import boot.jpa.crud.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 기본 생성자 protected로 자동으로 만들어줌
// 클래스 명이 바뀌어도 자동으로 알아서 만들어줌
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// Getter 자동 생성; 객체에서는 Setter는 절대! 만들면 안됨; 데이터베이스 엑세스 하는 객체를 따로 만들어 줘야함
@Getter
// database테이블(직접연결되는 객체)로 만들어 주는 것
@Entity
public class Hero extends BaseTimeEntity {

    // jpa : javax.persistence.Id
    @Id
    // Oracle 은 GenerationType.SEQUENCE; 나머지는 GenerationType.IDENTITY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // null 금지
    @Column(nullable = false)
    private String name;
    private int age;
    // 디비에 TEXT로 넣겠다는 말
    @Column(columnDefinition = "TEXT")
    private String note;

    @Builder
    public Hero(String name, int age, String note) {
        this.name = name;
        this.age = age;
        this.note = note;
    }
}
