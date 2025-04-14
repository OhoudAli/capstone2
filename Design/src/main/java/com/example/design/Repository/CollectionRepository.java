package com.example.design.Repository;


import com.example.design.Model.Collection;
import com.example.design.Model.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionRepository  extends JpaRepository<Collection,Integer> {

    Collection findCollectionById(Integer id);

    @Query("select d from Design  d where d.collectionId=?1")
    List<Design> findDesignsByCollectionId(Integer id);

    List<Collection> findByPriceBetween(Double minPrice, Double maxPrice);

//    List<Collection> findByT
}
