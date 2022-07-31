package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.ServDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.ServiceDto;
import by.silina.beautysalon.model.entity.Serv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

/**
 * The ServServiceImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class ServServiceImplTest {
    @InjectMocks
    ServServiceImpl servService;
    @Mock
    ServDaoImpl serviceDao;

    /**
     * Tests finding complex services.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findComplexServices() throws DaoException, ServiceException {
        var expectedResult = List.of(Serv.builder().isComplex(true).build());

        Mockito.when(serviceDao.findServices(true)).thenReturn(expectedResult);

        var actualResult = servService.findComplexServices();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding not complex services.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findNotComplexServices() throws DaoException, ServiceException {
        var expectedResult = List.of(Serv.builder().isComplex(false).build());

        Mockito.when(serviceDao.findServices(false)).thenReturn(expectedResult);

        var actualResult = servService.findNotComplexServices();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding service by its name.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findServiceByName() throws DaoException, ServiceException {
        var serviceName = "service name";
        var service = Serv.builder().name(serviceName).build();
        var expectedResult = Optional.of(service);

        Mockito.when(serviceDao.findServiceByName(serviceName)).thenReturn(expectedResult);

        var actualResult = servService.findServiceByName(serviceName);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests deleting service by its id.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void deleteById() throws DaoException, ServiceException {
        var serviceId = 6L;
        var expectedResult = true;

        Mockito.when(serviceDao.deleteById(serviceId)).thenReturn(expectedResult);

        var actualResult = servService.deleteById(serviceId);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests updating service by its id.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void updateById() throws DaoException, ServiceException {
        var serviceId = 4L;
        var expectedResult = true;

        Mockito.when(serviceDao.updateById(serviceId)).thenReturn(expectedResult);

        var actualResult = servService.updateById(serviceId);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding number of services.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findNumberOfServices() throws DaoException, ServiceException {
        var expectedResult = 7L;

        Mockito.when(serviceDao.findNumberOfServices()).thenReturn(expectedResult);

        var actualResult = servService.findNumberOfServices();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding paged ServiceDto list.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findPagedServiceDtoList() throws DaoException, ServiceException {
        var fromServiceId = 1L;
        var numberOfServicesPerPage = 2;

        var service1 = Serv.builder().id(fromServiceId).build();
        var service2 = Serv.builder().id(2L).build();
        var services = List.of(service1, service2);

        var expectedResult = List.of(
                ServiceDto.builder().id(fromServiceId).build(),
                ServiceDto.builder().id(2L).build());

        Mockito.when(serviceDao.findPagedServiceList(fromServiceId, numberOfServicesPerPage)).thenReturn(services);

        var actualResult = servService.findPagedServiceDtoList(fromServiceId, numberOfServicesPerPage);
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
