package senla.service;

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
import senla.dto.clientcard.ClientCardDTO;
import senla.dto.clientcard.ClientCardDTOToEntity;
import senla.entities.ClientCard;
import senla.mapper.ClientCardMapper;
import senla.repository.api.ClientCardRepository;
import senla.service.impl.ClientCardServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(
        classes = {HibernateConfig.class, LiquibaseConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class ClientCardServiceTest{
    @InjectMocks
    private ClientCardServiceImpl clientCardService;

    @Spy
    private ClientCardRepository clientCardRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllTest(){
        ClientCard clientCard = ClientCard.builder()
                .id(1L)
                .diagnos("test")
                .recommendations("Die")
                .build();

        ClientCard clientCard1 = ClientCard.builder()
                .id(1L)
                .diagnos("die")
                .recommendations("test")
                .build();

        Mockito.when(clientCardRepository.findAll()).thenReturn(List.of(clientCard, clientCard1));

        List<ClientCardDTO> actual = clientCardService.findAll();

        Mockito.verify(clientCardRepository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actual.isEmpty());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void findByIdTest(){
        ClientCard clientCard = ClientCard.builder()
                .id(1L)
                .diagnos("test")
                .recommendations("Die")
                .build();

        Mockito.when(clientCardRepository.findById(any(Long.class))).thenReturn(clientCard);

        ClientCardDTO expected = ClientCardMapper.convertToDto(clientCard);
        ClientCardDTO actual = clientCardService.findById(clientCard.getId());

        Mockito.verify(clientCardRepository, Mockito.times(1)).findById(Mockito.any());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getDiagnosis(), actual.getDiagnosis());
    }

    @Test
    public void saveTest(){
        ClientCardDTOToEntity dto = ClientCardDTOToEntity.builder()
                .diagnosis("test")
                .recommendations("Die")
                .build();

        ClientCard clientCard = ClientCardMapper.createClientCardDto(dto);

        Mockito.doNothing().when(clientCardRepository).save(Mockito.any(ClientCard.class));

        clientCardService. save(dto);

        Mockito.verify(clientCardRepository, Mockito.times(1)).save(Mockito.any(ClientCard.class));
    }

    @Test
    public void updateTest(){
        ClientCard clientCard = ClientCard.builder()
                .id(1L)
                .diagnos("test")
                .recommendations("Die")
                .build();

        ClientCardDTOToEntity dto = new ClientCardDTOToEntity();
        dto.setDiagnosis("TEST");

        Mockito.when(clientCardRepository.findById(clientCard.getId())).thenReturn(clientCard);

        clientCardService.update(clientCard.getId(), dto);

        Mockito.verify(clientCardRepository, Mockito.times(1)).save(Mockito.any(ClientCard.class));
    }

    @Test
    public void deleteByIdTest(){
        Long id = 1L;

        clientCardService.deleteById(id);
        Mockito.verify(clientCardRepository, Mockito.times(1)).deleteById(id);
    }
}
