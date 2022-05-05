package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass //맵핑정보만 받는 슈퍼클래스
//즉, 공통적으로 사용할 정보는 이렇게 슈퍼클래스를 사용하자 (공통의 매핑정보 필요시)
//상속관계 매핑X, 자식 클래스에 매핑 정보만 제공(부모타입으로 조회나 검색 안됨)
//**직접 생성해서 사용할 일이 없으니** 추상클래스로 권장
public abstract class BaseEntity {

//    @Column(name = "INSERT_") //공통적으로 이 클래스를 상속받은 자식클래스 모두 적용됨.
    private String createBy;
    private LocalDateTime createDate;
    private String modifiedBy;
    private LocalDateTime lastModifiedDate;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
