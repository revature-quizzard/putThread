package com.revature.putThread;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.putThread.models.Threads;
import com.revature.putThread.repositories.ThreadsRepo;
import com.revature.putThread.services.ThreadsService;

public class PutThreadsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Gson mapper = new GsonBuilder().setPrettyPrinting().create();

    /**
     * @param requestEvent - The proxy event from AWS API Gateway
     * @param context - the context of the request
     * @return - A response along with an HTTP status code
     * @Author - Charles Mettee
     */
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {

        LambdaLogger logger = context.getLogger();
        ThreadsRepo threadsRepo = new ThreadsRepo();
        ThreadsService threadsService = new ThreadsService(threadsRepo, logger);

        Threads threads = mapper.fromJson(requestEvent.getBody(), Threads.class);

        APIGatewayProxyResponseEvent respEvent = new APIGatewayProxyResponseEvent();
        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Headers", "Content-Type,X-Amz-Date,Authorization");
        headers.put("Access-Control-Allow-Origin", "*");
        respEvent.setHeaders(headers);

        try{
            threadsService.updateThreads(threads);
            respEvent.setStatusCode(200);
            return respEvent;
        } catch (Exception e){
            respEvent.setStatusCode(400);
            return respEvent;
        }

    }


}
