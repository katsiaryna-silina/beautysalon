package by.silina.beautysalon.model.entity;

public class OrderFeedback extends AbstractEntity {
    private Integer mark;
    private String feedback;

    private OrderFeedback() {
    }

    public static OrderFeedbackBuilder builder() {
        return new OrderFeedbackBuilder();
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderFeedback that = (OrderFeedback) o;

        if (mark != null ? !mark.equals(that.mark) : that.mark != null) return false;
        return feedback != null ? feedback.equals(that.feedback) : that.feedback == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (feedback != null ? feedback.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("OrderFeedback{")
                .append("mark=").append(mark)
                .append(", feedback='").append(feedback)
                .append('}')
                .toString();
    }

    public static class OrderFeedbackBuilder {
        private Long id;
        private Integer mark;
        private String feedback;

        OrderFeedbackBuilder() {
        }

        public OrderFeedback.OrderFeedbackBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderFeedback.OrderFeedbackBuilder mark(Integer mark) {
            this.mark = mark;
            return this;
        }

        public OrderFeedback.OrderFeedbackBuilder feedback(String feedback) {
            this.feedback = feedback;
            return this;
        }

        public OrderFeedback build() {
            OrderFeedback orderFeedback = new OrderFeedback();
            orderFeedback.setId(id);
            orderFeedback.setMark(mark);
            orderFeedback.setFeedback(feedback);
            return orderFeedback;
        }
    }
}
