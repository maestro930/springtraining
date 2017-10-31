package priv.rdo.sdaspring.task07;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private List<ErrorDetail> details = new ArrayList<>();

    public void addErrorDetails(ErrorDetail detail) {
        details.add(detail);
    }

    public List<ErrorDetail> getDetails() {
        return details;
    }
}
