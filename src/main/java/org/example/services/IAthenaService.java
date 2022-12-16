package org.example.services;

import org.example.models.Product;
import org.example.models.VerifyStock;

import java.util.List;

public interface IAthenaService {
    String submitQuery(String myQuery);
    void waitForQueryToComplete(String queryExecutionId);
    List<Product> processQueryResult(String queryExecutionId);
}
