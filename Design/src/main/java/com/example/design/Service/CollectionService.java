//package com.example.design.Service;
//
//
//import com.example.design.Model.Collection;
//import com.example.design.Model.Customer;
//import com.example.design.Model.Design;
//import com.example.design.Model.Order_table;
//import com.example.design.Repository.CollectionRepository;
//import com.example.design.Repository.CustomerRepository;
//import com.example.design.Repository.OrderRepository;
//import com.example.design.Repository.RateRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class CollectionService {
//
//
//    private final CollectionRepository collectionRepository;
//    private final OrderRepository orderRepository;
//    private final CustomerRepository customerRepository;
//    private final RateRepository rateRepository;
//
//
//    public List<Collection> getAllCollections() {
//        return collectionRepository.findAll();
//    }
//
//    public Collection getCollectionById(Integer id) {
//        return collectionRepository.findCollectionById(id);
//    }
//
//    public void addCollection(Collection collection) {
//        collectionRepository.save(collection);
//    }
//
//    public boolean deleteCollection(Integer id) {
//        Collection collection = collectionRepository.findCollectionById(id);
//        if (collection == null) {
//            return false;
//        }
//        collectionRepository.delete(collection);
//        return true;
//    }
//
//
//    public List<Design> getDesignsByCollection(Integer collectionId) {
//        return collectionRepository.findDesignsByCollectionId(collectionId);
//    }
//
//
//    // Filter collections by price range
//    public List<Collection> filterByPriceRange(Double minPrice, Double maxPrice) {
//        return collectionRepository.findByPriceBetween(minPrice, maxPrice);
//    }
//}
