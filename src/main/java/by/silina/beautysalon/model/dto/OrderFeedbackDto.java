package by.silina.beautysalon.model.dto;

/**
 * The OrderFeedbackDto class.
 *
 * @author Silina Katsiaryna
 */
public class OrderFeedbackDto {
    private Long id;
    private byte mark;
    private String feedback;
    private boolean isVerified;

    private OrderFeedbackDto() {
    }

    public static OrderFeedbackDtoBuilder builder() {
        return new OrderFeedbackDtoBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte getMark() {
        return mark;
    }

    public void setMark(byte mark) {
        this.mark = mark;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderFeedbackDto that = (OrderFeedbackDto) o;

        if (mark != that.mark) return false;
        if (isVerified != that.isVerified) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return feedback != null ? feedback.equals(that.feedback) : that.feedback == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + mark;
        result = 31 * result + (feedback != null ? feedback.hashCode() : 0);
        result = 31 * result + (isVerified ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("OrderFeedbackDto{")
                .append("id='").append(id)
                .append(", mark='").append(mark)
                .append(", feedback='").append(feedback)
                .append(", isVerified='").append(isVerified)
                .append('}')
                .toString();
    }

    public static class OrderFeedbackDtoBuilder {
        private Long id;
        private byte mark;
        private String feedback;
        private boolean isVerified;

        OrderFeedbackDtoBuilder() {
        }

        public OrderFeedbackDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderFeedbackDtoBuilder mark(byte mark) {
            this.mark = mark;
            return this;
        }

        public OrderFeedbackDtoBuilder feedback(String feedback) {
            this.feedback = feedback;
            return this;
        }

        public OrderFeedbackDtoBuilder isVerified(boolean isVerified) {
            this.isVerified = isVerified;
            return this;
        }

        public OrderFeedbackDto build() {
            OrderFeedbackDto orderFeedback = new OrderFeedbackDto();
            orderFeedback.setId(id);
            orderFeedback.setMark(mark);
            orderFeedback.setFeedback(feedback);
            orderFeedback.setVerified(isVerified);
            return orderFeedback;
        }
    }
}
