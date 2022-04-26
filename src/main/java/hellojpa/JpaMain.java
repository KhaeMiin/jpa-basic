package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //저장
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("helloB");
//            em.persist(member); //저장

            //수정
//            Member findMember = em.find(Member.class, 2L); //수정할 데이터 조회
//            findMember.setName("HelloJPA");
            //수정시에는 따로 저장 안해도 됨

//            em.remove(findMember);삭제

            List<Member> result = em.createQuery("select m from Member as m", Member.class) //Member객체를 개상으로 쿼리를 짠다.
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            //code
            em.close();
        }

        emf.close();
    }
}
