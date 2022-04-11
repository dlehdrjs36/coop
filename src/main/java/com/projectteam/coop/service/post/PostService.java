package com.projectteam.coop.service.post;

import com.projectteam.coop.web.post.PostForm;
import com.projectteam.coop.domain.Post;
import com.projectteam.coop.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(transactionManager = "h2TxManager")
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Long addPost(Post post) {
        return postRepository.addPost(post);
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public List<Post> findPosts(int offset, int size) {
        return postRepository.findPosts(offset, size);
    }

    @Transactional(transactionManager = "h2TxManager", readOnly = true)
    public Post findPost(Long id) {
        return postRepository.findPost(id);
    }

    public Long updatePost(PostForm postForm) {
        return postRepository.updatePost(postForm);
    }

    public int totalSize() {
        return postRepository.getTotalSize();
    }

}
