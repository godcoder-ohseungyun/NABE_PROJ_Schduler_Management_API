package nabe.scheduler.api.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Component
public class RestHeaderCreator {

    private static final String self_descriptive_msg_info = "; href=next state";
    private static final String hateoas_msg_info = "<https://citrine-sing-062.notion.site/Scheduler-Server-API-0eff9253bc7d4362bbaeaa1dae9f3a7a>;  rel=\"api doc\"";


    public static HttpHeaders createRestfulHeader(Class<?> controller , String remainUrls ,String httpMethod ){

        HttpHeaders restfulHeader = new HttpHeaders();

        addSelfDescriptiveHeader(restfulHeader, controller,remainUrls,httpMethod);
        addHateoasHeader(restfulHeader);

        return restfulHeader;
    }

    private static void addHateoasHeader(HttpHeaders headers){
        headers.add("Link" , hateoas_msg_info);
    }

    private static void addSelfDescriptiveHeader(HttpHeaders header ,Class<?> controller , String remainUrls ,String httpMethod){

        StringBuffer link = new StringBuffer(httpMethod);
        link.append(linkTo(controller).slash(remainUrls).toString());
        link.append(self_descriptive_msg_info);

        header.add("Location" , link.toString());
    }


}
