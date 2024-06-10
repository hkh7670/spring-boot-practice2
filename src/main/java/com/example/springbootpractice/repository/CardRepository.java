package com.example.springbootpractice.repository;

import com.example.springbootpractice.model.entity.Card;
import com.example.springbootpractice.model.entity.UserPoint;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

//  @Query("select i from ImageInfo i where (:title is null or i.title like :title)")
//  Page<ImageInfo> findAllByTitleLike(Pageable pageable, String title);
  List<Card> findByUserSeq(long seq);
  Optional<Card> findByUserSeqAndCardNum(long seq, String cardNum);
}
