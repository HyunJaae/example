package spring.practice.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spring.practice.domain.Member;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {


    @Autowired
    private MemberRepository repository;

    /**
     * JPA 의 nativeQuery 사용 시 발생한 이슈
     * <pre>
     *     이슈 상황
     *     1. Spring Data JPA 의 save() 메서드를 통해 데이터 저장
     *     2. nativeQuery 를 이용한 update
     *     3. update 한 정보로 find() 메서드 실행
     *
     *     => 결과 : find() 로 조회한 객체의 정보가 업데이트한 정보가 아니다.
     * </pre>
     */
    @Test
    @DisplayName("이슈 상황 테스트")
    void issue() {
        // given
        Member member = new Member("현재");
        repository.save(member);
        // when
        repository.update(member.getId(), "인혁");
        Optional<Member> findMember = repository.findById(member.getId());
        // then
        assertThat(findMember.get().getName()).isEqualTo("현재");
    }

    /**
     * 영속성 컨텍스트 초기화 옵션 추가
     * <pre>
     *     Modifying 어노테이션 옵션 중 clearAutomatically 속성을 true 로 변경한다.
     *     쿼리 실행 이후 1차 캐시 초기화 유무 옵션으로 default 는 false 이다.
     * </pre>
     */
    @Test
    @DisplayName("해결 방법")
    void solve() {
        // given
        Member member = new Member("현재");
        repository.save(member);
        // when
        repository.updateName(member.getId(), "인혁");
        Optional<Member> findMember = repository.findById(member.getId());
        // then
        assertThat(findMember.get().getName()).isEqualTo("인혁");
    }

}
