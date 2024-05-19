package com.vti.ecommerce.services;

import com.vti.ecommerce.domains.User;

public interface UserService {
    User getUser(String id);
    User createUser(User User);
    User updateUser(Long id, User User);
    boolean deleteUser(Long id);
}
