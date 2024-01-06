package com.projectteam.coop.web;

import com.projectteam.coop.domain.*;
import com.projectteam.coop.util.SecurityUtil;
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
//        initService.dbInit1();
    }

    @Transactional(transactionManager = "mysqlTxManager")
    @Component
    static class InitService {
        @PersistenceContext(unitName = "mysqlJpa")
        private EntityManager em;

        public void dbInit1() {
            //회원
            String memberSalt1 = SecurityUtil.getSalt();
            String memberSalt2 = SecurityUtil.getSalt();
            Member member1 = Member.createMember("aaaa","coop1@naver.com", "테스트1", SecurityUtil.encryptSHA256("1234", memberSalt1), memberSalt1, Boolean.TRUE);
            Member member2 = Member.createMember("bbbb", "coop2@naver.com", "테스트2", SecurityUtil.encryptSHA256("1234", memberSalt2), memberSalt2, Boolean.TRUE);
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

            //게시판
            Board board = Board.createBoard();
            em.persist(board);

            //게시물
            Post post1 = Post.createPost("테스트1", "1234", "테스트 내용입니다.", "닉네임1", board);
            Post post2 = Post.createPost("테스트2", "1234", "테스트 내용입니다.", "닉네임2", board);
            em.persist(post1);
            em.persist(post2);

            post1.initGroup();
            post2.initGroup();
        }
    }
}
