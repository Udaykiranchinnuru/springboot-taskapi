package com.jspiders.taskapi.data.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    boolean existsByEmailOrMobile(String email,String mobile);
    boolean existsByMobile(String mobile);
    Optional<AppUser> findByEmail(String email);
    AppUser findByMobile(String mobile);
    AppUser findByName(String name);
    boolean existsByEmailAndPassword(String email,String password);
    boolean existsByMobileAndPassword(String mobile,String password);

    AppUser save(AppUser appUser);

    Optional<AppUser> findById(Long userId);

    Optional<AppUser> findByEmailAndUserId(String oldEmail, String userId);
}
