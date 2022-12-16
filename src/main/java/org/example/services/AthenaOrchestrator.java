package org.example.services;

import lombok.SneakyThrows;
import org.example.models.VerifyStock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AthenaOrchestrator {
    private static final Logger logger = LoggerFactory.getLogger(AthenaService.class);
    private final String query;
    private AthenaService athenaService;

    public AthenaOrchestrator(String query, AthenaService athenaService) {
        this.query = query;
        this.athenaService = athenaService;
    }

    @SneakyThrows
    public List<VerifyStock> execute() {
        List<VerifyStock> transactionList = new ArrayList<>();
        String queryExecutionId =
                this.athenaService.submitQuery(this.query);

        this.athenaService.waitForQueryToComplete(queryExecutionId);
        this.athenaService.processQueryResult(queryExecutionId);
        /*
        transactionList = this.athenaService.processQueryResult(queryExecutionId);
        for (VerifyStock t: transactionList) {
            logger.info("Transaction = " + t);
        }
        */
         

        return transactionList;
    }
}