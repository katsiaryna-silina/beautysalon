package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.VisitTimeDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.model.dto.VisitTimeSlotDto;
import by.silina.beautysalon.model.entity.VisitTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * The VisitTimeServiceImplTest test class.
 *
 * @author Silina Katsiaryna
 */
@ExtendWith(MockitoExtension.class)
class VisitTimeServiceImplTest {
    @InjectMocks
    VisitTimeServiceImpl visitTimeService;
    @Mock
    VisitTimeDaoImpl visitTimeDao;

    /**
     * Tests getting VisitTimeSlotDto list.
     * This test can be fully covered only by using integration test.
     */
    @Test
    void getVisitTimeSlotDtos() throws DaoException, ServiceException {
        var visitDate = LocalDate.now().plusDays(1);
        var neededMinutes = 20;
        var beginTime = LocalTime.of(10, 20);

        var visitTimeSlot = VisitTime.builder()
                .id(2L)
                .beginTime(beginTime)
                .endTime(LocalTime.of(10, 50))
                .build();

        long duration = visitTimeSlot.getBeginTime().until(visitTimeSlot.getEndTime(), ChronoUnit.MINUTES);

        var expectedResult = List.of(VisitTimeSlotDto.builder()
                .visitTimeIdDuration(Map.of(visitTimeSlot.getId(), duration))
                .beginTime(beginTime)
                .endTime(visitTimeSlot.getBeginTime().plusMinutes(neededMinutes))
                .build());

        var freeTimeSlots = List.of(visitTimeSlot);

        Mockito.when(visitTimeDao.findFreeVisitTimeSlots(visitDate)).thenReturn(freeTimeSlots);

        var actualResult = visitTimeService.getVisitTimeSlotDtos(visitDate, neededMinutes);
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
