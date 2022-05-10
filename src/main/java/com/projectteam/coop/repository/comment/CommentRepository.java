package com.projectteam.coop.repository.comment;

import com.projectteam.coop.domain.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentRepository {
    @PersistenceContext(unitName = "h2Jpa")
    private EntityManager em;

    //등록
    public Long addComment(Comment comment) {
        if (comment.getParent() == null) {
            em.persist(comment);
            comment.initGroup();
        }else {
            em.persist(comment);
        }
        return comment.getId();
    }

    //미사용 상태로 변경
    public void changeStateComment(Comment comment) {
        comment.delete();
    }

    //삭제
    public void removeComment(Long id) {
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
    }

    //목록 조회
    public List<Comment> findComments(int offset, int size, Long postId) {
        List<Comment> findComments = em.createQuery("select p from Comment p left join fetch p.createMember where p.post.postId = :postId order by p.group desc, p.order asc", Comment.class)
                .setParameter("postId", postId)
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();

        return findComments;
    }

    //단건 조회
    public Comment findComment(Long id) {
        Comment post = em.find(Comment.class, id);
        return post;
    }

    //전체 개수
    public int getTotalSize() {
        int size = em.createQuery("select p from Comment p", Comment.class)
                .getResultList().size();

        return size;
    }

    //댓글 순서 정렬
    public void orderSort(Long commentId) {
        em.createQuery("select p from Comment p where p.parent.id = :commentId and p.depth > 0", Comment.class)
                .setParameter("commentId", commentId)
                .getResultList()
                .forEach(Comment::orderSort);
    }

}
