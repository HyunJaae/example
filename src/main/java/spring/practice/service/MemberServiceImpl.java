package spring.practice.service;

import org.springframework.stereotype.Service;
import spring.practice.domain.Member;
import spring.practice.repository.MemberRepository;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void update(Long id, String name) {
        memberRepository.update(id, name);
    }

    @Override
    public Member find(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) throw new NullPointerException();
        return member.get();
    }
}
