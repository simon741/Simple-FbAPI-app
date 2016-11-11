package sk.simon;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Simon on 10.11.2016.
 */


public interface FbUserRepository extends JpaRepository<FbUser,Long> {

    List<FbUser> findByFbId(String FbId);
}
