package by.silina.beautysalon.model.entity;

public class OrderFeedback extends AbstractEntity {
    private byte mark;
    private String feedback;
    private boolean isVerified;
    private User user;

    private OrderFeedback() {
    }

    public static OrderFeedbackBuilder builder() {
        return new OrderFeedbackBuilder();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderFeedback that = (OrderFeedback) o;

        if (mark != that.mark) return false;
        if (isVerified != that.isVerified) return false;
        if (feedback != null ? !feedback.equals(that.feedback) : that.feedback != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + mark;
        result = 31 * result + (feedback != null ? feedback.hashCode() : 0);
        result = 31 * result + (isVerified ? 1 : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("OrderFeedback{")
                .append("mark=").append(mark)
                .append(", feedback='").append(feedback)
                .append(", isVerified='").append(isVerified)
                .append(", user='").append(user)
                .append('}')
                .toString();
    }

    public static class OrderFeedbackBuilder {
        private Long id;
        private byte mark;
        private String feedback;
        private boolean isVerified;
        private User user;

        OrderFeedbackBuilder() {
        }

        public OrderFeedbackBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OrderFeedbackBuilder mark(byte mark) {
            this.mark = mark;
            return this;
        }

        public OrderFeedbackBuilder feedback(String feedback) {
            this.feedback = feedback;
            return this;
        }

        public OrderFeedbackBuilder isVerified(boolean isVerified) {
            this.isVerified = isVerified;
            return this;
        }

        public OrderFeedbackBuilder user(User user) {
            this.user = user;
            return this;
        }

        public OrderFeedback build() {
            OrderFeedback orderFeedback = new OrderFeedback();
            orderFeedback.setId(id);
            orderFeedback.setMark(mark);
            orderFeedback.setFeedback(feedback);
            orderFeedback.setVerified(isVerified);
            orderFeedback.setUser(user);
            return orderFeedback;
        }
    }
}
