package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {


    //MemoryMemberRepository 의 static이기 때문에 이는 객체가 아닌 클래스에 붙는 것이라  다른 객체로 선언해도 큰 문제가 없긴 한데 굳이 ...다르게
    // 해야하는가 ? -> 해결을 위해 생성자 (service 와 test비교하 며 복습하기)
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    public void beforeEach(){
        //service 에서 instance 생성하지 않고 외부에서 해주는 것을
        //defendency injection 이라고 함 DI
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        Member findMember = memberService.findOne(saveId).get();
        //then
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");





        //when
        memberService.join(member1);
        //try catch 대신 사용
        //단축키 ctrl + alt = V
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try{
//            memberService.join(member2);
//            fail();
//        }
//        catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다1234234!!3");
//
//        }
        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}