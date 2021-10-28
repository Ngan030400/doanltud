package com.example.projectappchat.service.role;

import com.example.projectappchat.entity.Role;
import com.example.projectappchat.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findById(Long roleId) {
        return roleRepository.findById(roleId).get();
    }
}
