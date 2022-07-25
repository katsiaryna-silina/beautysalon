package by.silina.beautysalon.model.dto;

import java.time.LocalTime;
import java.util.Map;

/**
 * The VisitTimeSlotDto class.
 *
 * @author Silina Katsiaryna
 */
public class VisitTimeSlotDto {
    private LocalTime beginTime;
    private LocalTime endTime;
    private Map<Long, Long> visitTimeIdDuration;

    private VisitTimeSlotDto() {
    }

    public static VisitTimeSlotDtoBuilder builder() {
        return new VisitTimeSlotDtoBuilder();
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Map<Long, Long> getVisitTimeIdDuration() {
        return visitTimeIdDuration;
    }

    public void setVisitTimeIdDuration(Map<Long, Long> visitTimeIdDuration) {
        this.visitTimeIdDuration = visitTimeIdDuration;
    }

    public static class VisitTimeSlotDtoBuilder {
        private LocalTime beginTime;
        private LocalTime endTime;
        private Map<Long, Long> visitTimeIdDuration;

        VisitTimeSlotDtoBuilder() {
        }

        public VisitTimeSlotDtoBuilder beginTime(LocalTime beginTime) {
            this.beginTime = beginTime;
            return this;
        }

        public VisitTimeSlotDtoBuilder endTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public VisitTimeSlotDtoBuilder visitTimeIdDuration(Map<Long, Long> visitTimeIdDuration) {
            this.visitTimeIdDuration = visitTimeIdDuration;
            return this;
        }

        public VisitTimeSlotDto build() {
            VisitTimeSlotDto visitTimeSlotDto = new VisitTimeSlotDto();
            visitTimeSlotDto.setBeginTime(beginTime);
            visitTimeSlotDto.setEndTime(endTime);
            visitTimeSlotDto.setVisitTimeIdDuration(visitTimeIdDuration);
            return visitTimeSlotDto;
        }
    }
}
