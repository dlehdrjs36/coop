package com.projectteam.coop.service;

import com.projectteam.coop.domain.Post;
import com.projectteam.coop.repository.PostRepository;
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

    public List<Post> findPosts(int offset, int size) {
        return postRepository.findPosts(offset, size);
    }

    public int totalSize() {
        return postRepository.getTotalSize();
    }

}
