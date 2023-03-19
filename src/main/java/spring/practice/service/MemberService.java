package spring.practice.service;

import spring.practice.domain.Member;

public interface MemberService {

    /**
     * 회원 가입
     * @param member 가입 신청한 고객 정보 객체
     */
    void join(Member member);

    /**
     * 개명
     * @param name 개명할 이름
     */
    void update(Long id, String name);

    /**
     * 회원 정보 조회
     * @param id 조회할 회원 번호
     * @return 조회한 회원 정보 객체
     */
    Member find(Long id);

}
