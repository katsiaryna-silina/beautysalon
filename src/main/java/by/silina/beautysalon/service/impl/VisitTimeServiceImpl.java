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

public class VisitTimeServiceImpl implements VisitTimeService {
    private static final VisitTimeServiceImpl instance = new VisitTimeServiceImpl();
    private final VisitTimeDaoImpl visitTimeDao = VisitTimeDaoImpl.getInstance();
    private final VisitTimeMapper visitTimeMapper = VisitTimeMapperImpl.getInstance();

    private VisitTimeServiceImpl() {
    }

    public static VisitTimeServiceImpl getInstance() {
        return instance;
    }

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

    private List<VisitTime> filterTimeSlots(List<VisitTime> timeSlots) {
        return timeSlots.stream()
                .filter(timeStol -> timeStol.getBeginTime().isAfter(LocalTime.now()))
                .toList();
    }

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

    private long getDurationInMinutes(VisitTime visitTime) {
        return visitTime.getBeginTime().until(visitTime.getEndTime(), ChronoUnit.MINUTES);
    }
}
