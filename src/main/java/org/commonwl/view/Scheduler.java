package org.commonwl.view;

import org.commonwl.view.workflow.QueuedWorkflow;
import org.commonwl.view.workflow.QueuedWorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    QueuedWorkflowRepository queuedWorkflowRepository;

    @Scheduled(cron = "*/10 * * * * ?")
    public void addDeleteQueuedWorkFlow() {
        QueuedWorkflow queuedWorkflow = queuedWorkflowRepository.findOne("60755303902b100001d5cc80");
        System.out.println("Retrieved QW " + queuedWorkflow.getTempRepresentation().getRetrievedOn() + " Id: " + queuedWorkflow.getId());



        System.out.println("Deleting QW...");
        queuedWorkflowRepository.deleteByRetrievedFrom(queuedWorkflow.getTempRepresentation().getRetrievedFrom());
        System.out.println("QW Deleted");

        System.out.println("Checking for Deleted QW");

        QueuedWorkflow qwD = queuedWorkflowRepository.findByRetrievedFrom(queuedWorkflow.getTempRepresentation().getRetrievedFrom());
        System.out.println("QW retrieved: " + qwD.getTempRepresentation().getRetrievedFrom() + " Id: " + qwD.getId());

        System.out.println();

        System.out.println("New Deleting QW...");
        queuedWorkflowRepository.deleteByTempRepresentation_RetrievedFrom(queuedWorkflow.getTempRepresentation().getRetrievedFrom());
        System.out.println("QW Deleted");

        System.out.println("Checking for Deleted QW");

        QueuedWorkflow qwDNew = queuedWorkflowRepository.findByRetrievedFrom(queuedWorkflow.getTempRepresentation().getRetrievedFrom());
        System.out.println("QW retrieved: " + qwD.getTempRepresentation().getRetrievedFrom() + " Id: " + qwDNew.getId());

        System.out.println();

        System.out.println("Saving QW...");
        queuedWorkflowRepository.save(queuedWorkflow);
        System.out.println("QW saved ?");

        QueuedWorkflow qw = queuedWorkflowRepository.findByRetrievedFrom(queuedWorkflow.getTempRepresentation().getRetrievedFrom());
        System.out.println(qw);
        System.out.println("QW retrieved: " + qw.getTempRepresentation().getRetrievedFrom() + " Id: " + qw.getId());

        System.out.println();
    }
}
