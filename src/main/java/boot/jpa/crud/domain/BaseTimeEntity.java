package boot.jpa.crud.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 생성, 수정 시간을 처리해주는 클래스

@Getter
// 부모 클래스를 상속받는 자식 클래스에게 매핑 정보만 제공하고 싶을 때 사용
// 엔티티는 엔티티만 상속받을 수 있기 때문에 클래스를 상속받기 위해 사용
// 엔티티 종류에 상관없이 공통으로 가지고 있어야 하는 정보가 있을때 많이 사용
@MappedSuperclass
// 이 클래스에 Auditing 기능을 포함 시킴
// Audit는 [감시하다, 감사하다]라는 뜻으로 JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능
@EntityListeners(AuditingEntityListener.class)
// abstract : 추상화
public abstract class BaseTimeEntity {

    // @CreatedDate : 처음 엔티티가 저장될때 날짜를 주입
    @CreatedDate
    // updatable = false : 처음 생성되면 update 불가능
    @Column(updatable = false)
    private LocalDateTime createdDate;
    // 엔티티가 수정될때 수정날짜 주입
    @LastModifiedDate
    private LocalDateTime modifiedDate;


}
