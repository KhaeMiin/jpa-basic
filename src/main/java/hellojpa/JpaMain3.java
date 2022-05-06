package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain3 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member3 member = new Member3();
            member.setUsername("hello");
            
            em.persist(member);
            
            em.flush();
            em.clear();

//            Member3 findMember = em.find(Member3.class, member.getId());
            Member3 findMember = em.getReference(Member3.class, member.getId()); //프록시사용
            System.out.println("findMember = " + findMember.getUsername());
            System.out.println("findMember.getId() = " + findMember.getId());//내부적으로 영속성컨텍스트에 요청(DB조회) 실제 Entity생성 후 프록시의 target 저장

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
