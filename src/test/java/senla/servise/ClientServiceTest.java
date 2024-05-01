package senla.servise;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.config.HibernateConfig;
import senla.config.LiquibaseConfig;
import senla.dto.client.ClientDTOToEntity;
import senla.dto.client.ClientFullInfoDTO;
import senla.dto.client.ClientShortInfoDTO;
import senla.dto.user.UserDTOToEntity;
import senla.entities.Client;
import senla.mapper.ClientMapper;
import senla.repository.api.ClientRepository;
import senla.servise.implement.ClientServiceImpl;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class ClientServiceTest{
    @InjectMocks
    private ClientServiceImpl clientService;

    @Spy
    private ClientRepository clientRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest(){
        Client client = Client.builder()
                .id(1L)
                .build();

        Client client1 = Client.builder()
                .id(1L)
                .build();

        Mockito.when(clientRepository.findAll()).thenReturn(List.of(client, client1));

        List<ClientShortInfoDTO> actual = clientService.findAll();

        Mockito.verify(clientRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void findByIdTest(){
        Client client = Client.builder()
                .id(1L)

                .build();

        Mockito.when(clientRepository.findById(any(Long.class))).thenReturn(client);

        ClientShortInfoDTO expected = ClientMapper.convertToShortDto(client);
        ClientShortInfoDTO actual = clientService.findById(client.getId());

        Mockito.verify(clientRepository, Mockito.times(1)).findById(Mockito.any());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getClientCard(), actual.getClientCard());
    }
    @Test
    public void saveTest(){
        ClientDTOToEntity dto = ClientDTOToEntity.builder()
                .user(UserDTOToEntity.builder().build())
                .build();

        Client client = ClientMapper.createClientDto(dto);

        Mockito.doNothing().when(clientRepository).save(Mockito.any(Client.class));

        clientService.save(dto);

        Mockito.verify(clientRepository, Mockito.times(1)).save(Mockito.any(Client.class));
    }


    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        clientService.deleteById(id);
        Mockito.verify(clientRepository, Mockito.times(1)).deleteById(id);
    }
}
