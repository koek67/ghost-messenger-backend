package hello;

import workflow.retrieval.Retriever;

/**
 * Created by koushikkrishnan on 9/27/15.
 */
public class Response {
    public String response;

    public Response(String response) {
        String m = Retriever.retrieve(response);
        this.response = m;
    }
}
