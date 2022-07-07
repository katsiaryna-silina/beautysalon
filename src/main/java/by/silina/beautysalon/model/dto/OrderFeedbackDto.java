package by.silina.beautysalon.model.dto;

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
