package com.projectteam.coop.repository.post;

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
    public List<Post> findPosts(int offset, int size) {
        List<Post> findPosts = em.createQuery("select p from Post p left join fetch p.createMember order by p.group desc, p.order asc", Post.class)
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

    //단건 조회
    public Post findPost(Long id) {
        Post post = em.find(Post.class, id);
        return post;
    }

    //답글 순서 정렬
    public void orderSort(Long postId, Long depth) {
        em.createQuery("select p from Post p where p.parent.postId = :postId and p.depth = :depth", Post.class)
                .setParameter("postId", postId)
                .setParameter("depth", depth+1)
                .getResultList()
                .forEach(post -> post.orderSort());
    }
}
