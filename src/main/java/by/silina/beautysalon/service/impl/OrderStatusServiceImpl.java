package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.OrderStatusDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.entity.OrderStatus;
import by.silina.beautysalon.model.entity.Role;
import by.silina.beautysalon.service.OrderStatusService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The OrderStatusServiceImpl class that responsible for processing OrderStatus.
 *
 * @author Silina Katsiaryna
 */
public class OrderStatusServiceImpl implements OrderStatusService {
    private static final OrderStatusServiceImpl instance = new OrderStatusServiceImpl(OrderStatusDaoImpl.getInstance());
    private final OrderStatusDaoImpl orderStatusDao;

    /**
     * Initializes a new OrderStatusServiceImpl.
     */
    private OrderStatusServiceImpl(OrderStatusDaoImpl orderStatusDao) {
        this.orderStatusDao = orderStatusDao;
    }

    /**
     * Gets the single instance of OrderStatusServiceImpl.
     *
     * @return OrderStatusServiceImpl
     */
    public static OrderStatusServiceImpl getInstance() {
        return instance;
    }

    /**
     * Finds all order statuses.
     *
     * @return List of OrderStatus
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public List<OrderStatus> findAll() throws ServiceException {
        try {
            return orderStatusDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds order status by its name.
     *
     * @param statusName String. Status name.
     * @return Optional of OrderStatus
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public Optional<OrderStatus> findByName(String statusName) throws ServiceException {
        try {
            return orderStatusDao.findByName(statusName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds default order status when order has just created.
     *
     * @return Optional of OrderStatus
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public Optional<OrderStatus> findDefault() throws ServiceException {
        try {
            return orderStatusDao.findDefault();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Finds order status names available for update the order by client.
     *
     * @param orderStatusCurrentName String. Order current status name.
     * @return List of String. List of order status names to update the order.
     */
    @Override
    public List<String> findOrderStatusNamesForClient(String orderStatusCurrentName) throws ServiceException {
        List<String> orderStatusNames = new ArrayList<>();

        var orderStatusOptional = findByName(orderStatusCurrentName);
        if (orderStatusOptional.isPresent()) {

            Role accessibleTo = orderStatusOptional.get().getAccessibleTo();
            if (!Role.ADMIN.equals(accessibleTo)) {

                orderStatusNames = getStatusNamesForRole(Role.CLIENT, orderStatusCurrentName);
                orderStatusNames = filterStatusNamesWithoutDefaultValue(orderStatusNames);
            }
        }
        return orderStatusNames;
    }

    /**
     * Finds order status names available for update the order by admin.
     *
     * @param orderStatusCurrentName String. Order current status name.
     * @return List of String. List of order status names to update the order.
     */
    @Override
    public List<String> findOrderStatusNamesForAdmin(String orderStatusCurrentName) throws ServiceException {
        List<String> orderStatusNames = new ArrayList<>();

        var orderStatusOptional = findByName(orderStatusCurrentName);
        if (orderStatusOptional.isPresent()) {

            orderStatusNames = getStatusNamesForRole(Role.ADMIN, orderStatusCurrentName);
            orderStatusNames = filterStatusNamesWithoutDefaultValue(orderStatusNames);

        }
        return orderStatusNames;
    }

    /**
     * Finds order status names available for update the order.
     *
     * @param role                   Role. User role.
     * @param orderStatusCurrentName String. Order current status name.
     * @return List of String. List of order status names.
     */
    private List<String> getStatusNamesForRole(Role role, String orderStatusCurrentName) throws ServiceException {
        return findAll()
                .stream()
                .filter(el -> el.getAccessibleTo().equals(role))
                .map(OrderStatus::getStatus)
                .filter(status -> !status.equals(orderStatusCurrentName))
                .toList();
    }

    /**
     * Filters order status names without default order status value.
     *
     * @param orderStatusNames List of String. Order status names to filter.
     * @return List of String. List of order status names.
     */
    private List<String> filterStatusNamesWithoutDefaultValue(List<String> orderStatusNames) throws ServiceException {
        var defaultStatusOptional = findDefault();
        if (defaultStatusOptional.isPresent()) {
            var defaultStatus = defaultStatusOptional.get();

            orderStatusNames = orderStatusNames.stream().
                    filter(el -> !defaultStatus.getStatus().equals(el))
                    .toList();
        }
        return orderStatusNames;
    }
}
