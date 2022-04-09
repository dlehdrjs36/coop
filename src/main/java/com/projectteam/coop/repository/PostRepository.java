package com.projectteam.coop.repository;

import com.projectteam.coop.domain.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PostRepository {

    @PersistenceContext(unitName = "h2Jpa")
    private EntityManager em;

    //등록
    public Long addPost(Post post) {
        em.persist(post);
        return post.getPostId();
    }

    //삭제
    public void removePost(Long id) {
        em.remove(id);
    }

    //수정
    public Long updatePost(Post post) {
        Post findPost = em.find(Post.class, post.getPostId());
        findPost.changePost(post.getBoard(), post.getPostStatus(), post.getUpperPostId(), post.getPassword(), post.getTitle(), post.getContent(), post.getViewCount(), post.getRecommendCount());
        return post.getPostId();
    }

    //목록 조회
    public List<Post> findPosts(int offset, int size) {
        List<Post> findPosts = em.createQuery("select p from Post p", Post.class)
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();

        return findPosts;
    }

    //전체 개수
    public int getTotalSize() {
        int size = em.createQuery("select p from Post p", Post.class)
                .getResultList().size();

        return size;
    }
}
