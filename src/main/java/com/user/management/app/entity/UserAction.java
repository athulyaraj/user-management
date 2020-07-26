package com.user.management.app.entity;

import com.user.management.app.enums.ActionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAction implements BaseEntity {
    String username;
    List<ActionType> actionTypeList;
}
