package com.projectteam.coop.web;

import com.projectteam.coop.domain.Member;
import com.projectteam.coop.domain.Post;
import com.projectteam.coop.domain.Product;
import com.projectteam.coop.domain.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Transactional(transactionManager = "h2TxManager")
    @Component
    static class InitService {
        @PersistenceContext(unitName = "h2Jpa")
        private EntityManager em;

        public void dbInit1() {
            //회원
            Member member1 = Member.createMember("coop1@naver.com", "테스트1", "1234", Boolean.TRUE);
            Member member2 = Member.createMember("coop2@naver.com", "테스트2", "1234", Boolean.TRUE);
            em.persist(member1);
            em.persist(member2);

            //배경 상품
            Product background1 = Product.createProduct("background1", "background01.jpg", ProductType.BACKGROUND, 1);
            Product background2 = Product.createProduct("background2", "background02.jpg", ProductType.BACKGROUND, 2);
            Product background3 = Product.createProduct("background3", "background03.jpg", ProductType.BACKGROUND, 3);
            em.persist(background1);
            em.persist(background2);
            em.persist(background3);
            //아이콘 상품
            Product icon1 = Product.createProduct("icon1", "icon02.png", ProductType.ICON, 1);
            Product icon2 = Product.createProduct("icon2", "icon03.png", ProductType.ICON, 2);
            Product icon3 = Product.createProduct("icon3", "icon04.png", ProductType.ICON, 3);
            em.persist(icon1);
            em.persist(icon2);
            em.persist(icon3);

            //게시물
            Post post1 = Post.createPost("테스트1", "1234", "테스트 내용입니다.", "닉네임1");
            Post post2 = Post.createPost("테스트2", "1234", "테스트 내용입니다.", "닉네임2");
            em.persist(post1);
            em.persist(post2);

            post1.initGroup();
            post2.initGroup();
        }
    }
}
