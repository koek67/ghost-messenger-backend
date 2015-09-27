package mvc.model;

import workflow.retrieval.Retriever;

/**
 * Created by koushikkrishnan on 9/27/15.
 */
public class ResponseDAO {
    public String response;

    public ResponseDAO(String response) {
        String m = Retriever.retrieve(response);
        this.response = m;
    }
}
