package com.web.travelagency.repository;

import com.web.travelagency.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Posts, Integer> {
    List<Posts> findByUserId(Integer id);
    List<Posts> findByIsPublic(boolean isPublic);
}
