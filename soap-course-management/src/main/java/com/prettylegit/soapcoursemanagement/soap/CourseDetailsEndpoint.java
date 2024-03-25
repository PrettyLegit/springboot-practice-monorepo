package com.prettylegit.soapcoursemanagement.soap;

import net.jimmywin.courses.CourseDetails;
import net.jimmywin.courses.GetCourseDetailsRequest;
import net.jimmywin.courses.GetCourseDetailsResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint {
    //method
    // input - GetCourseDetailsRequest
    // output - GetCourseDetailsResponse
    // namespace - http://jimmywin.net/courses
    // name - GetCourseDetailsRequest
    @PayloadRoot(namespace = "http://jimmywin.net/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        CourseDetails courseDetais = new CourseDetails();
        courseDetais.setId(request.getId());
        courseDetais.setName("Microservices Course");
        courseDetais.setDescription("This course will help you to understand Microservices");
        response.setCourseDetails(courseDetais);
        return response;
    }
}
