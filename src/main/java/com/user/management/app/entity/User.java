package com.user.management.app.entity;

import com.user.management.app.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements BaseEntity{

    int userId;
    String userName;
    String password;
    boolean isAdmin;
    Role defaultRole;
}
