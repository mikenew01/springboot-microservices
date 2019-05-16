package io.mk.repositories;

import io.mk.models.ApplicationUser;
import io.mk.models.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {

    ApplicationUser finbByUsername(String username);

}
