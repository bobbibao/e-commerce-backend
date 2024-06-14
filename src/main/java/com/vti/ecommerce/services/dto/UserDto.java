package com.vti.ecommerce.services.dto;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.vti.ecommerce.domains.entities.Address;
import com.vti.ecommerce.domains.entities.User;

import lombok.Data;

@Data
public class UserDto {
    private String id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String adress;
    private String password;
    private String role;
    private List<WishListDto> userWishlist;

    public UserDto() {
    }

    public UserDto(String id, String name, String lastname, String email, String phone, String adress, String password, String role,
            List<WishListDto> userWishlist) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.adress = adress;
        this.password = password;
        this.role = role;
        this.userWishlist = userWishlist;
    }

    public User convertToEntity() {
        User user = new User();
        Address address = new Address();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        address.setAddress(this.adress);
        user.setUserID(this.id);
        user.setFirstName(this.name);
        user.setLastName(this.lastname);
        user.setEmail(this.email);
        user.setPhone(this.phone);
        user.setAddress(address);
        user.setPasswordHash(encoder.encode(this.password));
        
        return user;
    }

}
