package org.ivangrod.needlehack.infrastructure.acceptance.helper;

import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;

@Component
public class World {

    private ResultActions preconditionResponse;

    private ResultActions actResponse;

    public ResultActions getPreconditionResponse() {
        return preconditionResponse;
    }

    public void setPreconditionResponse(ResultActions preconditionResponse) {
        this.preconditionResponse = preconditionResponse;
    }

    public ResultActions getActResponse() {
        return actResponse;
    }

    public void setActResponse(ResultActions actResponse) {
        this.actResponse = actResponse;
    }
}
