package by.silina.beautysalon.service.impl;

import by.silina.beautysalon.dao.impl.VisitTimeDaoImpl;
import by.silina.beautysalon.exception.DaoException;
import by.silina.beautysalon.exception.ServiceException;
import by.silina.beautysalon.mapper.VisitTimeMapper;
import by.silina.beautysalon.mapper.impl.VisitTimeMapperImpl;
import by.silina.beautysalon.model.dto.VisitTimeSlotDto;
import by.silina.beautysalon.model.entity.VisitTime;
import by.silina.beautysalon.service.VisitTimeService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The VisitTimeServiceImpl class that responsible for processing VisitTime.
 *
 * @author Silina Katsiaryna
 */
public class VisitTimeServiceImpl implements VisitTimeService {
    private static final VisitTimeServiceImpl instance = new VisitTimeServiceImpl(VisitTimeDaoImpl.getInstance());
    private final VisitTimeDaoImpl visitTimeDao;
    private final VisitTimeMapper visitTimeMapper = VisitTimeMapperImpl.getInstance();

    /**
     * Initializes a new VisitTimeServiceImpl.
     */
    private VisitTimeServiceImpl(VisitTimeDaoImpl visitTimeDao) {
        this.visitTimeDao = visitTimeDao;
    }

    /**
     * Gets the single instance of VisitTimeServiceImpl.
     *
     * @return VisitTimeServiceImpl
     */
    public static VisitTimeServiceImpl getInstance() {
        return instance;
    }

    /**
     * Finds visit time slots and maps them to dtos.
     *
     * @param visitDate     LocalDate. Date of visit.
     * @param neededMinutes Integer. Needed time for services in minutes.
     * @return List of VisitTimeSlotDto
     * @throws ServiceException if a service exception occurs.
     */
    @Override
    public List<VisitTimeSlotDto> getVisitTimeSlotDtos(LocalDate visitDate, Integer neededMinutes) throws ServiceException {
        List<VisitTimeSlotDto> visitTimeSlotDtos = new ArrayList<>();
        try {
            List<VisitTime> freeTimeSlots = visitTimeDao.findFreeVisitTimeSlots(visitDate);
            if (!freeTimeSlots.isEmpty()) {
                if (visitDate.isEqual(LocalDate.now())) {
                    freeTimeSlots = filterTimeSlots(freeTimeSlots);
                }

                visitTimeSlotDtos = getTimeSlotsForClient(freeTimeSlots, neededMinutes);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return visitTimeSlotDtos;
    }

    /**
     * Filters lister of free time slots.
     * Adds to result list a time slot which begin time is after then now.
     *
     * @param timeSlots List of VisitTime. List of free visit time slots.
     * @return List of VisitTime
     */
    private List<VisitTime> filterTimeSlots(List<VisitTime> timeSlots) {
        return timeSlots.stream()
                .filter(timeStol -> timeStol.getBeginTime().isAfter(LocalTime.now()))
                .toList();
    }

    /**
     * Gets time slots which time is enough for services.
     * Processes that time and return list of dto.
     *
     * @param freeTimeSlots List of VisitTime. List of free visit time slots.
     * @param neededMinutes Integer. Number of needed minutes for services.
     * @return List of VisitTimeSlotDto
     */
    private List<VisitTimeSlotDto> getTimeSlotsForClient(List<VisitTime> freeTimeSlots, final Integer neededMinutes) {
        List<VisitTimeSlotDto> dtoList = new ArrayList<>();

        List<VisitTime> sortedTimeSlots = sortByBeginTime(freeTimeSlots);
        var size = sortedTimeSlots.size();

        for (int i = 0; i < size; i++) {
            var freeTime = sortedTimeSlots.get(i);

            var id = freeTime.getId();
            var beginTime = freeTime.getBeginTime();
            var endTime = freeTime.getEndTime();

            var idDurationMap = new HashMap<Long, Long>();
            var duration = getDurationInMinutes(freeTime);
            idDurationMap.put(id, duration);

            if (duration >= neededMinutes) {
                var dto = visitTimeMapper.toDto(beginTime, endTime, idDurationMap);
                dtoList.add(dto);
            } else {
                for (int j = i + 1; j < size; j++) {
                    var nextFreeTime = sortedTimeSlots.get(j);

                    if (endTime.equals(nextFreeTime.getBeginTime())) {
                        endTime = nextFreeTime.getEndTime();

                        var nextDuration = getDurationInMinutes(nextFreeTime);
                        idDurationMap.put(nextFreeTime.getId(), nextDuration);

                        var newDuration = beginTime.until(endTime, ChronoUnit.MINUTES);
                        if (newDuration >= neededMinutes) {
                            var dto = visitTimeMapper.toDto(beginTime, endTime, idDurationMap);
                            dtoList.add(dto);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        dtoList.forEach(dto -> dto.setEndTime(dto.getBeginTime().plusMinutes(neededMinutes)));
        return dtoList;
    }

    /**
     * Sorts time slots by begin time.
     *
     * @param freeTimeList List of VisitTime. List of free visit time slots.
     * @return List of VisitTime
     */
    private List<VisitTime> sortByBeginTime(List<VisitTime> freeTimeList) {
        return freeTimeList.stream()
                .sorted(((o1, o2) -> {
                    var o1BeginTime = o1.getBeginTime();
                    var o2BeginTime = o2.getBeginTime();

                    if (o1BeginTime.isBefore(o2BeginTime)) {
                        return -1;
                    } else if (o1BeginTime.isAfter(o2BeginTime)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }))
                .toList();
    }

    /**
     * Gets duration of time slot in minutes.
     *
     * @param visitTime VisitTime. Visit time slot.
     * @return long
     */
    private long getDurationInMinutes(VisitTime visitTime) {
        return visitTime.getBeginTime().until(visitTime.getEndTime(), ChronoUnit.MINUTES);
    }
}
