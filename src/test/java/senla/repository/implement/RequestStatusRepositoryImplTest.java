package senla.repository.implement;

import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.config.HibernateConfig;
import senla.config.LiquibaseConfig;
import senla.entities.RequestStatus;
import senla.entities.Role;
import senla.entities.User;
import senla.enums.RoleName;
import senla.enums.StatusRequest;
import senla.repository.api.RequestStatusRepository;
import senla.repository.api.UserRepository;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class RequestStatusRepositoryImplTest {
    @Resource
    private RequestStatusRepository requestStatusRepository;

    @Test
    public void findAllWithFetch() {
        RequestStatus requestStatus = RequestStatus.builder()
                .requestStatus(StatusRequest.PENDING)
                .build();

        requestStatusRepository.save(requestStatus);

        List<RequestStatus> requestStatuses = requestStatusRepository.findAllWithFetch(requestStatus.getRequestStatus());

        assertNotNull(requestStatuses);
        assertFalse(requestStatuses.isEmpty());

        requestStatuses.forEach(x -> assertEquals(requestStatus.getRequestStatus(), x.getRequestStatus()));
    }

    @Test
    public void findAllWithJoinFetch() {
        RequestStatus requestStatus = RequestStatus.builder()
                .requestStatus(StatusRequest.PENDING)
                .build();

        requestStatusRepository.save(requestStatus);

        List<RequestStatus> requestStatuses = requestStatusRepository.findAllWithJoinFetch(requestStatus.getRequestStatus());

        assertNotNull(requestStatuses);
        assertFalse(requestStatuses.isEmpty());
    }



}