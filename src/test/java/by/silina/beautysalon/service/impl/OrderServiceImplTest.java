package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.OrderDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.OrderForAdminDto;
import by.silina.beautysalon.model.dto.OrderForClientDto;
import by.silina.beautysalon.model.dto.OrderFormDto;
import by.silina.beautysalon.model.entity.Order;
import by.silina.beautysalon.model.entity.OrderStatus;
import by.silina.beautysalon.model.entity.Serv;
import by.silina.beautysalon.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * The OrderServiceImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    @InjectMocks
    OrderServiceImpl orderService;
    @Mock
    OrderDaoImpl orderDao;

    /**
     * Tests finding order by its id.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findById() throws DaoException, ServiceException {
        var orderId = 1L;
        var order = Order.builder()
                .id(orderId)
                .build();
        var expectedResult = Optional.of(order);

        Mockito.when(orderDao.findById(orderId)).thenReturn(expectedResult);

        var actualResult = orderService.findById(orderId);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding number of all orders.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findNumberOfAllOrders() throws DaoException, ServiceException {
        var orders = List.of(
                Order.builder().build(),
                Order.builder().build());
        long expectedResult = orders.size();

        Mockito.when(orderDao.findNumberOfOrders()).thenReturn(expectedResult);

        var actualResult = orderService.findNumberOfOrders();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding number of user's orders.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findNumberOfUserOrders() throws DaoException, ServiceException {
        var userId = 5L;
        var orders = List.of(
                Order.builder().user(User.builder().id(userId).build()).build(),
                Order.builder().user(User.builder().id(userId).build()).build());
        long expectedResult = orders.size();

        Mockito.when(orderDao.findNumberOfOrders(userId)).thenReturn(expectedResult);

        var actualResult = orderService.findNumberOfOrders(userId);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests paged OrderForAdminDto list.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findPagedOrderForAdminDtoList() throws DaoException, ServiceException {
        var fromOrderId = 1L;
        var numberOfOrders = 2;
        var userId1 = 1L;
        var userId2 = 2L;

        var order1 = createOrder1(fromOrderId, userId1);
        var order2 = createOrder2(userId2);
        var orders = List.of(order1, order2);

        var expectedResult = List.of(
                OrderForAdminDto.builder()
                        .id(order1.getId())
                        .orderDateTime(order1.getOrderDateTime())
                        .visitDate(order1.getVisitDate())
                        .visitBeginTime(order1.getVisitBeginTime())
                        .visitEndTime(order1.getVisitEndTime())
                        .serviceNames(order1.getServices().stream()
                                .map(Serv::getName)
                                .toList())
                        .priceWithDiscount(order1.getPriceWithDiscount())
                        .username(order1.getUser().getUsername())
                        .firstName(order1.getUser().getFirstName())
                        .lastName(order1.getUser().getLastName())
                        .email(order1.getUser().getEmail())
                        .phoneNumber(order1.getUser().getPhoneNumber())
                        .status(order1.getOrderStatus().getStatus())
                        .description(order1.getOrderStatus().getDescription())
                        .build(),
                OrderForAdminDto.builder()
                        .id(order2.getId())
                        .orderDateTime(order2.getOrderDateTime())
                        .visitDate(order2.getVisitDate())
                        .visitBeginTime(order2.getVisitBeginTime())
                        .visitEndTime(order2.getVisitEndTime())
                        .serviceNames(order2.getServices().stream()
                                .map(Serv::getName)
                                .toList())
                        .priceWithDiscount(order2.getPriceWithDiscount())
                        .username(order2.getUser().getUsername())
                        .firstName(order2.getUser().getFirstName())
                        .lastName(order2.getUser().getLastName())
                        .email(order2.getUser().getEmail())
                        .phoneNumber(order2.getUser().getPhoneNumber())
                        .status(order2.getOrderStatus().getStatus())
                        .description(order2.getOrderStatus().getDescription())
                        .build());

        Mockito.when(orderDao.findPagedOrders(fromOrderId, numberOfOrders)).thenReturn(orders);

        var actualResult = orderService.findPagedOrderForAdminDtoList(fromOrderId, numberOfOrders);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests finding paged OrderForClientDto list.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void findPagedOrderForClientDtoList() throws DaoException, ServiceException {
        var fromOrderId = 1L;
        var numberOfOrders = 2;
        var userId = 1L;

        var order1 = createOrder1(fromOrderId, userId);
        var order2 = createOrder2(userId);
        var orders = List.of(order1, order2);

        var expectedResult = List.of(
                OrderForClientDto.builder()
                        .id(order1.getId())
                        .orderDateTime(order1.getOrderDateTime())
                        .visitDate(order1.getVisitDate())
                        .visitBeginTime(order1.getVisitBeginTime())
                        .visitEndTime(order1.getVisitEndTime())
                        .serviceNames(order1.getServices().stream()
                                .map(Serv::getName)
                                .toList())
                        .priceWithDiscount(order1.getPriceWithDiscount())
                        .status(order1.getOrderStatus().getStatus())
                        .description(order1.getOrderStatus().getDescription())
                        .build(),
                OrderForClientDto.builder()
                        .id(order2.getId())
                        .orderDateTime(order2.getOrderDateTime())
                        .visitDate(order2.getVisitDate())
                        .visitBeginTime(order2.getVisitBeginTime())
                        .visitEndTime(order2.getVisitEndTime())
                        .serviceNames(order2.getServices().stream()
                                .map(Serv::getName)
                                .toList())
                        .priceWithDiscount(order2.getPriceWithDiscount())
                        .status(order2.getOrderStatus().getStatus())
                        .description(order2.getOrderStatus().getDescription())
                        .build());

        Mockito.when(orderDao.findPagedOrders(fromOrderId, numberOfOrders, userId)).thenReturn(orders);

        var actualResult = orderService.findPagedOrderForClientDtoList(fromOrderId, numberOfOrders, userId);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    /**
     * Tests adding new order.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void addOrder() throws DaoException, ServiceException {
        var orderDto = OrderFormDto.builder()
                .visitDate(LocalDate.now())
                .visitBeginTime(LocalTime.parse("11:20:00"))
                .visitEndTime(LocalTime.parse("11:55:00"))
                .priceWithDiscount(BigDecimal.valueOf(76.30))
                .userId(1L)
                .servicesIds(List.of(1L, 2L))
                .timeSlotIds(List.of(4L))
                .build();

        Mockito.when(orderDao.insert(Mockito.any(Order.class))).thenReturn(true);

        var isOrderAdded = orderService.addOrder(orderDto);
        Assertions.assertTrue(isOrderAdded);
    }

    /**
     * Tests changing order status.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void changeStatus() throws DaoException, ServiceException {
        var orderId = 6L;
        var newStatusName = "new status";

        Mockito.when(orderDao.changeStatus(orderId, newStatusName)).thenReturn(true);

        var isOrderStatusChanges = orderService.changeStatus(orderId, newStatusName);
        Assertions.assertTrue(isOrderStatusChanges);
    }

    /**
     * Creates order.
     * This test can be fully covered only by using integration test.
     */
    Order createOrder1(long fromOrderId, Long userId) {
        return Order.builder()
                .id(fromOrderId)
                .orderDateTime(LocalDateTime.parse("2022-07-24 09:00:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .visitDate(LocalDate.now())
                .visitBeginTime(LocalTime.parse("11:20:00"))
                .visitEndTime(LocalTime.parse("11:55:00"))
                .priceWithDiscount(BigDecimal.valueOf(76.30))
                .orderStatus(OrderStatus.builder()
                        .status("waiting")
                        .description("Waiting for confirmation.")
                        .build())
                .user(User.builder()
                        .id(userId)
                        .username("client")
                        .firstName("Cli")
                        .lastName("Ent")
                        .email("client@gmail.com")
                        .phoneNumber("+1238882")
                        .build())
                .services(List.of(Serv.builder()
                        .name("serv name")
                        .build()))
                .build();
    }

    /**
     * Creates order.
     * This test can be fully covered only by using integration test.
     */
    Order createOrder2(Long userId) {
        return Order.builder()
                .id(2L)
                .orderDateTime(LocalDateTime.parse("2022-08-24 09:00:00", DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)))
                .visitDate(LocalDate.now())
                .visitBeginTime(LocalTime.parse("11:10:00"))
                .visitEndTime(LocalTime.parse("11:40:00"))
                .priceWithDiscount(BigDecimal.valueOf(60.30))
                .orderStatus(OrderStatus.builder()
                        .status("waiting")
                        .description("Waiting for confirmation.")
                        .build())
                .user(User.builder()
                        .id(userId)
                        .username("client")
                        .firstName("Cli")
                        .lastName("Ent")
                        .email("client@gmail.com")
                        .phoneNumber("+1238882")
                        .build())
                .services(List.of(Serv.builder()
                        .name("serv name")
                        .build()))
                .build();
    }
}
