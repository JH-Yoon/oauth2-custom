package com.example.oauth2.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
    /*
    * [@MappedSuperclass]
    * : JPA Entity 클래스들이 BaseTimeEntity 를 상속할 경우 필드들(createDate, modifiedDate)도 컬럼으로 인식하도록 합니다.
    * [@EntityListeners]
    * : BaseTimeEntity 클래스에 Auditing 기능을 포함시킵니다.
    * [@CreatedData]
    * : Entity 가 생성되어 저장될 때 시간
    * [@LastModifiedDate]
    * : 조회한 Entity의 값이 변경할 때 시간
    * */

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
