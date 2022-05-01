package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
//        jpaTest1(); //기본 CRUD와 기본키 수업

    }



    private static void jpaTest1() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /**
             * 저장, 수정, 삭제
             */
            //저장
//            Member1 member = new Member1();
//            member.setId(2L);
//            member.setName("helloB");
//            em.persist(member); //저장

            //수정
//            Member findMember = em.find(Member.class, 2L); //수정할 데이터 조회
//            findMember.setName("HelloJPA");
            //수정시에는 따로 저장 안해도 됨

//            em.remove(findMember);삭제

/*            List<Member> result = em.createQuery("select m from Member as m", Member.class) //Member객체를 개상으로 쿼리를 짠다.
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getName());
            }*/

            /**
             * 영속상태와 비영속 상태
             */
/*            //비영속 상태
            Member member = new Member();
            member.setId(20L);
            member.setName("HelloJAP!");

            //영속상태(DB에 저장되지는 않다.)
            System.out.println("===BEFORE===");
            em.persist(member);
//            em.detach(member); //준영속 상태로
            System.out.println("===AFTER===");*/

            /**
             * findMember == findMember2 (true)
             * 즉 영속 엔티티의 동일성이 보장된다.
             */
/*            Member findMember = em.find(Member.class, 20L);//JPA는 조회한 엔티티를 무조건 영속성으로 올림
            Member findMember2 = em.find(Member.class, 20L);*/

            //영속
/*
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);

*/

//            Member member = em.find(Member.class, 150L);
            /**
             * 값 변경시 작동방식
             * transaction.commit()시
             * 1.flush(): (플러시 발생)
             * 2.변경 감지 (Dirty Checking)
             * 3.엔티티와 스냅샷 비교
             * 스냅샷: 값을 읽어온 최초상태(처음 영속상태로 들어온 1차 상태)
             * 4.값이 변경된 경우 UPDATE SQL을 쓰기 지연 SQL 저장소에 저장함
             * em.remove(); 삭제 역시 같은 메커니즘
             *
             */
            //영속 엔티티 데이터 수정
//            member.setName("ZZZZZ");


            /**
             * flush() 강제 호출하기
             * 중간 JPQL이 실행되는 경우엔 자동으로 flush() 호출됨
             */
/*            Member member = new Member(150L, "member150");
            em.persist(member);

            em.flush();*/

/*            //영속 상태
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA"); // DIRTY CHECKING
            //준영속 상태로 만들기(즉 JPA에서 관리를 안함):
//            em.detach(member);//특정 상태만 준영속으로 만들기
//            em.clear(); //통으로 준영속 만들기
            em.close(); //영속성 컨텍스트를 종료*/


            Member2 member2 = new Member2();
//            member2.setId("ID_A");
            member2.setUsername("C");

            System.out.println("==================================");
            em.persist(member2);
            System.out.println("member2.getId() = " + member2.getId());
            System.out.println("==================================");
            /**
             * transaction.commit() 순간 쓰기 지연 SQL 저장소에 있던 SQL을 DB에 보낸다.
             */
            tx.commit();//DB 저장되는 시점 (커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
        } catch (Exception e) {
            tx.rollback();
        } finally {
            //code
            em.close();
        }

        emf.close();
    }
}
