//package com.example.demo.search;
//
//import com.example.demo.entity.Role;
//import com.example.demo.entity.User;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
//import java.util.List;
//
//public interface ElasticSearchRepository extends ElasticsearchRepository<UserDocument, Long> {
//
//    List<UserDocument> findByUsernameContaining(String username);
//
//    List<UserDocument> findByFirstNameContaining(String firstName);
//
//    List<UserDocument> findByLastNameContaining(String lastName);
//
//    List<UserDocument> findByRole(Role role);
//
//    List<User> search(String role, String firstName, String lastName);
//}
