package com.carepay.assignment.repository;

import com.carepay.assignment.domain.PostInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostInfoRepository extends JpaRepository<PostInfo, Long>{

}
