package com.resortmanagement.system.hr.dto.role;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {
    private String name;
    private String description;
    private String permissionsJson;
}
