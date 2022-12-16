package org.example.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import org.example.models.Product;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvocationType;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

public class FirstHandler implements RequestHandler<Object, Object> {
    private static final String SECOND_FUNCTION_NAME = "RESecondFunction";
    private final LambdaClient lambdaClient = DependencyFactory.lambdaClient();

    @Override
    public Object handleRequest(Object input, Context context) {
//        var payload = SdkBytes.fromUtf8String("{\"input\":\"bootcamp\"}");
        Product payloadData = new Product();
        payloadData.setId("1");
        payloadData.setName("bootcamp");
        payloadData.setStock(10);



        //This will convert object to JSON String
        String inputJSON = new Gson().toJson(payloadData);
        SdkBytes payload = SdkBytes.fromUtf8String(inputJSON);

        InvokeRequest invokeRequest = InvokeRequest.builder()
                .functionName(SECOND_FUNCTION_NAME)
                .invocationType(InvocationType.EVENT)
                .payload(payload)
                .build();

        InvokeResponse response = lambdaClient.invoke(invokeRequest);

        System.out.println("Response: " + response.toString());
        System.out.println("Payload: " + response.payload().asUtf8String());

        return input;
    }
}
