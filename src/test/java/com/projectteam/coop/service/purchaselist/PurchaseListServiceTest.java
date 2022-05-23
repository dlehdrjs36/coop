package com.projectteam.coop.service.purchaselist;

import com.projectteam.coop.domain.*;
import com.projectteam.coop.domain.log.LoginLog;
import com.projectteam.coop.exception.DuplicatePurchaseProductException;
import com.projectteam.coop.exception.NoPointException;
import com.projectteam.coop.repository.purchaselist.PurchaseListRepository;
import com.projectteam.coop.service.login.LoginService;
import com.projectteam.coop.service.member.MemberService;
import com.projectteam.coop.util.SecurityUtil;
import com.projectteam.coop.web.session.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
@Transactional(transactionManager = "h2TxManager")
class PurchaseListServiceTest {

    @PersistenceContext(unitName = "h2Jpa")
    private EntityManager em;

    @Autowired
    private PurchaseListService purchaseListService;
    @Autowired
    private PurchaseListRepository purchaseListRepository;
    @Autowired
    private LoginService loginService;
    @Autowired
    private MemberService memberService;

    @BeforeEach
    public void before() {

    }

    @Test
    @DisplayName("회원 상품 구매 시 보유 포인트가 없으면 구매 실패")
    void buyProduct_NO() {
        Product product = Product.createProduct("test1", "testItem.jpg", ProductType.BACKGROUND, 7);
        Product product2 = Product.createProduct("test2", "testItem2.jpg", ProductType.BACKGROUND, 11);
        em.persist(product);
        em.persist(product2);

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("test@gmail.com", "test", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        em.persist(member);

        NoPointException noPointException = assertThrows(NoPointException.class, () -> purchaseListService.addPurchaseList(product, member));
        assertEquals("상품 구매에 필요한 포인트가 부족합니다.", noPointException.getMessage());

    }

    @Test
    @DisplayName("회원 상품 구매 시 보유 포인트가 있지만 부족하면 구매 실패")
    void buyProduct_NO2() {
        Product product = Product.createProduct("test1", "testItem.jpg", ProductType.BACKGROUND, 7);
        Product product2 = Product.createProduct("test2", "testItem2.jpg", ProductType.BACKGROUND, 4);
        Product product3 = Product.createProduct("test3", "testItem3.jpg", ProductType.BACKGROUND, 2);
        em.persist(product);
        em.persist(product2);
        em.persist(product3);

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("test@gmail.com", "test", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        em.persist(member);

        loginService.addLoginLog(LoginLog.createLoginLog(member.getEmail()));
        loginService.addPoint(member);
        assertEquals(10, member.getPoint(), "일별 1회 로그인 시 포인트 10이 지급되어야 한다.");

        Long purchaseListId = purchaseListService.addPurchaseList(product, member);
        PurchaseList purchaseList = purchaseListRepository.findPurchaseList(purchaseListId);

        assertEquals(PurchaseListStatus.ORDER, purchaseList.getStatus(), "상품 구매시 초기 상태는 ORDER 이다.");
        assertEquals(3, member.getPoint(), "상품 구매시 회원의 포인트는 상품 가격만큼 포인트가 줄어들어야 한다.");

        purchaseListService.addPurchaseList(product3, member);
        assertEquals(1, member.getPoint(), "상품 구매시 회원의 포인트는 상품 가격만큼 포인트가 줄어들어야 한다.");

        NoPointException noPointException = assertThrows(NoPointException.class, () -> purchaseListService.addPurchaseList(product2, member));
        assertEquals("상품 구매에 필요한 포인트가 부족합니다.", noPointException.getMessage());
    }

    @Test
    @DisplayName("회원 상품 구매 시 이미 구입한 상품이면 구매 실패")
    void buyProduct_NO3() {
        Product product = Product.createProduct("test1", "testItem.jpg", ProductType.BACKGROUND, 4);
        em.persist(product);

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("test@gmail.com", "test", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        em.persist(member);

        loginService.addLoginLog(LoginLog.createLoginLog(member.getEmail()));
        loginService.addPoint(member);
        assertEquals(10, member.getPoint(), "일별 1회 로그인 시 포인트 10이 지급되어야 한다.");

        Long purchaseListId = purchaseListService.addPurchaseList(product, member);
        PurchaseList purchaseList = purchaseListRepository.findPurchaseList(purchaseListId);

        assertEquals(PurchaseListStatus.ORDER, purchaseList.getStatus(), "상품 구매시 초기 상태는 ORDER 이다.");
        assertEquals(6, member.getPoint(), "상품 구매시 회원의 포인트는 상품 가격만큼 포인트가 줄어들어야 한다.");

        DuplicatePurchaseProductException exception = assertThrows(DuplicatePurchaseProductException.class, () -> purchaseListService.addPurchaseList(product, member));
        assertEquals("이미 구매한 상품입니다.", exception.getMessage());
        assertEquals(6, member.getPoint(), "상품 구매시 회원의 포인트는 상품 가격만큼 포인트가 줄어들어야 한다.");
    }

    @Test
    @DisplayName("회원 상품 구매 성공")
    void buyProduct_OK() {
        Product product = Product.createProduct("test1", "testItem.jpg", ProductType.BACKGROUND, 7);
        em.persist(product);

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("test@gmail.com", "test", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        em.persist(member);

        //포인트 추가. 일별 로그인 1회만 추가
        loginService.addLoginLog(LoginLog.createLoginLog(member.getEmail()));
        loginService.addPoint(member);
        assertEquals(10, member.getPoint(), "일별 1회 로그인 시 포인트 10이 지급되어야 한다.");

        Long purchaseListId = purchaseListService.addPurchaseList(product, member);
        PurchaseList purchaseList = purchaseListRepository.findPurchaseList(purchaseListId);

        assertEquals(PurchaseListStatus.ORDER, purchaseList.getStatus(), "상품 구매시 초기 상태는 ORDER 이다.");
        assertEquals(3, member.getPoint(), "상품 구매시 회원의 포인트는 상품 가격만큼 포인트가 줄어들어야 한다.");
    }

    @Test
    @DisplayName("회원이 구매한 상품(배경) 상태 변경")
    void changeBackgroundStatus_OK() {
        Product product = Product.createProduct("test1", "testItem.jpg", ProductType.BACKGROUND, 7);
        Product product2 = Product.createProduct("test2", "testItem2.jpg", ProductType.BACKGROUND, 1);
        Product product3 = Product.createProduct("test3", "testItem3.jpg", ProductType.BACKGROUND, 1);
        em.persist(product);
        em.persist(product2);
        em.persist(product3);

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("test@gmail.com", "test", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        Long memberId = memberService.addMember(member);
        em.flush();
        Member findMember = memberService.findMember(memberId);
        MemberSessionDto loginMember = MemberSessionDto.createSession(findMember);

        loginService.addLoginLog(LoginLog.createLoginLog(member.getEmail()));
        loginService.addPoint(member);

        Long purchaseListId = purchaseListService.addPurchaseList(product, member);
        PurchaseList purchaseList = purchaseListRepository.findPurchaseList(purchaseListId);
        assertEquals(PurchaseListStatus.ORDER, purchaseList.getStatus(), "상품 구매시 초기 상태는 ORDER 이다.");
        assertEquals(3, member.getPoint(), "상품 구매시 회원의 포인트는 상품 가격만큼 포인트가 줄어들어야 한다.");

        Long purchaseListId2 = purchaseListService.addPurchaseList(product2, member);
        PurchaseList purchaseList2 = purchaseListRepository.findPurchaseList(purchaseListId2);
        assertEquals(PurchaseListStatus.ORDER, purchaseList2.getStatus(), "상품 구매시 초기 상태는 ORDER 이다.");

        Long purchaseListId3 = purchaseListService.addPurchaseList(product3, member);
        PurchaseList purchaseList3 = purchaseListRepository.findPurchaseList(purchaseListId3);
        assertEquals(PurchaseListStatus.ORDER, purchaseList3.getStatus(), "상품 구매시 초기 상태는 ORDER 이다.");
        assertEquals(1, member.getPoint(), "상품 구매시 회원의 포인트는 상품 가격만큼 포인트가 줄어들어야 한다.");

        purchaseListService.orderBackgroundApply(loginMember, purchaseListId);
        em.flush();
        assertEquals(PurchaseListStatus.APPLY, purchaseList.getStatus(), "상품 적용 시 상태는 APPLY 이다.");
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList2.getStatus(), "같은 타입의 상품이 적용되어 있으면 같은 타입의 다른 상품은 미적용 상태로 변경된다.");
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList3.getStatus(), "같은 타입의 상품이 적용되어 있으면 같은 타입의 다른 상품은 미적용 상태로 변경된다.");

        purchaseListService.orderBackgroundApply(loginMember, purchaseListId2);
        em.flush();
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList.getStatus(), "같은 타입의 상품이 적용되어 있으면 같은 타입의 다른 상품은 미적용 상태로 변경된다.");
        assertEquals(PurchaseListStatus.APPLY, purchaseList2.getStatus(), "상품 적용 시 상태는 APPLY 이다.");
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList3.getStatus(), "같은 타입의 상품이 적용되어 있으면 같은 타입의 다른 상품은 미적용 상태로 변경된다.");

        purchaseListService.orderUnapply(loginMember, purchaseListId2);
        em.flush();
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList.getStatus(), "같은 타입의 상품이 적용되어 있으면 같은 타입의 다른 상품은 미적용 상태로 변경된다.");
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList2.getStatus(), "상품 미적용 시 상태는 UNAPPLY 이다.");
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList3.getStatus(), "같은 타입의 상품이 적용되어 있으면 같은 타입의 다른 상품은 미적용 상태로 변경된다.");

    }

    @Test
    @DisplayName("회원이 구매한 상품(아이콘) 상태 변경")
    void changeIconStatus_OK() {
        Product product = Product.createProduct("test1", "testItem.jpg", ProductType.ICON, 7);
        Product product2 = Product.createProduct("test2", "testItem2.jpg", ProductType.ICON, 1);
        Product product3 = Product.createProduct("test3", "testItem3.jpg", ProductType.ICON, 1);
        em.persist(product);
        em.persist(product2);
        em.persist(product3);

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("test@gmail.com", "test", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        Long memberId = memberService.addMember(member);
        em.flush();
        Member findMember = memberService.findMember(memberId);
        MemberSessionDto loginMember = MemberSessionDto.createSession(findMember);

        loginService.addLoginLog(LoginLog.createLoginLog(member.getEmail()));
        loginService.addPoint(member);

        Long purchaseListId = purchaseListService.addPurchaseList(product, member);
        PurchaseList purchaseList = purchaseListRepository.findPurchaseList(purchaseListId);
        assertEquals(PurchaseListStatus.ORDER, purchaseList.getStatus(), "상품 구매시 초기 상태는 ORDER 이다.");
        assertEquals(3, member.getPoint(), "상품 구매시 회원의 포인트는 상품 가격만큼 포인트가 줄어들어야 한다.");

        Long purchaseListId2 = purchaseListService.addPurchaseList(product2, member);
        PurchaseList purchaseList2 = purchaseListRepository.findPurchaseList(purchaseListId2);
        assertEquals(PurchaseListStatus.ORDER, purchaseList2.getStatus(), "상품 구매시 초기 상태는 ORDER 이다.");

        Long purchaseListId3 = purchaseListService.addPurchaseList(product3, member);
        PurchaseList purchaseList3 = purchaseListRepository.findPurchaseList(purchaseListId3);
        assertEquals(PurchaseListStatus.ORDER, purchaseList3.getStatus(), "상품 구매시 초기 상태는 ORDER 이다.");
        assertEquals(1, member.getPoint(), "상품 구매시 회원의 포인트는 상품 가격만큼 포인트가 줄어들어야 한다.");

        purchaseListService.orderIconApply(loginMember, purchaseListId);
        em.flush();
        assertEquals(PurchaseListStatus.APPLY, purchaseList.getStatus(), "상품 적용 시 상태는 APPLY 이다.");
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList2.getStatus(), "같은 타입의 상품이 적용되어 있으면 같은 타입의 다른 상품은 미적용 상태로 변경된다.");
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList3.getStatus(), "같은 타입의 상품이 적용되어 있으면 같은 타입의 다른 상품은 미적용 상태로 변경된다.");

        purchaseListService.orderIconApply(loginMember, purchaseListId2);
        em.flush();
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList.getStatus(), "같은 타입의 상품이 적용되어 있으면 같은 타입의 다른 상품은 미적용 상태로 변경된다.");
        assertEquals(PurchaseListStatus.APPLY, purchaseList2.getStatus(), "상품 적용 시 상태는 APPLY 이다.");
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList3.getStatus(), "같은 타입의 상품이 적용되어 있으면 같은 타입의 다른 상품은 미적용 상태로 변경된다.");

        purchaseListService.orderUnapply(loginMember, purchaseListId2);
        em.flush();
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList.getStatus(), "같은 타입의 상품이 적용되어 있으면 같은 타입의 다른 상품은 미적용 상태로 변경된다.");
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList2.getStatus(), "상품 미적용 시 상태는 UNAPPLY 이다.");
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList3.getStatus(), "같은 타입의 상품이 적용되어 있으면 같은 타입의 다른 상품은 미적용 상태로 변경된다.");

    }

    @Test
    @DisplayName("회원이 구매한 상품 다른타입의 경우 상태 미변경")
    void changeStatus_OK() {
        Product product = Product.createProduct("test1", "testItem.jpg", ProductType.BACKGROUND, 7);
        Product product2 = Product.createProduct("test2", "testItem2.jpg", ProductType.BACKGROUND, 1);
        Product product3 = Product.createProduct("test3", "testItem3.jpg", ProductType.ICON, 1);
        em.persist(product);
        em.persist(product2);
        em.persist(product3);

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("test@gmail.com", "test", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        Long memberId = memberService.addMember(member);
        em.flush();
        Member findMember = memberService.findMember(memberId);
        MemberSessionDto loginMember = MemberSessionDto.createSession(findMember);

        loginService.addLoginLog(LoginLog.createLoginLog(member.getEmail()));
        loginService.addPoint(member);

        Long purchaseListId = purchaseListService.addPurchaseList(product, member);
        PurchaseList purchaseList = purchaseListRepository.findPurchaseList(purchaseListId);
        assertEquals(PurchaseListStatus.ORDER, purchaseList.getStatus(), "상품 구매시 초기 상태는 ORDER 이다.");
        assertEquals(3, member.getPoint(), "상품 구매시 회원의 포인트는 상품 가격만큼 포인트가 줄어들어야 한다.");

        Long purchaseListId2 = purchaseListService.addPurchaseList(product2, member);
        PurchaseList purchaseList2 = purchaseListRepository.findPurchaseList(purchaseListId2);
        assertEquals(PurchaseListStatus.ORDER, purchaseList2.getStatus(), "상품 구매시 초기 상태는 ORDER 이다.");

        Long purchaseListId3 = purchaseListService.addPurchaseList(product3, member);
        PurchaseList purchaseList3 = purchaseListRepository.findPurchaseList(purchaseListId3);
        assertEquals(PurchaseListStatus.ORDER, purchaseList3.getStatus(), "상품 구매시 초기 상태는 ORDER 이다.");
        assertEquals(1, member.getPoint(), "상품 구매시 회원의 포인트는 상품 가격만큼 포인트가 줄어들어야 한다.");

        purchaseListService.orderBackgroundApply(loginMember, purchaseListId);
        em.flush();
        assertEquals(PurchaseListStatus.APPLY, purchaseList.getStatus(), "상품 적용 시 상태는 APPLY 이다.");
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList2.getStatus(), "같은 타입의 상품이 적용되어 있으면 같은 타입의 다른 상품은 미적용 상태로 변경된다.");
        assertEquals(PurchaseListStatus.ORDER, purchaseList3.getStatus(), "다른 타입의 상품은 상태가 변경되지 않는다.");

        purchaseListService.orderIconApply(loginMember, purchaseListId3);
        em.flush();
        assertEquals(PurchaseListStatus.APPLY, purchaseList.getStatus(), "다른 타입의 상품은 상태가 변경되지 않는다.");
        assertEquals(PurchaseListStatus.UNAPPLY, purchaseList2.getStatus(), "다른 타입의 상품은 상태가 변경되지 않는다.");
        assertEquals(PurchaseListStatus.APPLY, purchaseList3.getStatus(), "같은 타입의 상품이 적용되어 있으면 같은 타입의 다른 상품은 미적용 상태로 변경된다.");

    }

}