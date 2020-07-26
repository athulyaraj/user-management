package com.user.management.app.entity;

import com.user.management.app.enums.AccessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Resource implements BaseEntity {
    int resourceId;
    String url;
    String resourceName;
    AccessType accessType;

    @Override
    public String toString(){
        return new StringBuffer()
                .append("resourceName = ")
                .append(resourceName)
                .append(", path = ")
                .append(url)
                .toString();
    }
}
