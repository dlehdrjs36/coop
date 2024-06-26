package com.projectteam.coop.service.post;

import com.projectteam.coop.domain.board.model.entity.Board;
import com.projectteam.coop.domain.board.service.BoardService;
import com.projectteam.coop.domain.member.model.entity.Member;
import com.projectteam.coop.domain.member.service.MemberService;
import com.projectteam.coop.domain.post.model.entity.Post;
import com.projectteam.coop.domain.post.service.PostService;
import com.projectteam.coop.util.SecurityUtil;
import com.projectteam.coop.web.post.PostCreateForm;
import com.projectteam.coop.web.session.MemberSessionDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("local")
@SpringBootTest(
        properties =
                "spring.config.import="
                        + "optional:../properties/local_security/coop_server_local.yml,"
                        + "optional:../properties/coop_server.yml",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional(transactionManager = "mysqlTxManager")
class PostServiceTest {

    @PersistenceContext(unitName = "mysqlJpa")
    private EntityManager em;

    @Autowired
    private PostService postService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("비회원 답글 게시물 등록 OK")
    void nonmemberAddReplyPost() {

        Long boardId = boardService.addBoard(Board.createBoard());

        PostCreateForm parentForm = getPostCreateForm("parentTitle", "password", "parent", "parentNickname", boardId);
        Long parentPostId = postService.addPost(parentForm, null);

        PostCreateForm postForm = getPostCreateForm("childTitle", "password", "child", "childNickname", boardId);
        postForm.setUpperPostId(parentPostId);
        Long childPostId = postService.addReplyPost(postForm, null);
        Post replyPost = em.find(Post.class, childPostId);

        PostCreateForm postForm2 = getPostCreateForm("childTitle2", "password", "child2", "childNickname2", boardId);
        postForm2.setUpperPostId(parentPostId);
        Long childPostId2 = postService.addReplyPost(postForm2, null);

        PostCreateForm postForm3 = getPostCreateForm("childTitle3", "password", "child3", "childNickname3", boardId);
        postForm3.setUpperPostId(parentPostId);
        postService.addReplyPost(postForm3, null);

        PostCreateForm postForm4 = getPostCreateForm("childTitle2_1", "password", "child2_1", "childNickname2_1", boardId);
        postForm4.setUpperPostId(childPostId2);
        postService.addReplyPost(postForm4, null);

        assertNotNull(replyPost.getParent(), () -> "게시글 부모가 존재해야 합니다.");
        assertNull(replyPost.getCreateMember(), () -> "작성자가 존재하면 안됩니다.");
        assertEquals("childTitle", replyPost.getTitle(), () -> "제목이 일치하지 않습니다.");
        assertEquals("password", replyPost.getPassword(), () -> "패스워드가 일치하지 않습니다.");
        assertEquals("child", replyPost.getContent(), () -> "내용이 일치하지 않습니다.");
        assertEquals("childNickname", replyPost.getNickname(), () -> "닉네임이 일치하지 않습니다.");
        assertEquals(0, replyPost.getViewCount(), () -> "조회수가 일치하지 않습니다.");
        assertEquals(0, replyPost.getRecommendCount(), () -> "추천수가 일치하지 않습니다.");
        assertEquals(parentPostId, replyPost.getGroup(), () -> "게시물 그룹이 일치하지 않습니다.");
        assertEquals(1L, replyPost.getDepth(), () -> "게시물 레벨이 일치하지 않습니다.");
        assertEquals(3L, replyPost.getOrder(), () -> "게시물 순서가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("회원 답글 게시물 등록 OK")
    void memberAddReplyPost() {

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("cccc", "test@gmail.com", "테스트", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        Long memberId = memberService.addMember(member);
        em.flush();
        assertEquals(member, memberService.findMember(memberId));

        Member findMember = memberService.findMember(memberId);
        MemberSessionDto loginMember = MemberSessionDto.createSession(findMember);

        Long boardId = boardService.addBoard(Board.createBoard());
        PostCreateForm parentForm = getPostCreateForm("parentTitle", "password", "parent", "parentNickname", boardId);
        Long parentPostId = postService.addPost(parentForm, loginMember);

        PostCreateForm postForm = getPostCreateForm("childTitle", "password", "child", "childNickname", boardId);
        postForm.setUpperPostId(parentPostId);
        Long childPostId = postService.addReplyPost(postForm, loginMember);
        Post replyPost = em.find(Post.class, childPostId);

        PostCreateForm postForm2 = getPostCreateForm("childTitle2", "password", "child2", "childNickname2", boardId);
        postForm2.setUpperPostId(parentPostId);
        Long childPostId2 = postService.addReplyPost(postForm2, loginMember);

        PostCreateForm postForm3 = getPostCreateForm("childTitle3", "password", "child3", "childNickname3", boardId);
        postForm3.setUpperPostId(parentPostId);
        postService.addReplyPost(postForm3, loginMember);

        PostCreateForm postForm4 = getPostCreateForm("childTitle2_1", "password", "child2_1", "childNickname2_1", boardId);
        postForm4.setUpperPostId(childPostId2);
        postService.addReplyPost(postForm4, loginMember);

        assertNotNull(replyPost.getParent(), () -> "게시글 부모가 존재해야 합니다.");
        assertNotNull(replyPost.getCreateMember(), () -> "작성자가 존재해야 합니다.");
        assertEquals("childTitle", replyPost.getTitle(), () -> "제목이 일치하지 않습니다.");
        assertEquals("password", replyPost.getPassword(), () -> "패스워드가 일치하지 않습니다.");
        assertEquals("child", replyPost.getContent(), () -> "내용이 일치하지 않습니다.");
        assertEquals("테스트", replyPost.getNickname(), () -> "닉네임이 일치하지 않습니다.");
        assertEquals(0, replyPost.getViewCount(), () -> "조회수가 일치하지 않습니다.");
        assertEquals(0, replyPost.getRecommendCount(), () -> "추천수가 일치하지 않습니다.");
        assertEquals(parentPostId, replyPost.getGroup(), () -> "게시물 그룹이 일치하지 않습니다.");
        assertEquals(1L, replyPost.getDepth(), () -> "게시물 레벨이 일치하지 않습니다.");
        assertEquals(3L, replyPost.getOrder(), () -> "게시물 순서가 일치하지 않습니다.");

    }

    @Test
    @DisplayName("비회원 게시물 등록 OK")
    void nonmemberAddPost() {

        Long boardId = boardService.addBoard(Board.createBoard());
        PostCreateForm postForm = getPostCreateForm("title", "password", "test", "nickname", boardId);

        Long postId = postService.addPost(postForm, null);
        Post post = em.find(Post.class, postId);

        assertNull(post.getParent(), () -> "게시글 부모가 존재합니다.");
        assertNull(post.getCreateMember(), () -> "작성자가 존재합니다.");
        assertEquals("title", post.getTitle(), () -> "제목이 일치하지 않습니다.");
        assertEquals("password", post.getPassword(), () -> "패스워드가 일치하지 않습니다.");
        assertEquals("test", post.getContent(), () -> "내용이 일치하지 않습니다.");
        assertEquals("nickname", post.getNickname(), () -> "닉네임이 일치하지 않습니다.");
        assertEquals(0, post.getViewCount(), () -> "조회수가 일치하지 않습니다.");
        assertEquals(0, post.getRecommendCount(), () -> "추천수가 일치하지 않습니다.");
        assertEquals(postId, post.getGroup(), () -> "게시물 그룹이 일치하지 않습니다.");
        assertEquals(0L, post.getDepth(), () -> "게시물 레벨이 일치하지 않습니다.");
        assertEquals(0L, post.getOrder(), () -> "게시물 순서가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("회원 게시물 등록 OK")
    void memberAddPost() {

        String salt = SecurityUtil.getSalt();
        Member member = Member.createMember("dddd","test@gmail.com", "테스트", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        Long memberId = memberService.addMember(member);
        em.flush();
        assertEquals(member, memberService.findMember(memberId));

        Member findMember = memberService.findMember(memberId);
        MemberSessionDto loginMember = MemberSessionDto.createSession(findMember);
        Long boardId = boardService.addBoard(Board.createBoard());
        PostCreateForm postForm = getPostCreateForm("title", "password", "test", "nickname", boardId);

        Long postId = postService.addPost(postForm, loginMember);
        Post post = em.find(Post.class, postId);

        assertNull(post.getParent(), () -> "게시글 부모가 존재합니다.");
        assertNotNull(post.getCreateMember(), () -> "작성자가 존재하지 않습니다.");
        assertEquals("test@gmail.com", post.getCreateMember().getEmail(), () -> "이메일이 일치하지 않습니다.");
        assertEquals("title", post.getTitle(), () -> "제목이 일치하지 않습니다.");
        assertEquals("password", post.getPassword(), () -> "패스워드가 일치하지 않습니다.");
        assertEquals("test", post.getContent(), () -> "내용이 일치하지 않습니다.");
        assertEquals("테스트", post.getNickname(), () -> "닉네임이 일치하지 않습니다.");
        assertEquals(0, post.getViewCount(), () -> "조회수가 일치하지 않습니다.");
        assertEquals(0, post.getRecommendCount(), () -> "추천수가 일치하지 않습니다.");
        assertEquals(postId, post.getGroup(), () -> "게시물 그룹이 일치하지 않습니다.");
        assertEquals(0L, post.getDepth(), () -> "게시물 레벨이 일치하지 않습니다.");
        assertEquals(0L, post.getOrder(), () -> "게시물 순서가 일치하지 않습니다.");
    }

    @Test
    @DisplayName("회원이 등록한 게시물 추천 OK")
    void addMemberRecommend() {

        String salt = SecurityUtil.getSalt();
        Member member1 = Member.createMember("eeee","test@gmail.com", "테스트", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        Long memberId1 = memberService.addMember(member1);
        em.flush();
        assertEquals(member1, memberService.findMember(memberId1));

        String salt2 = SecurityUtil.getSalt();
        Member member2 = Member.createMember("ffff","test2@gmail.com", "테스트2", SecurityUtil.encryptSHA256("1234", salt2), salt2, Boolean.TRUE);
        Long memberId2 = memberService.addMember(member2);
        em.flush();
        assertEquals(member2, memberService.findMember(memberId2));

        MemberSessionDto loginMember = MemberSessionDto.createSession(member1);
        Long boardId = boardService.addBoard(Board.createBoard());
        PostCreateForm postForm = getPostCreateForm("title", "password", "test", "nickname", boardId);

        Long postId = postService.addPost(postForm, loginMember);
        Post post = em.find(Post.class, postId);

        postService.recommend(memberId1, postId);
        postService.recommend(memberId1, postId);
        postService.recommend(memberId2, postId);

        assertEquals(2, post.getRecommendCount());

    }

    @Test
    @DisplayName("비회원이 등록한 게시물 추천 OK")
    void addNonmemberRecommend() {

        String salt = SecurityUtil.getSalt();
        Member member1 = Member.createMember("gggg","test@gmail.com", "테스트", SecurityUtil.encryptSHA256("1234", salt), salt, Boolean.TRUE);
        Long memberId1 = memberService.addMember(member1);
        em.flush();
        assertEquals(member1, memberService.findMember(memberId1));

        String salt2 = SecurityUtil.getSalt();
        Member member2 = Member.createMember("hhhh","test2@gmail.com", "테스트2", SecurityUtil.encryptSHA256("1234", salt2), salt2, Boolean.TRUE);
        Long memberId2 = memberService.addMember(member2);
        em.flush();
        assertEquals(member2, memberService.findMember(memberId2));

        Long boardId = boardService.addBoard(Board.createBoard());
        PostCreateForm postForm = getPostCreateForm("title", "password", "test", "nickname", boardId);
        Long postId = postService.addPost(postForm, null);
        Post post = em.find(Post.class, postId);

        postService.recommend(memberId1, postId);
        postService.recommend(memberId1, postId);
        postService.recommend(memberId2, postId);

        assertEquals(2, post.getRecommendCount());

    }

    private PostCreateForm getPostCreateForm(String title, String password, String content, String nickname, Long boardId) {
        PostCreateForm postForm = new PostCreateForm();
        postForm.setTitle(title);
        postForm.setPassword(password);
        postForm.setContent(content);
        postForm.setNickname(nickname);
        postForm.setBoardId(boardId);
        return postForm;
    }
}