package com.projectteam.coop.repository.post;

import com.projectteam.coop.domain.Post;
import com.projectteam.coop.web.post.PostSearch;
import com.projectteam.coop.web.post.PostSearchType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.projectteam.coop.domain.QPost.post;

@Repository
public class PostRepository {

    @PersistenceContext(unitName = "h2Jpa")
    private EntityManager em;

    //등록
    public Long addPost(Post post) {
        if (post.getParent() == null) {
            em.persist(post);
            post.initGroup();
        }else {
            em.persist(post);
        }
        return post.getPostId();
    }

    //삭제
    public void removePost(Long id) {
        Post post = em.find(Post.class, id);
        em.remove(post);
    }

    //수정
//    public Long updatePost(PostUpdateForm postForm) {
//        Post findPost = em.find(Post.class, postForm.getPostId());
//        findPost.changePost(null, null, postForm.getPassword(), postForm.getTitle(), postForm.getContent(), null, null);
//        return findPost.getPostId();
//    }

    //인기글 목록 조회 10개
    public List<Post> findRecommendPosts() {
        List<Post> findPosts = em.createQuery("select p from Post p left join fetch p.createMember order by p.recommendCount desc", Post.class)
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList();

        return findPosts;
    }

    //목록 조회
    public List<Post> findPosts(PostSearch postSearch, int offset, int size) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        List<Post> findPosts = queryFactory
                .selectFrom(post)
                .where(
                        postSearchCond(postSearch.getSearchType(), postSearch.getSearchValue())
                )
                .orderBy(
                        post.group.desc(),
                        post.order.asc(),
                        post.depth.asc()
                )
                .offset(offset)
                .limit(size)
                .fetch();

        return findPosts;
    }

    private BooleanBuilder contentCt(String contentCond) {
        return contentCond != null ? new BooleanBuilder(post.content.contains(contentCond)) : new BooleanBuilder();
    }

    private BooleanBuilder titleCt(String titleCond) {
        return titleCond != null ? new BooleanBuilder(post.title.contains(titleCond)) : new BooleanBuilder();
    }

    private BooleanBuilder createMemberNameCt(String createMemberNameCond) {
        return createMemberNameCond != null ? new BooleanBuilder(post.createMember.name.contains(createMemberNameCond)) : new BooleanBuilder();
    }

    private BooleanBuilder nicknameCt(String nicknameCond) {
        return nicknameCond != null ? new BooleanBuilder(post.nickname.contains(nicknameCond)) : new BooleanBuilder();
    }

    private BooleanBuilder postSearchCond(PostSearchType searchType, String searchValue) {
        if (searchType == PostSearchType.TITLE) {
            return titleCt(searchValue);
        } else if (searchType == PostSearchType.CONTENT) {
            return contentCt(searchValue);
        } else if (searchType == PostSearchType.TITLE_CONTENT) {
            return titleCt(searchValue).or(contentCt(searchValue));
        } else if (searchType == PostSearchType.CREATE_MEMBER) {
            return createMemberNameCt(searchValue).or(nicknameCt(searchValue)); //and 하위로 nickname query 들어가는 문제 수정필요
        } else {
            return new BooleanBuilder();
        }
    }

    //전체 개수
    public int getTotalSize() {
        int size = em.createQuery("select p from Post p", Post.class)
                .getResultList().size();

        return size;
    }

    //단건 조회
    public Post findPost(Long id) {
        Post post = em.find(Post.class, id);
        return post;
    }

    //답글 순서 정렬
    public void orderSort(Long postId) {
        em.createQuery("select p from Post p where p.group = :postId and p.depth > 0", Post.class)
                .setParameter("postId", postId)
                .getResultList()
                .forEach(post -> post.orderSort());
    }
}
