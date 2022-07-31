package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.DiscountStatusDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.DiscountStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The DiscountStatusServiceImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class DiscountStatusServiceImplTest {
    @InjectMocks
    DiscountStatusServiceImpl discountStatusService;
    @Mock
    DiscountStatusDaoImpl discountStatusDao;

    /**
     * Tests finding all discount statuses.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findAll() throws DaoException, ServiceException {
        var expectedResult = List.of(
                DiscountStatus.builder().status("status1").build(),
                DiscountStatus.builder().status("status2").build());

        Mockito.when(discountStatusDao.findAll()).thenReturn(expectedResult);

        var actualResult = discountStatusService.findAll();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding maximum discount status.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findMaximum() throws DaoException, ServiceException {
        var discountStatus = DiscountStatus.builder()
                .id(1L)
                .status("status1")
                .discount(BigDecimal.valueOf(20.6))
                .build();
        var expectedResult = Optional.of(discountStatus);

        Mockito.when(discountStatusDao.findMaximum()).thenReturn(expectedResult);

        var actualResult = discountStatusService.findMaximum();
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
