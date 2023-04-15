package org.example.booking.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.example.booking.contract.BookingResponse;
import reactor.core.publisher.Mono;

@WorkflowInterface
public interface BookingWorkflow {

    @WorkflowMethod
    BookingResponse book(String name);
}
