package spring.mvc.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private static Map<Long, Member> database = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    // 생성자 막아버리기
    private MemberRepository() {
    }

    // instance 꺼내는 방법 열어두기
    public static MemberRepository getInstance() {
        return instance;
    }

    // 데이터 저장하기
    public Member save(Member member) {
        member.setId(++sequence);
        database.put(member.getId(), member);
        return member;
    }

    // 데이터 꺼내기
    public Member findOne(Long memberId) {
        Member member = database.get(memberId);
        return member;
    }

    // 데이터 전체 꺼내기
    public List<Member> findAll() {
        return new ArrayList<>(database.values());
    }
}
