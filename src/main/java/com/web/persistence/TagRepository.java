package com.web.persistence;


import com.web.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 28.10.15.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
}
