package by.silina.beautysalon.model.entity;

import java.time.LocalTime;

/**
 * The VisitTime class.
 *
 * @author Silina Katsiaryna
 */
public class VisitTime extends AbstractEntity {
    private LocalTime beginTime;
    private LocalTime endTime;
    private boolean isDeprecated;

    private VisitTime() {
    }

    public static VisitTimeBuilder builder() {
        return new VisitTimeBuilder();
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

    public boolean isDeprecated() {
        return isDeprecated;
    }

    public void setDeprecated(boolean deprecated) {
        isDeprecated = deprecated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        VisitTime visitTime = (VisitTime) o;

        if (isDeprecated != visitTime.isDeprecated) return false;
        if (beginTime != null ? !beginTime.equals(visitTime.beginTime) : visitTime.beginTime != null) return false;
        return endTime != null ? endTime.equals(visitTime.endTime) : visitTime.endTime == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (beginTime != null ? beginTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (isDeprecated ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("VisitTime{")
                .append("beginTime='").append(beginTime)
                .append(", endTime='").append(endTime)
                .append(", isDeprecated='").append(isDeprecated)
                .append('}')
                .toString();
    }

    public static class VisitTimeBuilder {
        private Long id;
        private LocalTime beginTime;
        private LocalTime endTime;
        private boolean isDeprecated;

        VisitTimeBuilder() {
        }

        public VisitTime.VisitTimeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public VisitTime.VisitTimeBuilder beginTime(LocalTime beginTime) {
            this.beginTime = beginTime;
            return this;
        }

        public VisitTime.VisitTimeBuilder endTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public VisitTime.VisitTimeBuilder isDeprecated(boolean isDeprecated) {
            this.isDeprecated = isDeprecated;
            return this;
        }

        public VisitTime build() {
            VisitTime visitTime = new VisitTime();
            visitTime.setId(id);
            visitTime.setBeginTime(beginTime);
            visitTime.setEndTime(endTime);
            visitTime.setDeprecated(isDeprecated);
            return visitTime;
        }
    }
}
