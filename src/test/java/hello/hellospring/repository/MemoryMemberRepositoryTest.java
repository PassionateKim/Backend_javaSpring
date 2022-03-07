package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public  void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save123(){
        Member member = new Member();
        member.setName(("spring"));

       repository.save(member);

       Member result = repository.findById(member.getId()).get();
       Assertions.assertEquals(member,result);
    }

    @Test
    public void findByName123(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //shift + f6  이름 바꾸기기
       Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertEquals(member1,result);

    }

    @Test
    public void findAll123(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertEquals(2,result.size());
    }
}
