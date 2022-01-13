package com.armsV2.armsApi.repositories;

    import org.springframework.data.jpa.repository.JpaRepository;

    import javax.management.relation.Role;
    import java.util.Optional;

    public interface RoleRepository extends JpaRepository<Role, Long> {
        Optional<Role> findByName(String name);

    }
