//package com.example.demo.controller.admin;
//
//import com.example.demo.entity.User;
//import com.example.demo.search.ElasticSearchRepository;
//import com.example.demo.search.UserDocument;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/admin/search")
//@RequiredArgsConstructor
//public class SearchController {
//    private final ElasticSearchRepository searchRepository;
//
//    @GetMapping
//    public List<UserDocument> search(
//            @RequestParam(required = false) String username,
//            @RequestParam(required = false) String firstName,
//            @RequestParam(required = false) String lastName
//    ) {
//
//        if (username != null)
//            return searchRepository.findByUsernameContaining(username);
//
//        if (firstName != null)
//            return searchRepository.findByFirstNameContaining(firstName);
//
//        if (lastName != null)
//            return searchRepository.findByLastNameContaining(lastName);
//
//        return List.of();
//    }
//
//    @GetMapping("/users")
//    public List<User> searchUsers(
//            @RequestParam(required = false) String role,
//            @RequestParam(required = false) String firstName,
//            @RequestParam(required = false) String lastName
//    ) {
//        return searchRepository.search(role, firstName, lastName);
//    }
//
//}
