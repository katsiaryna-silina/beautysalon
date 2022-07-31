package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.OrderStatusDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.OrderStatus;
import by.silina.beautysalon.model.entity.Role;
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
 * The OrderStatusServiceImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class OrderStatusServiceImplTest {
    @InjectMocks
    OrderStatusServiceImpl orderStatusService;
    @Mock
    OrderStatusDaoImpl orderStatusDao;

    /**
     * Tests finding all order statuses.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findAll() throws DaoException, ServiceException {
        var expectedResult = List.of(
                OrderStatus.builder().id(1L).build(),
                OrderStatus.builder().id(2L).build());

        Mockito.when(orderStatusDao.findAll()).thenReturn(expectedResult);

        var actualResult = orderStatusService.findAll();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding order status by its name.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findByName() throws DaoException, ServiceException {
        var orderStatusName = "status name";

        var orderStatus = OrderStatus.builder().status(orderStatusName).build();
        var expectedResult = Optional.of(orderStatus);

        Mockito.when(orderStatusDao.findByName(orderStatusName)).thenReturn(expectedResult);

        var actualResult = orderStatusService.findByName(orderStatusName);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding default order status.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findDefault() throws DaoException, ServiceException {
        var defaultOrderStatus = OrderStatus.builder().id(4L).build();
        var expectedResult = Optional.of(defaultOrderStatus);

        Mockito.when(orderStatusDao.findDefault()).thenReturn(expectedResult);

        var actualResult = orderStatusService.findDefault();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding order statuses available to client.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findOrderStatusNamesForClient() throws DaoException, ServiceException {
        var currentStatusName = "current status";
        var currentOrderStatus = OrderStatus.builder()
                .status(currentStatusName)
                .accessibleTo(Role.CLIENT)
                .build();
        var currentOrderStatusOptional = Optional.of(currentOrderStatus);

        var allStatuses = List.of(
                OrderStatus.builder()
                        .id(1L)
                        .accessibleTo(Role.CLIENT)
                        .status("status1")
                        .build(),
                OrderStatus.builder()
                        .id(2L)
                        .accessibleTo(Role.CLIENT)
                        .status("status2")
                        .build());
        var expectedResult = allStatuses.stream().map(OrderStatus::getStatus).toList();

        var defaultOrderStatus = OrderStatus.builder()
                .id(4L)
                .status("default status")
                .build();
        var defaultStatusOptional = Optional.of(defaultOrderStatus);

        Mockito.when(orderStatusDao.findByName(currentStatusName)).thenReturn(currentOrderStatusOptional);
        Mockito.when(orderStatusDao.findDefault()).thenReturn(defaultStatusOptional);
        Mockito.when(orderStatusDao.findAll()).thenReturn(allStatuses);

        var actualResult = orderStatusService.findOrderStatusNamesForClient(currentStatusName);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding order statuses available to admin.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findOrderStatusNamesForAdmin() throws DaoException, ServiceException {
        var currentStatusName = "current status";
        var currentOrderStatus = OrderStatus.builder()
                .status(currentStatusName)
                .accessibleTo(Role.ADMIN)
                .build();
        var currentOrderStatusOptional = Optional.of(currentOrderStatus);

        var allStatuses = List.of(
                OrderStatus.builder()
                        .id(1L)
                        .accessibleTo(Role.ADMIN)
                        .status("status1")
                        .build(),
                OrderStatus.builder()
                        .id(2L)
                        .accessibleTo(Role.ADMIN)
                        .status("status2")
                        .build());
        var expectedResult = allStatuses.stream().map(OrderStatus::getStatus).toList();

        var defaultOrderStatus = OrderStatus.builder()
                .id(4L)
                .status("default status")
                .build();
        var defaultStatusOptional = Optional.of(defaultOrderStatus);

        Mockito.when(orderStatusDao.findByName(currentStatusName)).thenReturn(currentOrderStatusOptional);
        Mockito.when(orderStatusDao.findDefault()).thenReturn(defaultStatusOptional);
        Mockito.when(orderStatusDao.findAll()).thenReturn(allStatuses);

        var actualResult = orderStatusService.findOrderStatusNamesForAdmin(currentStatusName);
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
