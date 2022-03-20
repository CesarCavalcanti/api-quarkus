package br.com.eprecise.testdev;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title="API eprice-testdev",
                version = "1.0.",
                contact = @Contact(
                        name = "Cesar Cavalcanti",
                        url = "https://www.linkedin.com/in/cesar-cavalcanti-62945120a/",
                        email = "bangjogos1@gmail.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class QuarkusEpriceTestDevApplication extends Application {
}
