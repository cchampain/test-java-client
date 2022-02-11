package nuxeo.twentyone.project;

import org.nuxeo.client.NuxeoClient;
import org.nuxeo.client.objects.Document;
import org.nuxeo.client.objects.Repository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class JavaClientTest {

    protected Repository repository;
    public static final String TestDoc = "test_required_value";

    @Before
    public void setUp() {
        NuxeoClient client = new NuxeoClient.Builder()
                .url("http://localhost:8080/nuxeo")
                .authentication("Administrator", "Administrator123")
                .schemas("*")
                .connect();
        repository = client.repository();
    }

    @Test
    public void shouldReportListOfRequiredValues() {
        Document docOne = attemptToCreateDoc();
        assertNotNull(docOne);
    }

    private Document attemptToCreateDoc() {
       Document doc = Document.createWithName("docOne", TestDoc);
       doc.setPropertyValue("dc:description", "I did not set the required properties so that the test will fail");
       return repository.createDocumentByPath("/default-domain/workspaces/ws", doc);
    }
}
