package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


// 컨트롤러로 외부 요청을 받고 서비스에서 비지니스 로직을 만들고,리포지토리에서 데이터를 저장하는 것 -> 정형화된 패턴
//저장소
// interface는 좀 더 구현을 위한 저장 수단
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //null 일 가능성이 있으면 Optional
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public  void clearStore(){
        store.clear();
    }
}
