package by.silina.beautysalon.util;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

public class BrandTag extends TagSupport {
    private static final String BRAND = "2cat";

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(BRAND);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY; //don't process tag body
    }
}
