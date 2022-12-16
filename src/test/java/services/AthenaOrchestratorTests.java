package services;

import org.example.models.VerifyStock;
import org.example.services.AthenaOrchestrator;
import org.example.services.AthenaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AthenaOrchestratorTests {
    private String query = "select * from Transactions;";
    private String queryExecutionId = "123456";
    private AthenaOrchestrator orchestrator;

    @Mock
    private AthenaService service;

    @BeforeEach
    public void setup() {
        this.orchestrator = new AthenaOrchestrator(query, service);
    }

    //positive test
    @Test
    public void Should_Return_List_When_Athena_Execute_Success() {

        when(this.service.submitQuery(this.query)).thenReturn(queryExecutionId);
        verify(this.service).waitForQueryToComplete(this.queryExecutionId);

        List<VerifyStock> transactionList = this.orchestrator.execute();
    }
}